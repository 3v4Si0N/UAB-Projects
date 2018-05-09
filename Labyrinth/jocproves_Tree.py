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

def comprovarObjectesClasse(objecte, expected_variables, expected_methods, expected_classes, expected_other):
    object_methods = set(['__setattr__', '__reduce_ex__', '__new__', '__getattribute__', '__str__', '__format__', '__reduce__', '__class__', '__delattr__', '__subclasshook__', '__repr__', '__hash__', '__sizeof__', '__doc__', '__module__'])
    expected_linkedlist_methods = set(expected_variables + map(operator.itemgetter(0), expected_methods) + expected_classes + expected_other)
    missatge = ''
    
    current_methods = set(dir(objecte)).difference(object_methods)
    extra_members = current_methods.difference(expected_linkedlist_methods)
    if len(extra_members) > 0:
        funcions = filter(lambda x: x.count('_Tree') == 0, list(extra_members))
        variables = filter(lambda x: x.count('_Tree') > 0, list(extra_members))
        if len(funcions) > 0:
            missatge += "     - La classe Tree té més funcions de les necessàries: " + ', '.join(funcions)
        if len(variables) > 0:
            missatge += "     - La classe Tree té més variables membre de les necessàries: " + ', '.join(variables)
    
    #  Comprovar variables, mètodes i classes imbricades ..........................................
    for nom_variable in expected_variables:
        missatge += comprovaVariable(objecte, expected_linkedlist_methods, nom_variable)
    for nom_metode, parametres_metode in expected_methods:
        missatge += comprovaFuncio(objecte, expected_linkedlist_methods, nom_metode, parametres_metode)
    for nom_objecte in expected_classes:
        missatge += comprovaClasse(objecte, expected_linkedlist_methods, nom_objecte)
    
    if len(missatge) > 0:
        raise MissatgeError, missatge

def comprovarFuncionsTree(objecte):
    # Variables ...............................................................
    expected_variables_tree = ['_Tree__root']
    expected_variables_node = ['_Node__data', '_Node__childs']
    # Mètodes .................................................................
    expected_methods_tree  = []
    expected_methods_tree += [('__init__', [])]
    expected_methods_tree += [('build', ['filename'])]
    expected_methods_tree += [('add', ['features'])]
    expected_methods_tree += [('search', ['features'])]
    expected_methods_tree += [('__str__', [])]
    expected_methods_node  = []
    expected_methods_node += [('__init__', ['data'])]
    expected_methods_node += [('addChild', ['child'])]
    expected_methods_node += [('getChilds', [])]
    expected_methods_node += [('getData', [])]
    expected_methods_node += [('getChild', ['data'])]
    expected_methods_node += [('__str__', [])]
    expected_methods_node += [('str_recursive', ['prefix', 'final'])]
    # Classes .................................................................
    expected_classes_tree = ['Node']
    expected_classes_node = []
    # Altres objectes de Tree .................................................
    expected_other = ['__slots__']
    
    comprovarObjectesClasse(objecte, expected_variables_tree, expected_methods_tree, expected_classes_tree, expected_other)
    comprovarObjectesClasse(objecte.Node, expected_variables_node, expected_methods_node, expected_classes_node, expected_other)

def cercarNode(Tree, arbre, caracteristiques, resultat):
    cerca = arbre.search(caracteristiques)
    if cerca != resultat:
        msg = "Al crear un arbre amb un únic element la funció 'search' no retorna el contingut esperat. La funció 'search' ha de retornar el CONTINGUT de la FULLA, "
        if type(cerca) == type(Tree.Tree.Node(1)):
            raise MissatgeError, msg + "no un node de l'arbre."
        elif cerca == caracteristiques[-1]:
            raise MissatgeError, msg + "no el contingut de l'últim node que s'accedeix amb el vector de característiques."
        elif type(cerca) == type(Tree.LinkedList()):
            raise MissatgeError, msg + "no la llista de nodes (no ha de retornar getChilds())."
        else:
            raise MissatgeError, msg + "no retorna el contingut del node esperat."
    try:                            # Comprovar amb un vector que el primer element està a l'arbre però el segon no.
        arbre.search([caracteristiques[0], None])
        raise MissatgeError, "Al cercar un vector de característiques que no es present a l'arbre, la funció 'search' ha de generar una excepció de tipus IndexError."
    except IndexError: pass
    except:
        raise MissatgeError, missatge("Al cercar un vector de característiques que no es present a l'arbre, la funció 'search' ha de generar una excepció de tipus IndexError. Enlloc d'aquesta excepció s'ha generat:")
    try:                            # Comprovar amb un vector que cap element està a l'arbre.
        arbre.search([None, None])
        raise MissatgeError, "Al cercar un vector de característiques que no es present a l'arbre, la funció 'search' ha de generar una excepció de tipus IndexError."
    except IndexError: pass
    except:
        raise MissatgeError, missatge("Al cercar un vector de característiques que no es present a l'arbre, la funció 'search' ha de generar una excepció de tipus IndexError. Enlloc d'aquesta excepció s'ha generat:")

