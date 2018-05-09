# -*- coding: latin-1 -*-
import inspect, sys, traceback, os, types, operator


def missatge(text):
    msg = text + '\n'
    msg += '--------------------------------------------------------------------\n'
    msg += traceback.format_exc()
    return msg

class MissatgeError(Exception):
    def __init__(self, missatge):
        self._missatge = missatge

def cadenaOrdinal(n):
    if n == 0: return '1er'
    elif n == 1: return '2on'
    elif n == 3: return '4rt'
    else: return '%dé' % (n + 1)

def comprovaVariable(objecte, elements, cadena_objecte):
    if not cadena_objecte in elements:
        return "     - La classe %s ha de tenir una VARIABLE MEMBRE anomenada '%s'.\n" % (objecte.__name__, cadena_objecte)
    if sys.version_info[1] >= 5: # No es pot fer aquesta comprovació per versions de Python anterior a la versió 2.5
        ref = getattr(objecte, cadena_objecte)
        if type(ref) != types.MemberDescriptorType:
            return "     - %s no està declarat com una VARIABLE MEMBRE de la classe '%s'.\n" % (cadena_objecte, objecte.__name__)
    return ""

def comprovaFuncio(objecte, elements, cadena_objecte, parametres = []):
    if not cadena_objecte in elements:
        return "     - La classe %s ha de tenir una FUNCIÓ MEMBRE anomenada '%s'.\n" % (objecte.__name__, cadena_objecte)
    ref = getattr(objecte, cadena_objecte)
    if type(ref) != types.MethodType:
        return "     - %s no està declarat com una FUNCIÓ MEMBRE de la classe '%s'.\n" % (cadena_objecte, objecte.__name__)
    parametres_funcio = inspect.getargspec(ref)[0]
    if parametres_funcio[0] != 'self':
        text  = "     - El primer paràmetre de la funció membre '%s' de la classe '%s' ha de ser 'self'.\n" % (cadena_objecte, objecte.__name__)
        text_parametres = ''
        if len(parametres) > 0:
            text_parametres = ', ' + ', '.join(parametres)
        text += "     - La capçalera de la classe ha de ser: 'def %s(self%s):'\n" % (cadena_objecte, text_parametres)
        return text
    parametres_funcio = parametres_funcio[1:]
    if len(parametres_funcio) != len(parametres):
        text  = "     - La funció membre '%s' de la classe '%s' té un nombre inesperat de paràmetres.\n" % (cadena_objecte, objecte.__name__)
        text_parametres = ''
        if len(parametres) > 0:
            text_parametres = ', ' + ', '.join(parametres)
        text += "     - La capçalera de la classe ha de ser: 'def %s(self%s):'\n" % (cadena_objecte, text_parametres)
        return text
    for idx in range(len(parametres)):
        if not parametres[idx] in parametres_funcio[idx]:
            if idx == 0: ordinal = '2on'
            elif idx == 2: ordinal = '4rt'
            else: ordinal = '%dé' % (idx + 2)
            text_parametres = ''
            if len(parametres) > 0:
                text_parametres = ', ' + ', '.join(parametres)
            text  = "     - El %s paràmetre de la funció membre '%s' de la classe '%s' s'ha de dir '%s'.\n" % (ordinal, cadena_objecte, objecte.__name__, parametres[idx])
            text += "     - La capçalera de la classe ha de ser: 'def %s(self%s):'\n" % (cadena_objecte, text_parametres)
            return text
    return ''

def comprovaClasse(objecte, elements, cadena_objecte):
    if not cadena_objecte in elements:
        return "     - La classe %s ha de tenir una CLASSE IMBRICADA anomenada '%s'.\n" % (objecte.__name__, cadena_objecte)
    ref = getattr(objecte, cadena_objecte)
    if type(ref) != types.TypeType:
        return "     - %s no està declarat com una CLASSE IMBRICADA de la classe '%s'.\n" % (cadena_objecte, objecte.__name__)
    return ''

