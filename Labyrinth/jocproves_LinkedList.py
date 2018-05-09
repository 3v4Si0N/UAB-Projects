# -*- coding: latin-1 -*-
import inspect, sys, traceback, os, types

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

def comprovarFuncionsLinkedList(objecte):
    object_methods = set(['__setattr__', '__reduce_ex__', '__new__', '__getattribute__', '__str__', '__format__', '__reduce__', '__class__', '__delattr__', '__subclasshook__', '__repr__', '__hash__', '__sizeof__', '__doc__', '__module__'])
    expected_linkedlist_methods = set(['Node', '_LinkedList__head', 'insertBefore', '_LinkedList__current', 'moveNext', '_LinkedList__tail', 'getHead', 'remove', 'moveHead', '__slots__', 'isEmpty', 'getCurrent', 'insertAfter', 'getTail', 'clear', '__init__', '__str__', '_LinkedList__size', 'size'])
    expected_node_methods = set(['getNext', 'setNext', '_Node__data', '__slots__', '_Node__next', 'getData', 'setData', '__init__', '__str__'])
    missatge = ''
    
    current_methods = set(dir(objecte)).difference(object_methods)
    extra_members = current_methods.difference(expected_linkedlist_methods)
    if len(extra_members) > 0:
        funcions = filter(lambda x: x.count('_LinkedList') == 0, list(extra_members))
        variables = filter(lambda x: x.count('_LinkedList') > 0, list(extra_members))
        if len(funcions) > 0:
            missatge += "     - La classe LinkedList té més funcions de les necessàries:", funcions
        if len(variables) > 0:
            missatge += "     - La classe LinkedList té més variables membre de les necessàries:", variables
    
    #  Comprovar LinkedList .......................................................................
    missatge += comprovaVariable(objecte, expected_linkedlist_methods, '_LinkedList__head')
    missatge += comprovaVariable(objecte, expected_linkedlist_methods, '_LinkedList__tail')
    missatge += comprovaVariable(objecte, expected_linkedlist_methods, '_LinkedList__current')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, '__init__')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, '__str__')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'getHead')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'getTail')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'getCurrent')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'moveNext')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'moveHead')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'isEmpty')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'clear')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'size')
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'insertBefore', ['data'])
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'insertAfter', ['data'])
    missatge += comprovaFuncio(objecte, expected_linkedlist_methods, 'remove')
    missatge += comprovaClasse(objecte, expected_linkedlist_methods, 'Node')
    #  Comprovar la classe imbricada Node .........................................................
    missatge += comprovaVariable(objecte.Node, expected_node_methods, '_Node__data')
    missatge += comprovaVariable(objecte.Node, expected_node_methods, '_Node__next')
    missatge += comprovaFuncio(objecte.Node, expected_node_methods, '__init__', ['data', 'next'])
    missatge += comprovaFuncio(objecte.Node, expected_node_methods, 'getData')
    missatge += comprovaFuncio(objecte.Node, expected_node_methods, 'setData', ['data'])
    missatge += comprovaFuncio(objecte.Node, expected_node_methods, 'getNext')
    missatge += comprovaFuncio(objecte.Node, expected_node_methods, 'setNext', ['next'])
    missatge += comprovaFuncio(objecte.Node, expected_node_methods, '__str__')
    
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

def llistaBuida(LinkedList):
    l = LinkedList.LinkedList()
    if l._LinkedList__head != None:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__head' a None."
    if l._LinkedList__tail != None:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__tail' a None."
    if l._LinkedList__current != None:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__current' a None."
    if l._LinkedList__size != 0:
        raise MissatgeError, "La funció __init__ ha d'inicialitzar la variable '__size' a 0."
    try:
        l.getHead()
        raise MissatgeError, "La funció 'getHead' ha de generar una excepció de tipus 'IndexError' quan la llista està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        l.getTail()
        raise MissatgeError, "La funció 'getTail' ha de generar una excepció de tipus 'IndexError' quan la llista està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        l.getCurrent()
        raise MissatgeError, "La funció 'getCurrent' ha de generar una excepció de tipus 'IndexError' quan la llista està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        l.moveNext()
        raise MissatgeError, "La funció 'moveNext' ha de generar una excepció de tipus 'IndexError' quan la llista està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        l.moveHead()
        raise MissatgeError, "La funció 'moveHead' ha de generar una excepció de tipus 'IndexError' quan la llista està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    try:
        l.remove()
        raise MissatgeError, "La funció 'remove' ha de generar una excepció de tipus 'IndexError' quan la llista està buida."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    
    if l.isEmpty() == False:
        raise MissatgeError, "La funció 'isEmpty' ha de retornar True quan la llista està buida."
    if type(str(l)) != types.StringType:
        raise MissatgeError, "La funció '__str__' sempre ha de tornar una cadena de caràcters."
    if l.size() != 0:
        raise MissatgeError, "La funció 'size' ha de retornar 0 quan la llista està buida."
    l.clear()

