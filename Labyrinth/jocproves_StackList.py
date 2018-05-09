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

def comprovarFuncionsStackList(objecte):
    object_methods = set(['__setattr__', '__reduce_ex__', '__new__', '__getattribute__', '__str__', '__format__', '__reduce__', '__class__', '__delattr__', '__subclasshook__', '__repr__', '__hash__', '__sizeof__', '__doc__', '__module__'])
    expected_variables = ['_StackList__llista']
    # Mètodes de StackList ....................................................
    expected_methods  = []
    expected_methods += [('__init__', [])]
    expected_methods += [('push', ['data'])]
    expected_methods += [('pop', [])]
    expected_methods += [('head', [])]
    expected_methods += [('purge', [])]
    expected_methods += [('__len__', [])]
    expected_methods += [('__str__', [])]
    expected_classes = []
    expected_other = ['__slots__']
    expected_linkedlist_methods = set(expected_variables + map(operator.itemgetter(0), expected_methods) + expected_classes + expected_other)
    missatge = ''
    
    current_methods = set(dir(objecte)).difference(object_methods)
    extra_members = current_methods.difference(expected_linkedlist_methods)
    if len(extra_members) > 0:
        funcions = filter(lambda x: x.count('_LinkedList') == 0, list(extra_members))
        variables = filter(lambda x: x.count('_LinkedList') > 0, list(extra_members))
        if len(funcions) > 0:
            missatge += "     - La classe StackList té més funcions de les necessàries: " + ', '.join(funcions)
        if len(variables) > 0:
            missatge += "     - La classe StackList té més variables membre de les necessàries: " + ', '.join(variables)
    
    #  Comprovar variables, mètodes i classes imbricades ..........................................
    for nom_variable in expected_variables:
        missatge += comprovaVariable(objecte, expected_linkedlist_methods, nom_variable)
    for nom_metode, parametres_metode in expected_methods:
        missatge += comprovaFuncio(objecte, expected_linkedlist_methods, nom_metode, parametres_metode)
    for nom_objecte in expected_classes:
        missatge += comprovaClasse(objecte, expected_linkedlist_methods, nom_objecte)
    
    if len(missatge) > 0:
        raise MissatgeError, missatge

def pilaBuida(StackList):
    pila = StackList.StackList()
    llista = StackList.LinkedList()
    if type(pila._StackList__llista) != type(llista):
        raise MissatgeError, "La variable '__llista' de la pila ha de ser un objecte de tipus 'LinkedList'."
    if not pila._StackList__llista.isEmpty():
        raise MissatgeError, "La llista '__llista' no està buida quan la pila està buida."
    
    try:
        pila.head()
        raise MissatgeError, "La funció 'head' ha de generar una excepció de tipus 'IndexError' quan la pila està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        pila.pop()
        raise MissatgeError, "La funció 'pop' ha de generar una excepció de tipus 'IndexError' quan la pila està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    
    if len(pila) != 0:
        raise MissatgeError, "La funció 'size' ha de retornar 0 quan la pila està buida."
    pila.purge()

def comprovaValorsPila(StackList, pila, valors):
    N = len(valors)
    if len(pila) != N:
        raise MissatgeError, "La funció '__len__' no retorna el número d'elements de la cua (retorna '%s' però hauria de retornar '%d'). Possible error a les funcions '__len__' o 'push'." % (str(len(cua)), N)
    valors_pila = []
    aux = pila._StackList__llista._LinkedList__head
    while aux != None:
        valors_pila.append(aux.getData())
        aux = aux.getNext()
    if len(valors_pila) != N:
        raise MissatgeError, "La '__llista' LinkedList de la pila no està correctament enllaçada. Es pot accedir a %d elements però n'hi hauria d'haver %d." % (len(valors_pila), N)
    
    if sum(map(lambda x, y: x == y, valors_pila, valors)) == N:
        return # Correcte
    valors_pila.reverse()
    if sum(map(lambda x, y: x == y, valors_pila, valors)) == N:
        raise MissatgeError, "La '__llista' LinkedList de la pila no guarda els elements de la forma més eficient possible."
    else:
        raise MissatgeError, "La '__llista' LinkedList de la pila no guarda els elements en cap ordre concret."

