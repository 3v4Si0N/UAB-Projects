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
import Tree
reload(Tree)      # 'reload' serveix per forçar a Python a carregar l'arxiu Tree i actualitzar-ne les possibles modificacions.
import os

class WB(Brain):
    def setup(self):
        self.stack = StackList.StackList()
        self.pilot = Pilot.Pilot()
        self.robot.move('reset')
        self.tree = Tree.Tree()
        #Per algunes rutes, el Python no troba la ruta de l'arxiu 'contrasenyes.txt'.
        #self.tree.build('contrasenyes.txt')
        self.tree.build(os.path.dirname(os.path.realpath(__file__)) + '/contrasenyes.txt')
        self.doors = {}
        self.listaNueva = []
    def step(self):
        if not self.robot.getItem('win'):
            self.pilot.setSonar(self.robot.getItem('sonar'))
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
                siguienteMov = self.robot.move(self.pilot.moveTo(movimiento)) # nos movemos hacia el movimiento que toque
                self.robot.move('grab') # cogemos el oro cuando estemos en una casilla gold
                
            else:
                siguienteMov = self.robot.move(self.pilot.moveTo(self.pilot.nextMove())) # siguiente movimiento
                self.robot.move('grab') # cuando lleguemos a la casilla gold cogemos el oro

            if (siguienteMov == 'door'): # comprobamos si estamos en la casilla door
                door = self.robot.move('talk') # igualamos door a talk para poder hablar con la puerta
                while (door != None): # mientras el talk sea diferente de None hablamos con la puerta
                    self.listaNueva.append(door) # insertamos en la lista creada con lo que nos ha dicho la puerta uno por uno 
                    door = self.robot.move('talk')
                location = self.robot.getItem('location') # localizacion de la puerta
                self.doors[location] = self.listaNueva # guardamos en el diccionario self.doors la clave de la puerta con su localizacion
                try: # si noentramos en la excepcion IndexError buscamos la hoja para determinar la clave
                    animal = self.tree.search(self.listaNueva) # animal es la hoja
                    self.robot.move(animal) # nos movemos en la direccion en la que est� la puerta
                except(IndexError): # si entramos en el IndexError significar� que no tenemos la clave para abrir la puerta con lo que tenemos que ir a buscar la llave
                    siguienteMov # siguiente movimiento
                            
            if (siguienteMov == 'key'): # comprobamos si estamos en la casilla key
                key = self.robot.move('talk') # igualamos key a talk para poder hablar con la llave
                while (key != "This thing doesn't speak!"): # comprobamos que seguimos en la casilla key para poder hablar con ella
                    self.listaNueva.append(key) # insertamos lo que nos va diciendo la llave uno por uno
                    key = self.robot.move('talk')
                self.tree.add(self.listaNueva) # guardamos la llave en el arbol para poder abrir la puerta
                self.listaNueva.remove(self.listaNueva[len(self.listaNueva)-1]) # eliminamos el ultimo animal insertado de la lista creada
            

def INIT(engine):
    return WB('WB', engine)