def llistaUnElement(LinkedList):
    l = LinkedList.LinkedList()
    l.insertAfter(17)
    if l._LinkedList__head != l._LinkedList__tail or l._LinkedList__head != l._LinkedList__current:
        raise MissatgeError, "La funció 'insertAfter' no funciona correctament: __head, __tail i __current han d'apuntar al mateix node quan s'insereix el primer element a la llista."
    if l._LinkedList__current.getData() != 17:
        raise MissatgeError, "La funció 'insertAfter' no funciona correctament: El node no guarda correctament les dades."
    if l._LinkedList__current.getNext() != None:
        raise MissatgeError, "La funció 'insertAfter' no funciona correctament: La variable __next del node ha de ser None."
    if l._LinkedList__size != 1:
        raise MissatgeError, "La funció 'insertAfter' no incrementa la variable __size correctament."
    
    if l.getHead() != 17:
        raise MissatgeError, "La funció 'getHead' no retorna el valor del node __head"
    if l.getTail() != 17:
        raise MissatgeError, "La funció 'getTail' no retorna el valor del node __tail"
    if l.getCurrent() != 17:
        raise MissatgeError, "La funció 'getCurrent' no retorna el valor del node __current"
    
    l.moveHead()
    if l._LinkedList__current != l._LinkedList__head:
        raise MissatgeError, "La funció 'moveHead' no mou correctament el __current a __head."
    try:
        l.moveNext()
        raise MissatgeError, "La funció 'moveNext' ha de llançar una excepció de tipus 'IndexError' quan el __current no es pot moure."
    except IndexError: pass
    except: raise MissatgeError, missatge("S'esperava una excepció de tipus 'IndexError' però s'ha rebut:")
    l.moveHead()
    if l._LinkedList__current != l._LinkedList__head:
        raise MissatgeError, "La funció 'moveNext' ha mogut el __current a un node incorrecte."
    
    if l.isEmpty():
        raise MissatgeError, "La funció 'isEmpty' ha de retornar False quan hi ha un element a la llista."
    if l.size() != 1:
        raise MissatgeError, "La funció 'size' ha de retornar el valor de '__size'."
    
    if type(l.__str__()) != types.StringType:
        raise MissatgeError, "La funció '__str__' sempre ha de tornar una cadena de caràcters."
    if l.__str__().count('17') != 1:
        raise MissatgeError, "La funció '__str__' ha de retornar una cadena de caràcters amb el contingut de la llista."
    
    l.remove()
    if l._LinkedList__head != None or l._LinkedList__tail != None or l._LinkedList__current != None:
        raise MissatgeError, "La funció 'remove' no esborra correctament l'últim element de la llista (tots els apuntadors han d'apuntar a None)."
    if l._LinkedList__size != 0:
        raise MissatgeError, "La funció 'remove' no decrementa correctament la mida de la llista (__size hauria de ser 0)."
    
    l.insertBefore(19)
    if l._LinkedList__head != l._LinkedList__tail or l._LinkedList__head != l._LinkedList__current:
        raise MissatgeError, "La funció 'insertBefore no funciona correctament: __head, __tail i __current han d'apuntar al mateix node quan s'insereix el primer element a la llista."
    if l._LinkedList__current.getData() != 19:
        raise MissatgeError, "La funció 'insertBefore' no funciona correctament: El node no guarda correctament les dades."
    if l._LinkedList__current.getNext() != None:
        raise MissatgeError, "La funció 'insertBefore' no funciona correctament: La variable __next del node ha de ser None."
    if l._LinkedList__size != 1:
        raise MissatgeError, "La funció 'insertBefore' no incrementa la variable __size correctament."
    
    l.clear()
    if l._LinkedList__head != None or l._LinkedList__tail != None or l._LinkedList__current != None:
        raise MissatgeError, "La funció 'clear' no esborra correctament els elements de la llista (tots els apuntadors han d'apuntar a None)."
    if l._LinkedList__size != 0:
        raise MissatgeError, "La funció 'clear' no posa la variable __size a 0."

