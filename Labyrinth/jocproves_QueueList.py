# -*- coding: latin-1 -*-
import inspect, sys, traceback, os, types, operator

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

def missatge(text):
    msg = text + '\n'
    msg += '--------------------------------------------------------------------\n'
    msg += traceback.format_exc()
    return msg

class MissatgeError(Exception):
    def __init__(self, missatge):
        self._missatge = missatge

def comprovarFuncionsQueueList(objecte):
    object_methods = set(['__setattr__', '__reduce_ex__', '__new__', '__getattribute__', '__str__', '__format__', '__reduce__', '__class__', '__delattr__', '__subclasshook__', '__repr__', '__hash__', '__sizeof__', '__doc__', '__module__'])
    expected_variables = ['_LinkedList__head', '_LinkedList__current', '_LinkedList__tail', '_LinkedList__size']
    # Mètodes de LinkedList ...................................................
    expected_methods  = []
    expected_methods += [('__init__', [])]
    expected_methods += [('getHead', [])]
    expected_methods += [('getTail', [])]
    expected_methods += [('getCurrent', [])]
    expected_methods += [('moveHead', [])]
    expected_methods += [('moveNext', [])]
    expected_methods += [('isEmpty', [])]
    expected_methods += [('clear', [])]
    expected_methods += [('size', [])]
    expected_methods += [('insertAfter', ['data'])]
    expected_methods += [('insertBefore', ['data'])]
    expected_methods += [('remove', [])]
    expected_methods += [('__str__', [])]
    # Mètodes de QueueList ....................................................
    expected_methods += [('push', ['data'])]
    expected_methods += [('pop', [])]
    expected_methods += [('head', [])]
    expected_methods += [('purge', [])]
    expected_methods += [('__len__', [])]
    expected_classes = ['Node']
    expected_other = ['__slots__']
    expected_other += ['__dict__', '__weakref__'] # Herència.
    expected_linkedlist_methods = set(expected_variables + map(operator.itemgetter(0), expected_methods) + expected_classes + expected_other)
    missatge = ''
    
    current_methods = set(dir(objecte)).difference(object_methods)
    extra_members = current_methods.difference(expected_linkedlist_methods)
    if len(extra_members) > 0:
        funcions = filter(lambda x: x.count('_QueueList') == 0, list(extra_members))
        variables = filter(lambda x: x.count('_QueueList') > 0, list(extra_members))
        if len(funcions) > 0:
            missatge += "     - La classe QueueList té més funcions de les necessàries: " + ', '.join(funcions)
        if len(variables) > 0:
            missatge += "     - La classe QueueList té més variables membre de les necessàries: " + ', '.join(variables)
    
    #  Comprovar variables, mètodes i classes imbricades ..........................................
    for nom_variable in expected_variables:
        missatge += comprovaVariable(objecte, expected_linkedlist_methods, nom_variable)
    for nom_metode, parametres_metode in expected_methods:
        missatge += comprovaFuncio(objecte, expected_linkedlist_methods, nom_metode, parametres_metode)
    for nom_objecte in expected_classes:
        missatge += comprovaClasse(objecte, expected_linkedlist_methods, nom_objecte)
    
    if len(missatge) > 0:
        raise MissatgeError, missatge

def comprovaNode(objecte):
    n0 = objecte(17)
    n1 = objecte(12, n0)
    if n0._Node__data != 17 or n1._Node__data != 12:
        raise MissatgeError, "La funció __init__ no inicialitza correctament la variable '__data'"
    if n0._Node__next != None or n1._Node__next != n0:
        raise MissatgeError, "La funció __init__ no inicialitza correctament la variable '__next'"
    if n0.getData() != n0._Node__data or n1.getData() != n1._Node__data:
        raise MissatgeError, "El valor que retorna la funció getData(self) no és correcte. La funció ha de retornar la variable membre __data."
    if n0._Node__data != 17 or n1._Node__data != 12 or n0._Node__next != None or n1._Node__next != n0:
        raise MissatgeError, "Les dades dels nodes han canviat al cridar 'getData(self)'."
    if n0.getNext() != n0._Node__next or n1.getNext() != n1._Node__next:
        raise MissatgeError, "El valor que retorna la funció getNext(self) no és correcte. La funció ha de retornar la variable membre __next."
    if n0._Node__data != 17 or n1._Node__data != 12 or n0._Node__next != None or n1._Node__next != n0:
        raise MissatgeError, "Les dades dels nodes han canviat al cridar 'getNext(self)'."
    if type(str(n0)) != types.StringType or type(n1.__str__()) != types.StringType:
        raise MissatgeError, "La funció __str__ ha de retornar una cadena de caràcters."
    if n0._Node__data != 17 or n1._Node__data != 12 or n0._Node__next != None or n1._Node__next != n0:
        raise MissatgeError, "Les dades dels nodes han canviat al cridar '__str__(self)'."
    
    n0.setNext(n1)
    if n0._Node__next != n1:
        raise MissatgeError, "La funció setNext(self, next) ha d'assignar el paràmetre 'next' a '__next'."
    if n0._Node__data != 17:
        raise MissatgeError, "La funció setNext(self, next) no ha de canviar el valor de '__data'."
    n0.setData(19)
    if n0._Node__data != 19:
        raise MissatgeError, "La funció setData(self, data) ha d'assignar el paràmetre 'data' a '__data'."
    if n0._Node__next != n1:
        raise MissatgeError, "La funció setData(self, data) no ha de canviar el valor de '__next'."