def pilaMultipleElements(StackList):
    elements = [20, 17, 7, 19, 23, 17, 5, 8, 9]
    pila = StackList.StackList()
    # Afegint un element a la pila ................................................................
    pila.push(elements[0])
    comprovaValorsPila(StackList, pila, [elements[0]])
    if pila.head() != elements[0]:
        raise MissatgeError, "Al afegir un únic element a la pila, la funció 'head' no retorna el valor d'aquest element."
    if pila.pop() != elements[0]:
        raise MissatgeError, "La funció 'pop' no ha retornat el valor de l'element eliminat de la pila."
    try:
        pila.pop()
        raise MissatgeError, "La funció 'pop' ha de genera una excepció de tipus IndexError quan la pila està buida."
    except IndexError: pass
    except MissatgeError: raise
    except: raise MissatgeError, "La funció 'pop' ha fet un pop d'una pila buida ha generat una excepció de tipus no esperat."
    if len(pila) != 0:
        raise MissatgeError, "Desprès de fer un 'pop' de l'últim element la mida de la pila hauria de ser 0 (la funció '__len__' falla o la variable '__size no s'actualitza correctament)."
    if not pila._StackList__llista.isEmpty():
        raise MissatgeError, "La llista de la pila no està buida desprès de fer un 'pop' de l'únic element de la pila."
    
    pila.push(elements[0])
    pila.purge()
    if len(pila) != 0:
        raise MissatgeError, "Desprès de fer un 'purge' la mida de la pila ha de ser 0 (la funció '__len__' falla o la variable '__size no s'actualitza correctament)."
    if not pila._StackList__llista.isEmpty():
        raise MissatgeError, "La llista de la pila no està buida desprès de fer un 'purge' de l'únic element de la pila."
    
    # Afegint la meitat dels elements .............................................................
    resultat_esperat = []
    for idx in range(len(elements) / 2):
        pila.push(elements[idx])
        resultat_esperat.insert(0, elements[idx])
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if pila.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    while len(resultat_esperat) > 0:
        if resultat_esperat[0] != pila.pop():
            raise MissatgeError, "La funció 'pop' retorna un valor no esperat."
        resultat_esperat.pop(0)
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if len(resultat_esperat) > 0:
            if pila.head() != resultat_esperat[0]:
                raise MissatgeError, "La funció 'head' desprès de fer un 'pop' retorna un valor no esperat."
        else:
            if not pila._StackList__llista.isEmpty():
                raise MissatgeError, "La llista de la pila no està buida desprès de fer un 'pop' de l'únic element de la pila."
    
    # Afegir-ne la meitat, treure'n un terç i afegir la resta ........................................
    for idx in range(len(elements) / 2): # AFEGIR LA MEITAT
        pila.push(elements[idx])
        resultat_esperat.insert(0, elements[idx])
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if pila.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    for idx in range(len(elements) / 3): # TREURE'N UN TERÇ
        if resultat_esperat[0] != pila.pop():
            raise MissatgeError, "La funció 'pop' retorna un valor no esperat."
        resultat_esperat.pop(0)
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if pila.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'pop' retorna un valor no esperat."
    for idx in range(len(elements) / 2, len(elements)): # AFEGIR LA RESTA
        pila.push(elements[idx])
        resultat_esperat.insert(0, elements[idx])
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if pila.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    while len(resultat_esperat) > 0: # BUIDAR LA PILA FENT POPS
        if resultat_esperat[0] != pila.pop():
            raise MissatgeError, "La funció 'pop' retorna un valor no esperat."
        resultat_esperat.pop(0)
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if len(resultat_esperat) > 0:
            if pila.head() != resultat_esperat[0]:
                raise MissatgeError, "La funció 'head' desprès de fer un 'pop' retorna un valor no esperat."
        else:
            if not pila._StackList__llista.isEmpty():
                raise MissatgeError, "La llista de la pila no està buida desprès de fer un 'pop' de l'únic element de la pila."
    # Comprovar purge .............................................................................
    for idx in range(len(elements) / 2): # AFEGIR LA MEITAT
        pila.push(elements[idx])
        resultat_esperat.insert(0, elements[idx])
        comprovaValorsPila(StackList, pila, resultat_esperat)
        if pila.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    pila.purge()
    if not pila._StackList__llista.isEmpty():
        raise MissatgeError, "La llista de la pila no està buida desprès de fer un 'purge' de la pila."
    pila.purge()
    if not pila._StackList__llista.isEmpty():
        raise MissatgeError, "La llista de la pila no està buida desprès de fer un 'purge' de la pila buida."
    
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
        StackList = __import__(nom_arxiu)
        reload(StackList)
    except:
        print missatge("L'arxiu '%s' té errors de sintaxis:" % (arxiu))
        return
    
    # COMPROVAR EL NOM DE LES FUNCIONS MEMBRE .....................................................
    print " + Comprovant les funcions membre de la classe:",
    sys.stdout.flush()
    if not 'StackList' in dir(StackList):
        print "L'arxiu '%s' no conté la classe 'StackList'" % arxiu
        return
    try:
        comprovarFuncionsStackList(StackList.StackList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "La capçalera de la classe és incorrecta. No es pot continuar."
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE STACKLIST PER UNA PILA BUIDA ........................................
    print " + Comprovant la classe StackList buida:",
    sys.stdout.flush()
    try:
        pilaBuida(StackList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe StackList:")
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE STACKLIST PER UNA PILA AMB DIVERSOS ELEMENTS ........................
    print " + Comprovant la classe StackList afegint/eliminant múltiples elements:",
    sys.stdout.flush()
    try:
        pilaMultipleElements(StackList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe StackList:")
        return
    print "[CORRECTE]"

if __name__ == '__main__':
    jocproves("StackList.py")
