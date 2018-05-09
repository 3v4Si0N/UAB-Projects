
"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

class LinkedList(object):
	__slots__ = ['__head', '__tail', '__current', '__size']
	class Node(object):
		__slots__ = ['__data', '__next']
                def __init__(self, data, next = None): # Inicializa el nodo con un dato y una referencia al nodo siguiente
                        self.__data = data
                        self.__next = next
			
		def getData(self): # Devuelve el dato del nodo
			return self.__data
			
		def getNext(self): # Devuelve la referencia del siguiete nodo
			return self.__next
			
		def setData(self, data): # Instancia la variable data con un nuevo valor
			self.__data = data
			
		def setNext(self, next): # Instancia la variable next con un nuevo valor
			self.__next = next
			
		def __str__(self): # Devuelve el contenido de la variable data convertido en una cadena caracteres
			return str(self.__data)
			

	def __init__(self): # Inicializa la lista como vacia
		self.__head=None
		self.__tail=None
		self.__current=None
		self.__size=0

	def getHead(self): # Devuelve el valor de head
		if(self.isEmpty()): # Comprueba si la lista esta vacia o no
			raise IndexError, 'La lista esta vacia'
		else:
			return self.__head.getData()
		
	def getTail(self): # Devuelve el valor de tail
		if(self.isEmpty()): # Comprueba si la lista esta vacia o no
			raise IndexError, 'La lista esta vacia'
		else:
			return self.__tail.getData()
		
	def getCurrent(self): # Devuelve el valor de current
		if(self.isEmpty()): # Comprueba si la lista esta vacia o no
			raise IndexError, 'La lista esta vacia'
		else:
			return self.__current.getData()
			
	def moveNext(self): # Mueve el current al siguiente nodo
		if((self.isEmpty()) or (self.__current==self.__tail)): # Comprueba que la lista no esta vacia y que si el current esta en la ultima posicion de la lista, no se mueva
			raise IndexError, 'El nodo no se puede mover'
		else:
			self.__current=self.__current.getNext()
			
	def moveHead(self): # Mueve el current a head
		if(self.isEmpty()): # Comprueba si la lista esta vacia o no
			raise IndexError, 'La lista esta vacia'
		else:
			self.__current=self.__head
		
	def isEmpty(self): #retorna boolea que es True si la llista es buida y False en cas contrari.
		return (self.__head == None and self.__tail == None)
		
	def size(self): # Devuelve el numero de elementos de la lista
		return self.__size
		
	def clear(self): # Borra la lista completa
		self.__head = None
		self.__current = None
		self.__tail = None
		self.__size = 0
		
	def insertBefore(self, data):
		newNode = LinkedList.Node(data) # crea un nuevo nodo en la lista
		if (self.isEmpty()): # Comprueba si la lista esta vacia, y si lo esta el head, el tail y el current son el nuevo nodo
			self.__head = newNode
			self.__tail = newNode
			self.__current = newNode
		
		elif (self.__current == self.__head): # Comprueba si el actual es el head
			newNode.setNext(self.__current) # El nuevo apunta a current
			self.__head=newNode # El head es el nuevo
			self.__current=newNode # El actual es el nuevo
		
		else:
			aux = self.__head # Creamos un auxiliar para poder recorrer la lista
			while (aux.getNext() != self.__current): # Mientras el siguiente de aux sea distinto al actual saltamos al siguiente nodo
				aux=aux.getNext()
			newNode.setNext(self.__current) # El nuevo apunta al actual
			aux.setNext(newNode) # aux apunta al nuevo
			self.__current=newNode	# El actual es el nuevo
		self.__size+=1 # Aumenta 1 la lista
		
	def insertAfter(self, data):
		newNode = LinkedList.Node(data) # Crea un nuevo nodo en la lista
		if (self.isEmpty()): # Comprueba si la lista esta vacia, y si lo esta el head, el tail y el current son el nuevo
			self.__head = newNode
			self.__tail = newNode
			self.__current = newNode
			
		elif (self.__current == self.__tail): # Comprueba que el actual es el ultimo
			newNode.setNext(self.__current.getNext()) # El nuevo nodo apunta al siguiente del actual
                        self.__current.setNext(newNode) # El actual apunta al nuevo nodo
			self.__tail = newNode # El ultimo es el nuevo
			self.__current = newNode # El actual es el nuevo
			
		else:
			newNode.setNext(self.__current.getNext()) # El nuevo nodo apunta al siguiente del actual
			self.__current.setNext(newNode) # El actual apunta al nuevo
			self.__current=newNode # El actual es el nuevo
		self.__size+=1 # Aumenta 1 la lista

	def remove(self):
		if (self.isEmpty()): # Comprueba si la lista esta vacia o no
			raise IndexError, 'EmptyList'
		else:	
			if(self.__head == self.__tail): # Comprueba que el head es el tail y si lo es todos son none
				self.__head=None
				self.__current = None
				self.__tail = None
			else:
				if(self.__current==self.__head): # comprueba si el actual es head
					self.__current = self.__current.getNext() # movemos el actual al siguiente nodo
					self.__head = self.__current # Ahora el primer elemento es el actual
				
                                elif(self.__current.getNext()==None): # Comprueb a si el siguiente de actual es none
                                        aux = self.__head # creamos un auxilir para recorrer la lista desde el principio
                                        while (aux.getNext() != self.__current): # Mientras el siguiente de aux sea distinto a el actual
                                                aux = aux.getNext() # Nos movemos un nodo hacia delante
                                        self.__tail = aux # Ya el siguiente de aux es el actual con lo cual hacemos que el ultimo sea aux
                                        aux.setNext(self.__current.getNext()) # A aux le asignamos un siguiente que es el siguiente del actual
                                        self.__current=self.__tail # Ahora el actual es el ultimo

                                else:
                                        aux = self.__head # creamos un auxiliar para recorrer la lista desde el principio
                                        while (aux.getNext() != self.__current): # Mientras el siguiente de aux sea distinto a el actual
                                                aux = aux.getNext() # Nos movemos un nodo hacia delante
                                        aux.setNext(self.__current.getNext()) # A aux le asignamos un siguiente que es el siguiente de actual
                                        self.__current=aux # Ahora el actual es aux
		self.__size-=1 # Decrementamos 1 el total de la lista
		
	def __str__(self): # Convierte la lista en una cadena de caracteres
		if not self.isEmpty(): # Comprueba que la lista no esta vacia
                        aux = self.__current # aux es el actual
			self.moveHead() # movemos el actual a head
			string = '' # creamos la cadena
			while (self.__current.getNext() !=  None): # Comprobamos que el siguiente de actual no es none
				string+=str(self.__current) + ', ' # Metemos el nodo en la lista
				self.moveNext() # Saltamos al siguiente nodo
			string+=str(self.__current) + ''
			self.__current = aux # El actual es aux
			return string # Devolvemos la cadena de caracteres
		else:
			return '' # Si la lista esta vacia no devuelve nada	


