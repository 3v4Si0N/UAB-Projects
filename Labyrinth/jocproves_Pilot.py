# -*- coding:utf-8  -*-
import inspect 

print "Comprovant Pilot:",
# Primer de tots s'hauria de comprovar si l'arxiu existeix o no...

try:
	Pilot = __import__("Pilot")
	reload(Pilot)
except:
	raise ValueError, """Pilot: No s'ha trobat l'arxiu "Pilot.py" (Comproveu el nom de l'arxiu que ha de ser exactament igual)."""

# Comprovació de la declaració de la classe:
################################################################################
if len(inspect.getargspec(Pilot.Pilot.__init__)[0]) != 1: raise ValueError, """Pilot: El constructor de Pilot ha de tenir exactament la següent capçalera: "def __init__(self):" """

try:
	if len(inspect.getargspec(Pilot.Pilot.getPrevious)[0]) != 1: raise ValueError, """Pilot: a getPrevious() no se li passa cap paràmetre, la seva capçalera ha de ser exactament: "def getPrevious(self):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció getPrevious(). La capçalera d'aquesta ha de ser exactament: "def getPrevious(self):"."""
try:
	if len(inspect.getargspec(Pilot.Pilot.setSonar)[0]) != 2: raise ValueError, """Pilot: a setSonar() se li passa un paràmetre, la seva capçalera ha de ser exactament: "def setSonar(self, sonar):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció setSonar(). La capçalera d'aquesta ha de ser exactament: "def setSonar(self, sonar):"."""
try:
	if len(inspect.getargspec(Pilot.Pilot.isCrossRoad)[0]) != 1: raise ValueError, """Pilot: a isCrossRoad() no se li passa cap paràmetre, la seva capçalera ha de ser exactament: "def isCrossRoad(self):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció isCrossRoad(). La capçalera d'aquesta ha de ser exactament: "def isCrossRoad(self):"."""
try:
	if len(inspect.getargspec(Pilot.Pilot.getCulDeSac)[0]) != 1: raise ValueError, """Pilot: a getCulDeSac() no se li passa cap paràmetre, la seva capçalera ha de ser exactament: "def getCulDeSac(self):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció getCulDeSac(). La capçalera d'aquesta ha de ser exactament: "def getCulDeSac(self):"."""
try:
	if len(inspect.getargspec(Pilot.Pilot.setCulDeSac)[0]) != 2: raise ValueError, """Pilot: a setCulDeSac() se li passa un paràmetre, la seva capçalera ha de ser exactament: "def setCulDeSac(self, culdesac):" """
	if inspect.getargspec(Pilot.Pilot.setCulDeSac)[3] != None: raise ValueError, """Pilot: als paràmetres de setCulDeSac() no se'ls hi pot passar cap paràmetre per defecte, la seva capçalera ha de ser exactament: "def setCulDeSac(self, culdesac):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció setCulDeSac(). La capçalera d'aquesta ha de ser exactament: "def setCulDeSac(self, culdesac):"."""
try:
	if len(inspect.getargspec(Pilot.Pilot.moveTo)[0]) != 2: raise ValueError, """Pilot: a moveTo() se li ha de passar un paràmetre, la seva capçalera ha de ser exactament: "def moveTo(self, action):" """
	if inspect.getargspec(Pilot.Pilot.moveTo)[3] != None: raise ValueError, """Pilot: als paràmetres de moveTo() no se'ls hi pot passar cap paràmetre per defecte, la seva capçalera ha de ser exactament: "def moveTo(self, action):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció moveTo(). La capçalera d'aquesta ha de ser exactament: "def moveTo(self, action):"."""
try:
	if len(inspect.getargspec(Pilot.Pilot.nextMove)[0]) != 1: raise ValueError, """Pilot: a nextMove() no se li ha de passar cap paràmetre, la seva capçalera ha de ser exactament: "def nextMove(self):" """
except AttributeError:
	raise ValueError, """Pilot: No s'ha trobat la funció nextMove(). La capçalera d'aquesta ha de ser exactament: "def nextMove(self):"."""



# Comprovació del funcionament de la classe:
################################################################################
msg2 = """Pilot: La funció nextMove no actualitza correctament la variable membre 'self.__previous'."""
msg3 = """Pilot: La funció nextMove ha de posar a True la variable membre 'self.__culdesac' quan el robot arriba a un cul de sac."""
p=Pilot.Pilot()
inverse = {'right' : 'left', 'down' : 'up', 'left' : 'right', 'up' : 'down'}
for i in inverse.keys():
    for j in inverse.keys():
        s={'right' : 1, 'down' : 1, 'left' : 1, 'up' : 1}
        p.setSonar(s)
        p.moveTo(inverse[i])
        s={'right' : 0, 'down' : 0, 'left' : 0, 'up' : 0}
        s[i]=1
        s[j]=1
        p.setSonar(s)
        tmp=p.nextMove()
        msg1 = """Pilot: La funció nextMove no retorna el moviment correcte en el cas d'un sonar """ + str(s) + """ i acabant de fer un '""" + str(inverse[i]) + """', en lloc d'un '""" + str(j) + """' retorna un '""" + str(tmp) + """'."""
        if tmp!=j: raise ValueError, msg1
        if p.getPrevious() != p._Pilot__previous: raise ValueError, "La funció getPrevious no retorna el valor de __previous."
        if p._Pilot__previous != tmp: raise ValueError, msg2
        s[p._Pilot__previous] = 0
        if s.values().count(True) == 0 and p._Pilot__culdesac != True: raise ValueError, msg3


#raise ValueError, """Pilot: No es poden recollir les dades al fer "getData()"."""
print "[Correcte]"


#///////////////////////////////////////////////////////////////////////////////
#//////////////////////////////////////#////////////////////////////////////////
#/////////////////////////////////////###///////////////////////////////////////
#////////////////////////////////////#####//////////////////////////////////////
#///////////////////////////////////#######/////////////////////////////////////
#//////////////////////////////////#########////////////////////////////////////
#/////////////////////////////////###########///////////////////////////////////
#////////////////////////////////////#####//////////////////////////////////////
#////////////////////////////////////#####//////////////////////////////////////
#////////////////////////////////////#####//////////////////////////////////////
#/////////////////////////////////###########///////////////////////////////////
#//////////////////////////////////#########////////////////////////////////////
#///////////////////////////////////#######/////////////////////////////////////
#////////////////////////////////////#####//////////////////////////////////////
#/////////////////////////////////////###///////////////////////////////////////
#//////////////////////////////////////#////////////////////////////////////////
#///////////////////////////////////////////////////////////////////////////////
