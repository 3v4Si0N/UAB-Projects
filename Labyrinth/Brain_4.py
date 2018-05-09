# -*- coding: latin-1 -*- # Comentari per permetre que s'utilitzin accents i caràcters especials als comentaris i les cadenes de text.

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from pyrobot.brain import Brain

from LinkedList import * # Es suposa que LinkedList està implementada correctament.
import StackList
reload(StackList) # 'reload' serveix per forçar a Python a carregar l'arxiu StackList i actualitzar-ne les possibles modificacions.
import Pilot
reload(Pilot)     # 'reload' serveix per forçar a Python a carregar l'arxiu Pilot i actualitzar-ne les possibles modificacions.

class WB(Brain):
    def setup(self):
        self.stack = StackList.StackList()
        self.pilot = Pilot.Pilot()
        self.robot.move('reset')
    def step(self):
        if not self.robot.getItem('win'):
            self.pilot.setSonar(self.robot.getItem('sonar')) # utilizamos el sonar para saber a donde podemos movernos  
            if self.pilot.isCrossRoad(): # comprueba si hay una bifurcacion de ida
                if (len(self.stack)==0): # comprueba si la pila esta vacia
                    self.pilot.setCulDeSac(False) # hay salida
                if(self.pilot.getCulDeSac()==True): # comprueba si estamos en un callejon sin salida
                    (culdesac, movimiento) = self.stack.pop() # devuelve el movimiento de la pila
                    self.pilot.setCulDeSac(culdesac) # ponemos el callejon sin salida al valor que toque
                else:
                    actions = self.pilot.possibleActions() # utilizamos el possibleActions para saber a donde podemos ir
                    while (len(actions)>0): # mientras possibleActions no este vacio
                        self.stack.push(actions.pop()) # devolvemos el valor del movimiento que insertamos
                    (culdesac, movimiento) = self.stack.pop() # devolvemos el movimiento con su booleano
                self.robot.move(self.pilot.moveTo(movimiento)) # nos movemos hacia el movimiento que toque
                self.robot.move('grab') # cogemos el oro cuando estemos en una casilla gold
            else:
                siguienteMov=self.pilot.moveTo(self.pilot.nextMove()) # siguiente movimiento
                self.robot.move(siguienteMov) # si no hay bifurcacion utilizamos el piloto para movernos
                self.robot.move('grab') # cuando lleguemos a la casilla gold cogemos el oro

def INIT(engine):
    return WB('WB', engine)