def comprovarFuncionsPilot(objecte):
    object_methods = set(['__setattr__', '__reduce_ex__', '__new__', '__getattribute__', '__str__', '__format__', '__reduce__', '__class__', '__delattr__', '__subclasshook__', '__repr__', '__hash__', '__sizeof__', '__doc__', '__module__'])
    # Variables ...............................................................
    expected_variables = ['_Pilot__previous', '_Pilot__sonar', '_Pilot__inverse', '_Pilot__culdesac']
    # Mètodes .................................................................
    expected_methods  = []
    expected_methods += [('__init__', [])]
    expected_methods += [('getPrevious', [])]
    expected_methods += [('setSonar', ['sonar'])]
    expected_methods += [('isCrossRoad', [])]
    expected_methods += [('getCulDeSac', [])]
    expected_methods += [('setCulDeSac', ['culdesac'])]
    expected_methods += [('moveTo', ['action'])]
    expected_methods += [('nextMove', [])]
    expected_methods += [('possibleActions', [])]
    # Altres ..................................................................
    expected_classes = []
    expected_other = ['__slots__']
    expected_linkedlist_methods = set(expected_variables + map(operator.itemgetter(0), expected_methods) + expected_classes + expected_other)
    missatge = ''
    
    current_methods = set(dir(objecte)).difference(object_methods)
    extra_members = current_methods.difference(expected_linkedlist_methods)
    if len(extra_members) > 0:
        funcions = filter(lambda x: x.count('_Pilot') == 0, list(extra_members))
        variables = filter(lambda x: x.count('_Pilot') > 0, list(extra_members))
        if len(funcions) > 0:
            missatge += "     - La classe Pilot té més funcions de les necessàries: " + ', '.join(funcions)
        if len(variables) > 0:
            missatge += "     - La classe Pilot té més variables membre de les necessàries: " + ', '.join(variables)
    
    #  Comprovar variables, mètodes i classes imbricades ..........................................
    for nom_variable in expected_variables:
        missatge += comprovaVariable(objecte, expected_linkedlist_methods, nom_variable)
    for nom_metode, parametres_metode in expected_methods:
        missatge += comprovaFuncio(objecte, expected_linkedlist_methods, nom_metode, parametres_metode)
    for nom_objecte in expected_classes:
        missatge += comprovaClasse(objecte, expected_linkedlist_methods, nom_objecte)
    
    if len(missatge) > 0:
        raise MissatgeError, missatge

def comprovarResultat(Pilot, pilot, direccio, sonar):
    direccions_possibles = set()
    for moviment in sonar:
        if sonar[moviment]:
            direccions_possibles.add(moviment)
    
    inverse = { 'left' : 'right', 'right' : 'left', 'up' : 'down', 'down' : 'up' }
    pilot.setSonar({ 'left' : True, 'right' : True, 'up' : True, 'down' : True })
    pilot.moveTo(direccio)
    pilot.setSonar(sonar)
    accions = pilot.possibleActions()
    if type(accions) != type(Pilot.QueueList()):
        raise MissatgeError, "La funció 'possibleActions' ha de retornar un objecte de tipus 'QueueList'."
    if accions.size() != sum(sonar.values()):
        raise MissatgeError, "En una intersecció amb quatre cantonades la cua ha de retornar quatre accions."
    culdesac, moviment = accions.pop()
    if moviment != inverse[direccio]:
        raise MissatgeError, "El primer element que surt de la cua ha de ser la tupla corresponent al moviment per sortir de la intersecció."
    if culdesac != True:
        raise MissatgeError, "El primer element que surt de la cua ha de tenir el flag a True."
    direccions_possibles.remove(inverse[direccio])
    
    while accions.size() > 0:
        culdesac, moviment = accions.pop()
        if not moviment in direccions_possibles:
            raise MissatgeError, "La cua que retorna 'possibleActions' té direccions que no són possibles."
        if culdesac:
            raise MissatgeError, "La cua té una tupla amb un flag a True que hauria de ser False (no és la direcció de tornada)."
        direccions_possibles.remove(moviment)
    if len(direccions_possibles) > 0:
        raise MissatgeError, "La funció 'possibleActions' no retorna totes les direccions possibles."