def cuaBuida(QueueList):
    l = QueueList.QueueList()
    if l._LinkedList__head != None:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__head' a None."
    if l._LinkedList__tail != None:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__tail' a None."
    if l._LinkedList__current != None:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__current' a None."
    if l._LinkedList__size != 0:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__size' a 0."
    try:
        l.head()
        raise MissatgeError, "La funció 'head' ha de generar una excepció de tipus 'IndexError' quan la cua està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        l.pop()
        raise MissatgeError, "La funció 'pop' ha de generar una excepció de tipus 'IndexError' quan la cua està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    
    if len(l) != 0:
        raise MissatgeError, "La funció 'size' ha de retornar 0 quan la cua està buida."
    l.purge()

def comprovaValorNode(QueueList, node, valor):
    naux = QueueList.LinkedList.Node(valor)
    return node == None or type(node) != type(naux) or node.getData() != valor

def cadenaOrdinal(n):
    if n == 0: return '1er'
    elif n == 1: return '2on'
    elif n == 3: return '4rt'
    else: return '%dé' % (n + 1)

def comprovaValorsCua(QueueList, cua, valors):
    N = len(valors)
    if len(cua) != N:
        raise MissatgeError, "La funció '__len__' no retorna el número d'elements de la cua (retorna '%s' però hauria de retornar '%d'). Possible error a les funcions '__len__' o 'push'." % (str(len(cua)), N)
    if cua._LinkedList__size != N:
        raise MissatgeError, "La variable '__size' no s'actualitza correctament al fer un 'push' (hauria de ser '%d' però és '%s')." % (N, str(cua._LinkedList__size))
    aux = cua._LinkedList__head
    for idx in range(len(valors)):
        if comprovaValorNode(QueueList, aux, valors[idx]):
            raise MissatgeError, "El %s element de la cua hauria de ser %d però el node té un altre valor" % (cadenaOrdinal(idx), valors[idx])
        aux = aux.getNext()
        if idx < N - 1 and aux == None:
            raise MissatgeError, "S'esperaven més nodes però el '__next' del node apunta a None. La cua està tallada al %s element" % (cadenaOrdinal(idx))
    if aux != None:
        raise MissatgeError, "L'últim node de la cua ha d'apuntar a None."

def comprovaCuaBuida(cua, missatge = ''):
    if cua._LinkedList__head != None:
        raise MissatgeError, "%sLa variable '__head' d'una cua buida ha de ser None." % missatge
    if cua._LinkedList__tail != None:
        raise MissatgeError, "%sLa variable '__tail' d'una cua buida ha de ser None." % missatge
    if cua._LinkedList__current != None:
        raise MissatgeError, "%sLa variable '__current' d'una cua buida ha de ser None." % missatge
    if cua._LinkedList__size != 0:
        raise MissatgeError, "%sLa variable '__size' d'una cua buida ha de ser 0." % missatge

