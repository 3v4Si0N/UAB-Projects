# -*- coding: latin-1 -*-

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from LinkedList import *

class StackList(object):
    __slots__ = ['__llista']
    def __init__(self):
        super(StackList, self).__init__() # inicializacion
        self.__llista=LinkedList()
        
    def push(self, data):
        self.__llista.insertBefore(data) # insertamos al principio de la pila
        
    def pop(self):
        aux=self.__llista.getHead() # creamos un auxiliar el cual es la cabeza que es donde esta el actual
        self.__llista.remove() # Eliminamos el nodo actual que es el primero de la cola
        return aux # Devolvemos el dato del elemento borrado
        
    def head(self):
        aux=self.__llista.getHead() # auxiliar es la cabeza que es donde esta el actual
        return aux # devolvemos el dato del actual
        
    def purge(self):
        self.__llista.clear() # Borramos toda la pila
        
    def __len__(self):
        return self.__llista.size() # devuelve la longitud de la pila
    
    def __str__(self):
        return self.__llista.__str__() # devuelve la pila convertida en una cadena de caracteres
