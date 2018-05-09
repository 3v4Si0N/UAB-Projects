# -*- coding: cp1252 -*-

"""
Daniel Martos Tornay - 1369988
Hector De Armas Padron - 1369599
"""

class Graph(object):
    __slots__ = ['__nodes', '__edges', '__size']
    class Node(object):
        __slots__ = ['__data']
        def __init__(self, data):
            self.__data = data # inicializamos self.__data al dato, que es el dato del nodo
            
        def getData(self):
            return self.__data # devolvemos el dato
        
        def __str__(self):
            return str(self.__data) # devolvemos el dato convertido a cadena de caracteres
        
    class Edge(object):
        __slots__=['__start','__end','__data']

        def __init__(self,start,end,data):
            self.__start = start # inicializamos self._start a start que es el nodo inicial
            self.__end = end # inicializamos self._end a end que es el nodo final
            self.__data = data # inicializamos self.__data al dato, que es el dato de la arista
            
        def getData(self):
            return self.__data # devolvemos el dato
        
        def getStart(self):
            return self.__start # devolvemos el nodo inicial
        
        def getEnd(self):
            return self.__end    # devolvemos el nodo final     
    def __init__(self):
        self.__nodes = [] # inicializamos lista de nodos vacia
        self.__edges = [] # inicializamos lista de aristas vacia
        self.__size = 0 # inicializamos size a 0
        
    def addNode(self,data):
        node = self.Node(data) # creamos un nodo nuevo del grafo
        self.__nodes.append(node) # añadimos el nodo creado a la lista de python
        self.__size += 1 # incrementamos en 1 el tamaño del grafo
            
    def addEdge(self,start,end,data):
        for i in self.__nodes: # creamos un bucle en la lista de nodos para recorrerla
            if (i.getData() == start): # buscamos el nodo que contiene el dato inicial
                NodoInicial = i
        for j in self.__nodes: # creamos otro bucle en la lista nodos para recorrerla
            if (j.getData() == end): # buscamos el nodo que contiene el dato final
                NodoFinal = j
        NuevaArista = self.Edge(NodoInicial,NodoFinal,data) # creamos una nueva arista con el nodo inicial, el nodo final y el coste de la arista
        self.__edges.append(NuevaArista) # añadimos la arista a la lista 'self.__edges'
        
    def getNodes(self):
        listaNodos = [] # creamos una lista de python vacía
        for node in self.__nodes: # creamos un bucle en la lista nodos para recorrerla
            listaNodos.append(node.getData()) # añadimos a la lista 'listaNodos' el dato de cada nodo
        return listaNodos # devolvemos la lista
    
    def getEdges(self):
        ListaEnlaces = [] # creamos una lista de python vacía
        for edge in self.__edges: # creamos un bucle en la lista arista para recorrerla
            NodoInicial = edge.getStart().getData() # nodo inicial de la arista con su dato
            NodoFinal = edge.getEnd().getData() # nodo final de la arista con su dato
            Data = edge.getData() # dato de la arista
            ListaEnlaces.append((NodoInicial, NodoFinal, Data)) # insertamos en la lista de aristas el nodo inicial, nodo final y dato
        return ListaEnlaces # devolvemos la lista de aristas con sus respectivos datos
        
    def getNeighbors(self,nodedata):
        ListaVecinos = [] # creamos una lista de python vacía
        for edge in self.__edges: # creamos un bucle en la lista aristas para recorrerla
            if (edge.getStart().getData() == nodedata): # comprobamos si el el dato del nodo inicial corresponde con el dato que queremos
                NodoVecino = edge.getEnd() # nodo vecino
                Data = edge.getData() # dato de la arista con el nodo vecino
                ListaVecinos.append((NodoVecino, Data)) # añadimos a la lista 'ListaVecinos' el nodo vecino y el dato de la arista
        return ListaVecinos # devolvemos la lista de nodos vecinos

    def getMinPath(self,start,end):
        distances = {} # diccionario vacio donde las claves serán las distancias acumuladas de los nodos
        not_visited = {} # diccionario vacio donde las claves serán datos de los nodos y los valores serán bool

        for nodo in self.__nodes:
            if(nodo.getData() == start): # comprobamos si estamos en el nodo inicial
                distances[nodo.getData()] = 0 # distancia del nodo inicial es 0
                not_visited[nodo.getData()] = False # ponemos el bool del nodo inicial a False 
                actual = nodo # creamos una variable auxiliar para saber que nodo es el actual
            else:
                distances[nodo.getData()] = 99999999 # ponemos una distancia casi infinito para el resto de nodos
                not_visited[nodo.getData()] = True # El bool not_visited a True, controla que no los hemos visitado aun

        while(not_visited[end] == True): # mientras el ultimo nodo no se haya visitado...
            vecinos = self.getNeighbors(actual.getData()) # guardamos en 'vecinos' la información de los vecinos del nodo actual
            if(len(vecinos) > 0): # comprobamos que vecinos no está vacio
                for nodo in vecinos:
                    if (not_visited[nodo[0].getData()]) == True: # para todos los nodos que no estan visitados...
                        distTemp = distances[actual.getData()] + nodo[1] # calculamos la distancia que hay del nodo actual al siguiente vecino suyo y la guardamos en la variable 'dist'
                        if(distTemp < distances[nodo[0].getData()]): # comprobamos si la distancia temporal es mas pequeña que la del primer nodo
                            distances[nodo[0].getData()] = distTemp # actualizamos la distancia en el diccionario
                minDist = 99999999
                for nodo in vecinos:
                    if (not_visited[nodo[0].getData()] == True): # comprobamos que el nodo no esta visitado
                        if (nodo[1] < minDist): # comprobamos si la distancia del primer vecino es menor a minDist
                            minDist = nodo[1] # actualizamos minDist a la del nodo vecino
                            minDistNode = nodo[0].getData() # minDistNode distancia del primer nodo

                for nodo in self.__nodes:
                    if (nodo.getData() == minDistNode): # comprobamos si el nodo actual tiene la distancia mas corta
                        actual = nodo # el nodo es el nuevo actual
                        not_visited[actual.getData()] = False # marcamos el nodo actual como visitado
            else: # si vecinos está vacio....
                for nodo in self.__nodes:
                    if (not_visited[nodo.getData()] == True): # comprobamos que el nodo no está visitado
                        actual = nodo # el nodo no visitado es ahora el actual

        return start, end, distances[end] # devolvemos nodo inicial, nodo final y distancia menor recorrida hasta el nodo final