def cuaMultipleElements(QueueList):
    elements = [20, 17, 7, 19, 23, 17, 5, 8, 9]
    q = QueueList.QueueList()
    # Afegint un element a la cua .................................................................
    q.push(elements[0])
    comprovaValorsCua(QueueList, q, [elements[0]])
    if q.head() != elements[0]:
        raise MissatgeError, "Al afegir un únic element a la cua, la funció 'head' no retorna el valor d'aquest element."
    if q.pop() != elements[0]:
        raise MissatgeError, "La funció 'pop' no ha retornat el valor de l'element eliminat de la cua."
    try:
        q.pop()
        raise MissatgeError, "La funció 'pop' ha de genera una excepció de tipus IndexError quan la cua està buida."
    except IndexError: pass
    except MissatgeError: raise
    except: raise MissatgeError, "La funció 'pop' ha fet un pop d'una cua buida ha generat una excepció de tipus no esperat."
    if len(q) != 0 or q._LinkedList__size != 0:
        raise MissatgeError, "Desprès de fer un 'pop' de l'últim element la mida de la cua hauria de ser 0 (la funció '__len__' falla o la variable '__size no s'actualitza correctament)."
    comprovaCuaBuida(q, "Desprès de fer un 'pop' de l'únic element de la cua: ")
    
    q.push(elements[0])
    q.purge()
    if len(q) != 0 or q._LinkedList__size != 0:
        raise MissatgeError, "Desprès de fer un 'purge' la mida de la cua ha de ser 0 (la funció '__len__' falla o la variable '__size no s'actualitza correctament)."
    comprovaCuaBuida(q, "Desprès de fer un 'purge' de l'únic element de la cua: ")
    
    # Afegint la meitat dels elements .............................................................
    resultat_esperat = []
    for idx in range(len(elements) / 2):
        q.push(elements[idx])
        resultat_esperat.append(elements[idx])
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if q.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    while len(resultat_esperat) > 0:
        if resultat_esperat[0] != q.pop():
            raise MissatgeError, "La funció 'pop' retorna un valor no esperat."
        resultat_esperat.pop(0)
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if len(resultat_esperat) > 0:
            if q.head() != resultat_esperat[0]:
                raise MissatgeError, "La funció 'head' desprès de fer un 'pop' retorna un valor no esperat."
        else:
            comprovaCuaBuida(q, "Desprès de fer un 'pop' de l'últim element de la cua: ")
    
    # Afegir-ne la meitat, treure'n un terç i afegir la resta ........................................
    for idx in range(len(elements) / 2): # AFEGIR LA MEITAT
        q.push(elements[idx])
        resultat_esperat.append(elements[idx])
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if q.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    for idx in range(len(elements) / 3): # TREURE'N UN TERÇ
        if resultat_esperat[0] != q.pop():
            raise MissatgeError, "La funció 'pop' retorna un valor no esperat."
        resultat_esperat.pop(0)
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if q.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'pop' retorna un valor no esperat."
    for idx in range(len(elements) / 2, len(elements)): # AFEGIR LA RESTA
        q.push(elements[idx])
        resultat_esperat.append(elements[idx])
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if q.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    while len(resultat_esperat) > 0: # BUIDAR LA CUA FENT POPS
        if resultat_esperat[0] != q.pop():
            raise MissatgeError, "La funció 'pop' retorna un valor no esperat."
        resultat_esperat.pop(0)
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if len(resultat_esperat) > 0:
            if q.head() != resultat_esperat[0]:
                raise MissatgeError, "La funció 'head' desprès de fer un 'pop' retorna un valor no esperat."
        else:
            comprovaCuaBuida(q, "Desprès de fer un 'pop' de l'últim element de la cua: ")
    # Comprovar purge .............................................................................
    for idx in range(len(elements) / 2): # AFEGIR LA MEITAT
        q.push(elements[idx])
        resultat_esperat.append(elements[idx])
        comprovaValorsCua(QueueList, q, resultat_esperat)
        if q.head() != resultat_esperat[0]:
            raise MissatgeError, "La funció 'head' desprès de fer un 'push' retorna un valor no esperat."
    q.purge()
    comprovaCuaBuida(q, "Desprès de fer un 'purge' d'una cua: ")
    q.purge()
    comprovaCuaBuida(q, "Desprès de fer un 'purge' d'una cua buida: ")
    
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
        QueueList = __import__(nom_arxiu)
        reload(QueueList)
    except:
        print missatge("L'arxiu '%s' té errors de sintaxis:" % (arxiu))
        return
    
    # COMPROVAR EL NOM DE LES FUNCIONS MEMBRE .....................................................
    print " + Comprovant les funcions membre de la classe:",
    sys.stdout.flush()
    if not 'QueueList' in dir(QueueList):
        print "L'arxiu '%s' no conté la classe 'QueueList'" % arxiu
        return
    try:
        comprovarFuncionsQueueList(QueueList.QueueList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "La capçalera de la classe és incorrecta. No es pot continuar."
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE QUEUELIST PER UNA CUA BUIDA .........................................
    print " + Comprovant la classe QueueList buida:",
    sys.stdout.flush()
    try:
        cuaBuida(QueueList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe QueueList:")
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE QUEUELIST PER UNA CUA AMB DIVERSOS ELEMENTS .........................
    print " + Comprovant la classe QueueList afegint/eliminant múltiples elements:",
    sys.stdout.flush()
    try:
        cuaMultipleElements(QueueList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe QueueList:")
        return
    print "[CORRECTE]"

if __name__ == '__main__':
    jocproves("QueueList.py")