def comprovaValorNode(LinkedList, node, valor):
    naux = LinkedList.LinkedList.Node(valor)
    return node == None or type(node) != type(naux) or node.getData() != valor

def cadenaOrdinal(n):
    if n == 0: return '1er'
    elif n == 1: return '2on'
    elif n == 3: return '4rt'
    else: return '%dé' % (n + 1)

def comprovaValorsLlista(LinkedList, llista, valors):
    if llista._LinkedList__size != len(valors):
        raise MissatgeError, "La llista hauria de tenir %d elements però en té %d" % (len(valors), llista._LinkedList__size)
    aux = llista._LinkedList__head
    for idx in range(len(valors)):
        if comprovaValorNode(LinkedList, aux, valors[idx]):
            raise MissatgeError, "El %s element de la llista hauria de ser %d però el node té un altre valor." % (cadenaOrdinal(idx), valors[idx])
        aux = aux.getNext()
        if idx < len(valors) - 1 and aux == None:
            raise MissatgeError, "S'esperaven més nodes però el __next del node apunta a None. La llista està tallada al %s element" % (cadenaOrdinal(idx))
    if aux != None:
        raise MissatgeError, "L'últim node de la llista ha d'apuntar a None."

def llistaMultipleElements(LinkedList):
    l = LinkedList.LinkedList()
    
    # Comprovar insertAfter inserint 3 elements ...................................................
    l.insertAfter(1)
    l.insertAfter(2)
    l.insertAfter(3)
    
    if l._LinkedList__size != 3:
        raise MissatgeError, "La funció 'insertAfter' no incrementa correctament la variable '__size'."
    if l._LinkedList__size != l.size():
        raise MissatgeError, "La funció 'size' no retorna el nombre correcte d'elements dins la llista."
    if comprovaValorNode(LinkedList, l._LinkedList__head, 1):
        raise MissatgeError, "La funció 'insertAfter' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, __head és incorrecte."
    if comprovaValorNode(LinkedList, l._LinkedList__head.getNext(), 2):
        raise MissatgeError, "La funció 'insertAfter' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, el segon element és incorrecte."
    if comprovaValorNode(LinkedList, l._LinkedList__head.getNext().getNext(), 3):
        raise MissatgeError, "La funció 'insertAfter' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, el tercer element és incorrecte."
    if l._LinkedList__head.getNext().getNext().getNext() != None:
        raise MissatgeError, "La funció 'insertAfter' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, l'últim node ha d'apuntar a None."
    if l._LinkedList__current != l._LinkedList__head.getNext().getNext():
        raise MissatgeError, "La funció 'insertAfter' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, __current no apunta al darrer element."
    if l._LinkedList__tail != l._LinkedList__head.getNext().getNext():
        raise MissatgeError, "La funció 'insertAfter' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, __tail no apunta al darrer element."
    
    # Comprovar la resta de funcions ara que a la llista hi han elements ..........................
    if l.getHead() != 1:
        raise MissatgeError, "La funció 'getHead' no retorna el valor de __head."
    if l.getTail() != 3:
        raise MissatgeError, "La funció 'getTail' no retorna el valor de __tail."
    if l.isEmpty():
        raise MissatgeError, "La funció 'isEmpty' ha de retorna False quan a la llista hi han elements."
    
    cadena = l.__str__()
    if cadena.count('1') == 0 or cadena.count('2') == 0 or cadena.count('3') == 0:
        raise MissatgeError, "La funció '__str__' no retorna una cadena de caràcters amb el contingut de tots els nodes."
    if cadena.index('1') > cadena.index('2') or cadena.index('1') > cadena.index('3') or cadena.index('2') > cadena.index('3'):
        raise MissatgeError, "La funció '__str__' no retorna una cadena de caràcters amb el continguts del tots els nodes amb l'ordre correcte."
    if len(cadena) == 3:
        raise MissatgeError, "La funció '__str__' no retorna una cadena de caràcters on es pot diferenciar el contingut dels diferents nodes."
    
    l.moveHead()
    if l._LinkedList__current != l._LinkedList__head:
        raise MissatgeError, "La funció 'moveHead' no mou el __current al __head de la llista."
    if l.getCurrent() != 1:
        raise MissatgeError, "La funció 'getCurrent' no retorna el valor de __current."
    l.moveNext()
    if l._LinkedList__current != l._LinkedList__head.getNext():
        raise MissatgeError, "La funció 'moveNext' no mou el __current al següent node de la llista."
    if l.getCurrent() != 2:
        raise MissatgeError, "La funció 'getCurrent' no retorna el valor de __current."
    l.moveNext()
    if l._LinkedList__current != l._LinkedList__head.getNext().getNext():
        raise MissatgeError, "La funció 'moveNext' no mou el __current al següent node de la llista."
    if l.getCurrent() != 3:
        raise MissatgeError, "La funció 'getCurrent' no retorna el valor de __current."
    try:
        l.moveNext()
        raise MissatgeError, "La funció 'moveNext' ha de generar una excepció de tipus IndexError quan el __current no es pot moure més."
    except IndexError: pass
    
    l.clear()
    if l._LinkedList__head != None or l._LinkedList__tail != None or l._LinkedList__current != None:
        raise MissatgeError, "La funció 'clear' no esborra correctament els elements de la llista (tots els apuntadors han d'apuntar a None)."
    if l._LinkedList__size != 0:
        raise MissatgeError, "La funció 'clear' no posa la variable __size a 0."
    
    # Comprovar insertBefore inserint 3 elements ..................................................
    l.insertBefore(1)
    l.insertBefore(2)
    l.insertBefore(3)
    
    if l._LinkedList__size != 3:
        raise MissatgeError, "La funció 'insertBefore' no incrementa correctament la variable '__size'."
    if comprovaValorNode(LinkedList, l._LinkedList__head, 3):
        raise MissatgeError, "La funció 'insertBefore' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, __head és incorrecte."
    if comprovaValorNode(LinkedList, l._LinkedList__head.getNext(), 2):
        raise MissatgeError, "La funció 'insertBefore' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, el segon element és incorrecte."
    if comprovaValorNode(LinkedList, l._LinkedList__head.getNext().getNext(), 1):
        raise MissatgeError, "La funció 'insertBefore' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, el tercer element és incorrecte."
    if l._LinkedList__head.getNext().getNext().getNext() != None:
        raise MissatgeError, "La funció 'insertBefore' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, l'últim node ha d'apuntar a None."
    if l._LinkedList__current != l._LinkedList__head:
        raise MissatgeError, "La funció 'insertBefore' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, __current no apunta al primer element."
    if l._LinkedList__tail != l._LinkedList__head.getNext().getNext():
        raise MissatgeError, "La funció 'insertBefore' no afegeix correctament les dades a la llista. Després d'afegir 3 elements, __tail no apunta al darrer element."
    l.clear()
    
    # Comprovar insertAfter i insertBefore pel cas general ........................................
    l.insertAfter(1)
    l.insertAfter(2)
    l.moveHead()
    l.insertBefore(3)
    try:
        comprovaValorsLlista(LinkedList, l, [3, 1, 2])
    except MissatgeError, (exp):
        raise MissatgeError, "La funció 'insertBefore' ha fallat desprès d'afegir un element quan el __current està al __head:\n%s" % exp._missatge
    l.moveNext()
    l.insertAfter(4) # Llista esperada: 3, 1, 4, 2
    try:
        comprovaValorsLlista(LinkedList, l, [3, 1, 4, 2])
    except MissatgeError, (exp):
        raise MissatgeError, "La funció 'insertAfter' ha fallat al afegir un element al mig de la llista:\n%s" % exp._missatge
    if l._LinkedList__current != l._LinkedList__head.getNext().getNext():
        raise MissatgeError, "La funció 'insertAfter' ha fallat. El __current no apunta al node correcte quan s'afegeixen elements al mig d'una llista."
    l.insertBefore(5) # Llista esperada: 3, 1, 5, 4, 2
    try:
        comprovaValorsLlista(LinkedList, l, [3, 1, 5, 4, 2])
    except MissatgeError, (exp):
        raise MissatgeError, "La funció 'insertBefore' ha fallat al afegir un element al mig de la llista:\n%s" % exp._missatge
    if l._LinkedList__current != l._LinkedList__head.getNext().getNext():
        raise MissatgeError, "La funció 'insertAfter' ha fallat. El __current no apunta al node correcte quan s'afegeixen elements al mig d'una llista."
    
    # Comprovar la funció remove .................................................................
    l.moveHead()
    l.moveNext()
    l.moveNext()
    l.remove() # Esborra el 5
    try:
        comprovaValorsLlista(LinkedList, l, [3, 1, 4, 2])
    except MissatgeError, (exp):
        raise MissatgeError, "La funció 'remove' ha fallat al esborrar l'element del mig de la llista:\n%s" % exp._missatge
    if l._LinkedList__current != l._LinkedList__head.getNext():
        raise MissatgeError, "La funció 'remove' ha fallat. El __current no apunta al node correcte quan s'eliminen elements al mig d'una llista."
    l.moveNext()
    l.moveNext()
    l.remove() # Esborra el 2
    try:
        comprovaValorsLlista(LinkedList, l, [3, 1, 4])
    except MissatgeError, (exp):
        raise MissatgeError, "La funció 'remove' ha fallat al esborrar l'últim element de la llista:\n%s" % exp._missatge
    if l._LinkedList__current != l._LinkedList__tail:
        raise MissatgeError, "La funció 'remove' ha fallat. El __current no apunta al node correcte quan s'elimina l'últim element de la llista."
    l.moveHead()
    l.remove() # Esborra el 3
    try:
        comprovaValorsLlista(LinkedList, l, [1, 4])
    except MissatgeError, (exp):
        raise MissatgeError, "La funció 'remove' ha fallat al esborrar el primer element de la llista:\n%s" % exp._missatge
    if l._LinkedList__current != l._LinkedList__head:
        raise MissatgeError, "La funció 'remove' ha fallat. El __current no apunta al node correcte quan s'elimina el primer de la llista."

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
        LinkedList = __import__(nom_arxiu)
        reload(LinkedList)
    except:
        print missatge("L'arxiu '%s' té errors de sintaxis:" % (arxiu))
        return
    
    # COMPROVAR EL NOM DE LES FUNCIONS MEMBRE .....................................................
    print " + Comprovant les funcions membre de la classe:",
    sys.stdout.flush()
    if not 'LinkedList' in dir(LinkedList):
        print "L'arxiu '%s' no conté la classe 'LinkedList'" % arxiu
        return
    try:
        comprovarFuncionsLinkedList(LinkedList.LinkedList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "La capçalera de la classe és incorrecta. No es pot continuar."
        return
    print "[CORRECTE]"
    
    # AVALUAR LA CLASSE NODE ......................................................................
    print " + Comprovant la classe imbricada Node:",
    try:
        comprovaNode(LinkedList.LinkedList.Node)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe Node:")
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE LINKEDLIST PER UNA LLISTA BUIDA .....................................
    print " + Comprovant la classe LinkedList buida:",
    sys.stdout.flush()
    try:
        llistaBuida(LinkedList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe LinkedList:")
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE LINKEDLIST PER UNA LLISTA D'UN ÚNIC ELEMENT .........................
    print " + Comprovant la classe LinkedList amb un element:",
    sys.stdout.flush()
    try:
        llistaUnElement(LinkedList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe LinkedList:")
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS DE LINKEDLIST PER UNA LLISTA D'UN ÚNIC ELEMENT .........................
    print " + Comprovant la classe LinkedList amb més d'un element:",
    sys.stdout.flush()
    try:
        llistaMultipleElements(LinkedList)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe LinkedList:")
        return
    print "[CORRECTE]"

if __name__ == '__main__':
    jocproves("LinkedList.py")