def comprovarPossibleActions(Pilot):
    pilot = Pilot.Pilot()
    
    # Comprovar la funció possibleActions per interseccions de quatre elements ...................
    comprovarResultat(Pilot, pilot, 'up', { 'left' : True, 'right' : True, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'left', { 'left' : True, 'right' : True, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'right', { 'left' : True, 'right' : True, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'down', { 'left' : True, 'right' : True, 'up' : True, 'down' : True })
    
    # Comprovar la funció possibleActions per interseccions LRU ..................................
    comprovarResultat(Pilot, pilot, 'down', { 'left' : True, 'right' : True, 'up' : True, 'down' : False })
    comprovarResultat(Pilot, pilot, 'left', { 'left' : True, 'right' : True, 'up' : True, 'down' : False })
    comprovarResultat(Pilot, pilot, 'right', { 'left' : True, 'right' : True, 'up' : True, 'down' : False })
    
    # Comprovar la funció possibleActions per interseccions LRD ..................................
    comprovarResultat(Pilot, pilot, 'up', { 'left' : True, 'right' : True, 'up' : False, 'down' : True })
    comprovarResultat(Pilot, pilot, 'left', { 'left' : True, 'right' : True, 'up' : False, 'down' : True })
    comprovarResultat(Pilot, pilot, 'right', { 'left' : True, 'right' : True, 'up' : False, 'down' : True })
    
    # Comprovar la funció possibleActions per interseccions LUD ..................................
    comprovarResultat(Pilot, pilot, 'right', { 'left' : True, 'right' : False, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'up', { 'left' : True, 'right' : False, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'down', { 'left' : True, 'right' : False, 'up' : True, 'down' : True })
    
    # Comprovar la funció possibleActions per interseccions RUD ..................................
    comprovarResultat(Pilot, pilot, 'left', { 'left' : False, 'right' : True, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'up', { 'left' : False, 'right' : True, 'up' : True, 'down' : True })
    comprovarResultat(Pilot, pilot, 'down', { 'left' : False, 'right' : True, 'up' : True, 'down' : True })

def jocproves(arxiu):
    # COMPROVAR QUE L'ARXIU ES POT CARREGAR .......................................................
    if not (os.path.exists(arxiu) and os.path.isfile(arxiu)):
        print "No es pot accedir a l'arxiu '%s'. Comproveu que el nom de l'arxiu sigui exactament el mateix i que l'arxiu estigui a la mateixa ruta que el joc de proves." % (arxiu)
        return
    if not os.access(arxiu, os.R_OK):
        print "No es pot llegir l'arxiu '%s'" % (arxiu)
        return
    try:
        if arxiu[-3:] == '.py':
            nom_arxiu = arxiu[:-3]
        else:
            nom_arxiu = arxiu
        arxiu_name = arxiu
        Pilot = __import__(nom_arxiu)
        reload(Pilot)
    except:
        print missatge("L'arxiu '%s' té errors de sintaxis:" % (arxiu))
        return
    
    # COMPROVAR EL NOM DE LES FUNCIONS MEMBRE .....................................................
    print " + Comprovant les funcions membre de la classe:",
    sys.stdout.flush()
    if not 'Pilot' in dir(Pilot):
        print "L'arxiu '%s' no conté la classe 'Pilot'" % arxiu
        return
    try:
        comprovarFuncionsPilot(Pilot.Pilot)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "La capçalera de la classe és incorrecta. No es pot continuar."
        return
    print "[CORRECTE]"
    
    # AVALUAR LA FUNCIÓ POSSIBLEACTIONS DEL PILOT .................................................
    print " + Comprovant la funció possibleAction de la classe Pilot:",
    sys.stdout.flush()
    try:
        comprovarPossibleActions(Pilot)
        pass
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe Pilot:")
        return
    print "[CORRECTE]"
    
if __name__ == '__main__':
    jocproves("Pilot.py")
