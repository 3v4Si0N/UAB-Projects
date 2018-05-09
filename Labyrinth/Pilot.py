# -*- coding: latin-1 -*- # Comentari per permetre que s'utilitzin accents i caràcters especials als comentaris i les cadenes de text.

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from QueueList import *

class Pilot(object):
    __slots__ = ['__previous', '__sonar', '__inverse', '__culdesac']
    def __init__(self):
        self.__previous = None
        self.__sonar = {}
        self.__inverse = {'left':'right','right':'left','up':'down','down':'up'}
        self.__culdesac = False
        
    def getPrevious(self):
        return self.__previous
    
    def setSonar(self, sonar):
        self.__sonar = dict(sonar) # Hace una copia del diccionario, no una referencia.
        
    def isCrossRoad(self):
        return sum(self.__sonar.values()) > 2
    
    def getCulDeSac(self):
        return self.__culdesac
    
    def setCulDeSac(self, culdesac):
        self.__culdesac = culdesac

    def moveTo(self,action):
        try:   
            if self.__sonar.has_key(action): # comprueba que existe la direccion 
                try:
                    if (self.__sonar[action]==True): # comprueba que la accion es posible
                        self.__previous=self.__inverse[action]# guarda a previous la direccion contraria a accion
                        return action # devuelve la accion 
                except:
                    print 'no se puede mover en esta direccion'
        except:
            print 'direccion NO valida'
                        
    def nextMove(self):
        nuevo=self.__sonar.copy() # copia del sonar
        for direccion in nuevo: # comprueba q nos podemos mover en esa direccion y que nos movemos en una direccion nueva y que no vengamos de esa nueva 
            if ((nuevo[direccion]==True) and (direccion!=self.getPrevious())):
                self.__previous=direccion
                return direccion # devuelve la direccion
        else: # si estamos en un camino sin salida volvemos hacia atras
            self.setCulDeSac(True) # hemos topado con una pared y ponemos el culdesac a true
            return self.getPrevious() # devuelve la unica direccion posible a la que podemos ir
        
    def possibleActions(self):
        cola=QueueList() # creamos una cola
        action=self.getPrevious() # la accion es la anterior
        tupla=(True, action) # creamos una tupla con un bool a true con la accion, ya que la primera accion de la cola debe ser true siempre
        cola.push(tupla) # insertamos la accion en la cola
        for action in ('up','down','right','left'): # comprobamos la accion en todas las posibles direcciones
            if(self.__sonar[action]==True) and (action!=self.getPrevious()): # comprobamos que la accion sea verdadera en el sonar y que la accion no sea la anterior hecha
                tupla=(False, action) # ponemos la accion a false
                cola.push(tupla) # insertamos la accion en la cola
        return cola # devolvemos la cola
                
