# -*- coding: latin-1 -*- # Comentari per permetre que s'utilitzin accents i carÃ cters especials als comentaris i les cadenes de text.

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from pyrobot.brain import Brain

import Pilot
reload(Pilot)
class WB(Brain):
        def setup(self):
                self.counter=0
                self.inverse={'right':'left','left':'right','up':'down','down':'up'}
                self.actions=['left','up','up','left']
                self.backtrace=[]
                self.pilot=Pilot.Pilot()
                self.robot.move('reset')
        def step(self):
                self.pilot.setSonar(self.robot.getItem('sonar')) # utilizamos el sonar para saber a donde podemos movernos
                if self.pilot.isCrossRoad(): # comprueba si hay una bifurcacion de ida
                        if (self.robot.getItem('golds')>0): # comprueba que aun hay oro en el laberinto
                                self.robot.move(self.pilot.moveTo(self.actions[self.counter])) # cuando hay una bifurcacion vamos hacia la siguiente casilla mediante la lista accion
                                self.counter+=1 # nos movemos 1 hacia delante
                        else:
                                self.counter-=1 # invertimos el contador para volver atras
                                self.robot.move(self.pilot.moveTo(self.backtrace[self.counter])) #volvemos hacia atras utilizando la lista backtrace en donde hemos guardado cada movimiento que hemos hecho hacia delante para poder volver
                else:
                        movimiento=self.pilot.moveTo(self.pilot.nextMove()) # siguiente movimiento
                        self.robot.move(movimiento) # si no hay bifurcacion utilizamos el piloto para movernos
                        self.robot.move('grab') # cuando lleguemos a la casilla gold cogemos el oro
                        self.pilot.setSonar(self.robot.getItem('sonar')) # utilizamos el sonar para saber a donde podemos movernos una vez hayamos cogido el oro
                        if self.pilot.isCrossRoad(): # comprueba si hay una bifurcacion a la vuelta
                                self.backtrace.append(self.inverse[movimiento]) # añade movimiento a la lista backtrace para poder volver
                        
def INIT(engine):
        return WB('WB',engine)