def comprovaNode(Tree):
    # COMPROVAR NODE BUIT .........................................................................
    c = 'adflkajs'
    node = Tree.Tree.Node(c)
    if node._Node__data != c:
        raise MissatgeError, "El constructor del Node (funció __init__) ha d'inicialitzar la variable '__data' al contingut del paràmetre 'data'."
    if type(node._Node__childs) != type(Tree.LinkedList()):
        raise MissatgeError, "El constructor del Node (funció __init__) ha d'inicialitzar la variable '__childs' com una llista LinkedList."
    if node._Node__childs.size() != 0:
        raise MissatgeError, "El constructor del Node (funció __init__) ha d'inicialitzar la variable '__childs' com una llista buida."
    if node.getData() != c:
        raise MissatgeError, "La funció 'getData' de node ha de retornar el valor de '__data'."
    if type(node.getChilds()) != type(Tree.LinkedList()):
        raise MissatgeError, "La funció 'getChilds' ha de retornar una llista LinkedList."
    if node.getChilds().size() != 0:
        raise MissatgeError, "La llista que retorna 'getChilds' hauria d'estar buida (node buit) però té elements."
    try:
        if node.getChild(c) != None:
            raise MissatgeError, "La funció 'getChild' d'un node buit hauria de retornar 'None' (no pot trobar cap fill que tingui l'identificador seleccionat)."
    except:
        raise MissatgeError, missatge("La funció 'getChild' ha generat una excepció inesperada:")
    # AFEGIR UN FILL AL NODE ......................................................................
    n1 = Tree.Tree.Node('1er')
    node.addChild(n1)
    
    if node._Node__data != c or type(node._Node__childs) != type(Tree.LinkedList()):
        raise MissatgeError, "Després d'afegir un node (funció 'addChild') el node ha canviat el seu contingut."
    if n1._Node__data != '1er' or type(n1._Node__childs) != type(Tree.LinkedList()) or n1._Node__childs.size() != 0:
        raise MissatgeError, "Després d'afegir un node (funció 'addChild') el node afegit ha canviat el seu contingut."
    if node._Node__childs.size() != 1:
        raise MissatgeError, "La funció 'addChild' no ha incrementat el nombre d'elements de la llista '__childs'."
    if node._Node__childs.getHead() != n1:
        raise MissatgeError, "La funció 'addChild' no ha afegit el nou node a la llista '__childs'."
    if node.getChilds() != node._Node__childs:
        raise MissatgeError, "La funció 'getChilds' ha de retornar la llista '__childs'."
    
    try:
        if node.getChild('1er') != n1:
            raise MissatgeError, "La funció 'getChild' no retorna el node afegit amb 'addChild'."
        if node.getChild(c) != None:
            raise MissatgeError, "La funció 'getChild' no ha retornat None que s'ha buscat un fill no existent."
    except MissatgeError: raise
    except:
        raise MissatgeError, missatge("La funció 'getChild' ha generat una excepció inesperada:")
    
    # AFEGIR UN SEGON FILL AL NODE ................................................................
    n2 = Tree.Tree.Node('2on')
    node.addChild(n2)
    if node._Node__data != c or type(node._Node__childs) != type(Tree.LinkedList()):
        raise MissatgeError, "Després d'afegir un node (funció 'addChild') el node ha canviat el seu contingut."
    if n2._Node__data != '2on' or type(n2._Node__childs) != type(Tree.LinkedList()) or n2._Node__childs.size() != 0:
        raise MissatgeError, "Després d'afegir un node (funció 'addChild') el node afegit ha canviat el seu contingut."
    if n1._Node__data != '1er' or type(n1._Node__childs) != type(Tree.LinkedList()) or n1._Node__childs.size() != 0:
        raise MissatgeError, "Després d'afegir un node (funció 'addChild') el node afegit ha canviat del node afegit anteriorment."
    if node._Node__childs.size() != 2:
        raise MissatgeError, "La funció 'addChild' no ha incrementat el nombre d'elements de la llista '__childs'."
    if node.getChilds() != node._Node__childs:
        raise MissatgeError, "La funció 'getChilds' ha de retornar la llista '__childs'."
    if not ((node.getChilds().getHead() == n1 and node.getChilds().getTail() == n2) or (node.getChilds().getHead() == n2 and node.getChilds().getTail() == n1)):
        raise MissatgeError, "La funció 'addChild' no ha afegit correctament un segon node a '__childs'."
    
    try:
        if node.getChild('1er') != n1:
            raise MissatgeError, "La funció 'getChild' no retorna el primer node afegit amb 'addChild'."
        if node.getChild('2on') != n2:
            raise MissatgeError, "La funció 'getChild' no retorna el segon node afegit amb 'addChild'."
        if node.getChild(c) != None:
            raise MissatgeError, "La funció 'getChild' no ha retornat None que s'ha buscat un fill no existent."
    except MissatgeError: raise
    except:
        raise MissatgeError, missatge("La funció 'getChild' ha generat una excepció inesperada:")
    
