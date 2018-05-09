# -*- coding: latin-1 -*- # Comentari per permetre que s'utilitzin accents i caràcters especials als comentaris i les cadenes de text.

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

from pyrobot.brain import Brain

class WB(Brain):
    def setup(self):
        self.counter = 0
        self.inverse = {'up':'down','down':'up','right':'left','left':'right'}
        self.actions = ['right','right','up','up','left','left','up','up','left','up','up','right','up','up','up','right','right','down','down','right','right','right','right','up','up','left','left']
    def step(self):
        if not self.robot.getItem('win'): # comprueba si aun no hemos ganado
            if (self.robot.getItem('golds')>0): # comprueba si hay oro
                self.robot.move(self.actions[self.counter]) # nos movemos +1 casilla con el actions
		self.counter+=1 # nos movemos 1 posicion hacia delante
                self.robot.move('grab') # cuando llegamos a la casilla gold cogemos el oro
            else:
                self.counter-=1 # invertimos el counter para poder volver
                self.robot.move(self.inverse[self.actions[self.counter]]) # invertimos el actions y con eso conseguimos la siguiente casilla a la que tenemos que ir
                if (self.counter < 1): # comprueba si el counter llega a 0
                    self.robot.move('left') # cuando llega a 0 nos movemos a la izquierda y salimos
                
	    
def INIT(engine):
    return WB('WB', engine)
