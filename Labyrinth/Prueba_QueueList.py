# -*- coding: cp1252 -*-

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

import QueueList
reload(QueueList)

c = QueueList.QueueList() # Cola
print '\n--------------\n'
print 'PUSHs:'
c.push(3)
c.push(4)
c.push(5)
c.push(6)
c.push(7)
c.push(8)
c.push(9)
c.push(10)

print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent())

print '\n--------------\n'
print 'POP:'
elemento=c.head()
c.pop()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent()) + '\nEl elemento extraido es: ' + str(elemento)

print '\n--------------\n'
print 'POP:'
c.pop()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent())

print '\n--------------\n'
print 'POP:'
c.pop()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent())

print '\n--------------\n'
print 'POP:'
c.pop()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent())

print '\n--------------\n'
print 'POP:'
c.pop()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent())

print '\n--------------\n'
print 'POP:'
elemento=c.pop()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__()) + '\nEl actual es: ' + str(c.getCurrent()) + '\nel elemento extraido es: ' + str(elemento)
print '\n--------------\n'
print 'Eliminamos todos los nodos de la cola: '
c.purge()
print 'Cola: ' + str(c)
print 'tamaño de la cola: ' + str(c.__len__())