def comprovaTree(Tree):
    # Arbre test:
    # ROOT
    # |-- A
    # |   |-- a -- 17
    # |   +-- b -- 29
    # +-- B
    #     |-- a -- 31
    #     +-- b -- 37
    #
    # Vectors de característiques:
    # ['A', 'a', 17], ['A', 'b', 19], ['B', 'a', 31], ['B', 'b', 37]
    arbre = Tree.Tree() # Crear l'arbre buit.
    
    # Comprovar les funcions per afegir elements a l'arbre ........................................
    arbre.add(['A', 'a', 17])               # Afegir un element.
    cercarNode(Tree, arbre, ['A', 'a'], 17) # Comprovar que es pot accedir al node.
    
    arbre.add(['A', 'b', 19])               # Afegir el segon element.
    cercarNode(Tree, arbre, ['A', 'b'], 19) # Comprovar que es pot accedir al nou node.
    cercarNode(Tree, arbre, ['A', 'a'], 17) # Comprovar que es pot accedir al node anterior.
    
    arbre.add(['B', 'a', 31])               # Afegir la resta d'elements.
    arbre.add(['B', 'b', 37])
    cercarNode(Tree, arbre, ['A', 'a'], 17) # Comprovar que es pot accedir a tots els nodes.
    cercarNode(Tree, arbre, ['A', 'b'], 19)
    cercarNode(Tree, arbre, ['B', 'a'], 31)
    cercarNode(Tree, arbre, ['B', 'b'], 37)
    
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
        Tree = __import__(nom_arxiu)
        reload(Tree)
    except:
        print missatge("L'arxiu '%s' té errors de sintaxis:" % (arxiu))
        return
    
    # COMPROVAR EL NOM DE LES FUNCIONS MEMBRE .....................................................
    print " + Comprovant les funcions membre de la classe:",
    sys.stdout.flush()
    if not 'Tree' in dir(Tree):
        print "L'arxiu '%s' no conté la classe 'Tree'" % arxiu
        return
    try:
        comprovarFuncionsTree(Tree.Tree)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "La capçalera de la classe és incorrecta. No es pot continuar."
        return
    print "[CORRECTE]"
    
    # COMPROVAR QUE EL NODE ESTIGUI BEN IMPLEMENTAT ...............................................
    print " + Comprovant la classe imbricada Node de l'arbre:",
    sys.stdout.flush()
    try:
        comprovaNode(Tree)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe Node de Tree:")
        return
    print "[CORRECTE]"
    
    # AVALUAR LES FUNCIONS PER AFEGIR I BUSCAR ELEMENTS A L'ARBRE .................................
    print " + Comprovant la classe Tree afegint i buscant elements a l'arbre:",
    sys.stdout.flush()
    try:
        comprovaTree(Tree)
    except MissatgeError, (exp):
        print "[FALLA]"
        print exp._missatge
        print "No es pot continuar."
        return
    except:
        print "[FALLA]"
        print missatge("S'ha produït un al avaluar la classe Tree:")
        return
    print "[CORRECTE]"

if __name__ == '__main__':
    jocproves("Tree.py")
