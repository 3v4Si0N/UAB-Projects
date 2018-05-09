# -*- coding: latin-1 -*-

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from LinkedList import *

class Tree(object):
    __slots__ = ['__root']
    class Node(object):
        __slots__ = ['__data', '__childs']
        def __init__(self, data):
            self.__childs=LinkedList() # inicializacion de la variable self.__childs como una lista vacia
            self.__data=data # inicializacion del self.__data como data

        
        def addChild(self, child):
            self.__childs.insertAfter(child) # insertamos un nodo

        
        def getChilds(self):
            return self.__childs # devuelve los hijos del nodo que se le pase

        
        def getData(self):
            return self.__data # devuelve el dato de un nodo

        
        def getChild(self, data):
            if (self.__childs.isEmpty()): # comprobamos si la lista está vacía
                return None # devuelve none si está vacía
            else:
                self.__childs.moveHead() # nos movemos a la cabeza de la lista
                while(self.__childs.getCurrent().getData()!=data): # recorremos la lista mediante un bucle comprobando si la data del nodo actual es diferente de la que buscamos, entonces que siga buscando
                    if (self.__childs.getCurrent()== self.__childs.getTail()): # comprobamos si hemos llegado al final de la lista
                        return None # devolvemos none si hemos llegado al final y no hemos encontrado nada
                    self.__childs.moveNext() # nos movemos al siguiente nodo
                return self.__childs.getCurrent() # cuando encontremos la data que queremos devolvemos el nodo

        
        def __str__(self):
            return self.str_recursive('', True)
        
        def str_recursive(self, prefix, final):
            if final:
                contingut = prefix + 'â””â”€â”€ ' + str(self.__data) + '\n'
                mascara =   prefix + '    '
            else:
                contingut = prefix + 'â”œâ”€â”€ ' + str(self.__data) + '\n'
                mascara =   prefix + 'â”‚   '
            if self.__childs.size() > 0:
                self.__childs.moveHead()
                for idx in range(self.__childs.size() - 1):
                    contingut += self.__childs.getCurrent().str_recursive(mascara, False)
                    self.__childs.moveNext()
                contingut += self.__childs.getCurrent().str_recursive(mascara, True)
            return contingut

        
    def __init__(self):
        self.__root = self.Node(None)
        
    def build(self, filename):
        f = open(filename, 'r')
        for linia in filter(lambda x: len(x.split()) == 18, f.readlines()):
            self.add(linia.split())
        f.close()
        
    def add(self, features):
        nodeAux = self.__root # creamos un nodo auxiliar para no perder la raiz del arbol
        for feature in features: # comprobamos mediante un bucle las caracteristicas
            if(nodeAux.getChild(feature) == None): # comprobamos si el hijo del nodo actual no nos devuelve una caracteristica
                nodeAux.addChild(self.Node(feature)) # si no nos devuelve nada agregamos el nodo correspondiente con su caracteristica correspondiente
                nodeAux = nodeAux.getChilds().getCurrent() # ahora el nodo que hemos creado es el actual
            else:
                nodeAux = nodeAux.getChild(feature) # si no nos devuelve none seguimos hacia delante
    
    def search(self, features):
        nodeAux = self.__root # creamos un nodo auxiliar para no perder la raiz del arbol
        for feature in features: # comprobamos mediante un bucle las caracteristicas
            if(nodeAux.getChild(feature) == None): # comprobamos si el hijo del nodo actual no nos devuelve una caracteristica y lanzamos una excepcion
                raise IndexError
            else:
                nodeAux = nodeAux.getChild(feature) # si no nos devuelve none seguimos hacia delante
        return nodeAux.getChilds().getCurrent().getData() # cuando lleguemos al final nos devolverá el dato del último nodo
    
    def __str__(self):
        contingut = 'ROOT\n'
        if self.__root.getChilds().size() > 0:
            self.__root.getChilds().moveHead()
            for idx in range(self.__root.getChilds().size() - 1):
                contingut += self.__root.getChilds().getCurrent().str_recursive('', False)
                self.__root.getChilds().moveNext()
            contingut += self.__root.getChilds().getCurrent().str_recursive('', True)
        return contingut 

