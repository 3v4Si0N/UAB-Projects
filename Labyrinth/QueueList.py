# -*- coding: latin-1 -*-

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from LinkedList import *

class QueueList(LinkedList):
    def __init__(self):
        LinkedList.__init__(self) # Inicializamos todas las variables de la lista
        
    def push(self, data):
        LinkedList.insertAfter(self, data) # Insertamos elemento por el final
        
    def pop(self):
        LinkedList.moveHead(self) # Nos movemos al principio de la cola
        aux=LinkedList.getHead(self) # creamos un auxiliar que es la cabeza donde esta el actual
        LinkedList.remove(self) # Eliminamos el nodo actual que es el primero de la cola
        return aux # Devolvemos el dato del elemento borrado
            
    def head(self):
        aux=LinkedList.getHead(self) # auxiliar es la cabeza que es donde esta el actual
        return aux # devolvemos el dato del actual
        
    def purge(self):
        LinkedList.clear(self) # Borramos toda la cola
        
    def __len__(self):
        return self._LinkedList__size # Longitud de la cola

