# -*- coding: cp1252 -*-
import Graph
reload(Graph)

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

g=Graph.Graph() # grafo

# añadimos nodos al grafo
g.addNode('1')
g.addNode('2')
g.addNode('3')
g.addNode('4')
g.addNode('5')
g.addNode('6')

#añadimos aristas al grafo con sus respectivos costes
g.addEdge('1','2',7)
g.addEdge('1','6',14)
g.addEdge('1','3',9)
g.addEdge('2','3',10)
g.addEdge('2','4',15)
g.addEdge('3','4',11)
g.addEdge('3','6',2)
g.addEdge('3','4',11)
g.addEdge('4','5',6)
g.addEdge('6','5',9)
print "camino grafo prueba:" + str(g.getMinPath('1','5')) # camino mas corto con coste 20 OK.


g.addNode('Barcelona')
g.addNode('Girona')
g.addNode('Tarragona')
g.addNode('Lleida')
g.addNode('Cerdanyola')
g.addNode('Terrassa')
g.addNode('Sabadell')
g.addNode('Manresa')
g.addEdge('Barcelona','Girona',99)
g.addEdge('Barcelona','Cerdanyola',21)
g.addEdge('Cerdanyola','Sabadell',10)
g.addEdge('Sabadell','Terrassa',12)
g.addEdge('Girona','Lleida',229)
g.addEdge('Barcelona','Lleida',163)
g.addEdge('Manresa','Lleida',115)
g.addEdge('Lleida','Tarragona',103)
print "camino grafo Distancias:" + str(g.getMinPath('Barcelona','Tarragona')) # camino mas corto con coste 266 OK.




