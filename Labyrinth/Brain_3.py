# -*- coding: latin-1 -*- # Comentari per permetre que s'utilitzin accents i caràcters especials als comentaris i les cadenes de text.

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from pyrobot.brain import Brain

import QueueList
reload(QueueList) # 'reload' serveix per forçar que Python carregui l'arxiu QueueList. 
import Pilot
reload(Pilot) # 'reload' serveix per forçar que Python carregui l'arxiu Pilot. 

class WB(Brain):
    def setup(self):
        self.inverse = {'up' : 'down', 'down' : 'up', 'right' : 'left', 'left' : 'right'}
        self.actions = QueueList.QueueList()
        self.pilot = Pilot.Pilot()
        self.backtrace = []
        self.counter=0
        self.robot.move('reset')
    def step(self):
        if not(self.robot.getItem('win')): # Comprueba si hemos ganado o no
            if(self.robot.getItem('golds')>0): # comprueba que aun hay oro en el laberinto
                self.pilot.setSonar(self.robot.getItem('sonar')) # utilizamos el sonar para saber a donde podemos movernos
                aux="" #cadena vacia
                while(aux != "This thing doesn't speak!"): # mientras aux sea distinto a this thing doesn't speak
                    aux=self.robot.move('talk') # aux es igual a talk
                    if (aux=='right' or aux=='left' or aux=='up' or aux=='down'): # si encontramos que aux es derecha o izquierda o arriba o abajo
                        self.actions.push(aux) # hacemos un push de lo que haya hablado el wumpus
                        
                if self.pilot.isCrossRoad(): # comprueba si hay una bifurcacion de ida
                    self.backtrace.append(self.pilot.getPrevious()) # insertamos en backtrace el movimiento inverso que hicimos previamente
                    self.counter+=1 # aumentamos 1 el contador
                    self.robot.move(self.pilot.moveTo(self.actions.pop())) # Nos movemos hacia la direccion que devuelva el pop
                                        
                else:
                    movimiento=self.pilot.moveTo(self.pilot.nextMove()) # siguiente movimiento
                    self.robot.move(movimiento) # si no hay bifurcacion utilizamos el piloto para movernos
                self.robot.move('grab') # cuando lleguemos a la casilla gold cogemos el oro
                
            else:
                self.pilot.setSonar(self.robot.getItem('sonar')) # utilizamos el sonar para saber a donde podemos movernos a la vuelta
                if self.pilot.isCrossRoad(): # comprueba si hay una bifurcacion de vuelta
                    self.counter-=1 # invertimos el contador para volver atras
                    self.robot.move(self.pilot.moveTo(self.backtrace[self.counter])) #volvemos hacia atras utilizando la lista backtrace en donde hemos guardado cada movimiento que hemos hecho hacia delante para poder volver
                    
                else:
                    self.robot.move(self.pilot.moveTo(self.pilot.nextMove())) # nos movemos a la siguiente casilla
                    

def INIT(engine):
    return WB('WB', engine)
