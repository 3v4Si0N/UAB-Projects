# -*- coding: cp1252 -*-

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

import StackList
reload(StackList)

p = StackList.StackList() # Pila
print '\n--------------\n'
print 'PUSHs:'
p.push(3)
p.push(4)
p.push(5)
p.push(6)
p.push(7)
p.push(8)
p.push(9)
p.push(10)

print 'Pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__())

print '\n--------------\n'
print 'POP:'
elemento=p.head()
p.pop()
print 'Pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__()) + '\nEl elemento extraido es: ' + str(elemento)

print '\n--------------\n'
print 'POP:'
p.pop()
print 'pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__())

print '\n--------------\n'
print 'POP:'
p.pop()
print 'pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__())

print '\n--------------\n'
print 'POP:'
p.pop()
print 'pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__())

print '\n--------------\n'
print 'POP:'
p.pop()
print 'Pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__())

print '\n--------------\n'
print 'POP:'
elemento=p.pop()
print 'Pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__()) + '\nel elemento extraido es: ' + str(elemento)
print '\n--------------\n'
print 'Eliminamos todos los nodos de la pila: '
p.purge()
print 'Pila: ' + str(p)
print 'tamaño de la pila: ' + str(p.__len__())

