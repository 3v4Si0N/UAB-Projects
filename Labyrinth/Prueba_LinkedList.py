import LinkedList
reload(LinkedList) 

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

l = LinkedList.LinkedList() # lista

#insercion de los elementos en la lista
l.insertAfter(0)
l.insertBefore(1)
l.insertAfter(2)
l.insertBefore(3)
l.insertAfter(4)
l.insertBefore(5)
l.insertAfter(6)
l.insertBefore(7)
l.insertAfter(8)
l.insertBefore(9)

print '-------------------------------------'    
print '\nlista: '+ str(l)
print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())
print '-------------------------------------'

try:
    l.remove()
    print '-------------------------------------'
    print '\nlista: '+ str(l)
    print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())

    print 'Se ha borrado un elemento de la lista.'
    print '-------------------------------------'
except IndexError:
    print 'La lista no puede borrar elementos.'

try:
    l.remove()
    print '-------------------------------------'
    print '\nlista: '+ str(l)
    print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())
    print 'Se ha borrado un elemento de la lista.'
    print '-------------------------------------'
except IndexError:
    print 'La lista no puede borrar elementos.'
    
l.moveHead()
l.moveNext()
l.moveNext()
l.moveNext()
l.remove() # borra el 8

print '-------------------------------------'
print '\nlista: '+ str(l)
print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())
print 'Borra el numero 8 de la lista'
print '-------------------------------------'

l.moveNext()
l.moveNext()
l.moveNext()
l.moveNext()
l.remove() # borra el ultimo elemento que es el 0

print '-------------------------------------'
print '\nlista: '+ str(l)
print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())
print 'Borra el 0 que es el ultimo elemento de la lista'
print '-------------------------------------'

l.moveHead()
l.remove() # borra el primer elemento que es el 1

print '-------------------------------------'
print '\nlista: '+ str(l)
print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())
print 'Borra el 1 que es el ultimo elemento de la lista'
print '-------------------------------------'

print '-------------------------------------'
l.clear()
try:
    print '\nlista: '+ str(l)
    print 'Tamaño:' + str(l.size()) + '\nprimer elemento:' + str(l.getHead()) + '\nultimo elemento:' + str(l.getTail()) + '\nelemento actual:' + str(l.getCurrent())
except IndexError:
    print 'La lista esta vacia.'
print '-------------------------------------' 
