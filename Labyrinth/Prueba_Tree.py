# -*- coding: cp1252 -*-
import Tree
reload(Tree)

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

t=Tree.Tree()
n0 = Tree.Tree.Node('no')
n1 = Tree.Tree.Node('yes')
n2 = Tree.Tree.Node('yes')
n3 = Tree.Tree.Node(4)
n4 = Tree.Tree.Node('animal')

#Comprobación de la clase Node
n0.addChild(n1)
n1.addChild(n2)
n2.addChild(n3)
n3.addChild(n4)
x = n1.getChilds()
y = n2.getChilds()
print 'Nodos:\n' + str(n0) + '\nnodos hijos de nodo1:\n' + str(x) + '\nnodos hijos de nodo2:\n' + str(y)


#Comprobación de la clase Tree
lista = ['yes','no',4,'yes','yes','no','no','nombre']
t.add(lista)
print 'Arbol:\n' + str(t)

lista = ['yes','no',4,'yes','yes','no','no']
print 'El nodo hoja es: ' + str(t.search(lista))

