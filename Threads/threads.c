#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/types.h>
#include <sys/syscall.h>

#define NUM_THREADS 100
#define TAM_BUFFER 5

typedef struct {
        int id_cliente; // Identificador de cliente {1...N}
        int id_compra; // Identificador de compra {1..100}
} peticion_t;


typedef struct {
	int pos_lectura;
	// Siguiente posicion a
	int pos_escritura;
	// Siguiente posicion a
	int num_peticiones; // Número de peticiones
	peticion_t peticiones[TAM_BUFFER]; // Buffer
} buffer_peticiones_t;

buffer_peticiones_t buffer;
pthread_mutex_t mutex;


// Inicializa la estructura del buffer y sus centinelas @pos_lectura y @pos_escritura
void buffer_peticiones_inicializar(buffer_peticiones_t* buffer_peticiones)
{
	buffer_peticiones->pos_lectura = 0;
	buffer_peticiones->pos_escritura = 0;
	buffer_peticiones->num_peticiones = 0;
}

// Devuelve 1 si el buffer esta completamente lleno, 0 en caso contrario
int buffer_peticiones_lleno(buffer_peticiones_t* buffer_peticiones)
{
	return buffer_peticiones->num_peticiones == TAM_BUFFER;
}

// Devuelve 1 si el buffer esta completamente vacio, 0 en caso contrario
int buffer_peticiones_vacio(buffer_peticiones_t* buffer_peticiones)
{
	return buffer_peticiones->num_peticiones == 0;
}

// Encola @peticion en el buffer
void buffer_peticiones_encolar(buffer_peticiones_t* buffer_peticiones, peticion_t* peticion)
{
	buffer_peticiones->peticiones[buffer_peticiones->pos_escritura] = *peticion;
	buffer_peticiones->pos_escritura = (buffer_peticiones->pos_escritura+1) % TAM_BUFFER;
	++buffer_peticiones->num_peticiones;
}

// Retira del buffer la peticion mas antigua (FIFO) y la copia en @peticion
void buffer_peticiones_atender(buffer_peticiones_t* buffer_peticiones, peticion_t* peticion)
{
	*peticion = buffer_peticiones->peticiones[buffer_peticiones->pos_lectura];
	buffer_peticiones->pos_lectura = (buffer_peticiones->pos_lectura+1) % TAM_BUFFER;
	--buffer_peticiones->num_peticiones;
}

void* cliente(int thread_id)
{
	long int i;
	for(i = 1; i <= NUM_THREADS;)
	{
		peticion_t peticion;
		peticion.id_cliente = thread_id;
		peticion.id_compra = i;
		pthread_mutex_lock(&mutex);
		if(!buffer_peticiones_lleno(&buffer))
		{
			buffer_peticiones_encolar(&buffer, &peticion);
			i++;
		}
		pthread_mutex_unlock(&mutex);
	}
	pthread_exit(NULL);
}

void* servidor()
{
	FILE *fd;
	fd = fopen("compras.txt", "w");
	fprintf(fd, "ID cliente -> ID compra\n");
	printf("Realizando compras..\n");
	while(1)
	{
		pthread_mutex_lock(&mutex);
		if(!buffer_peticiones_vacio(&buffer))
		{
			peticion_t peticion;
			buffer_peticiones_atender(&buffer, &peticion);
			fprintf(fd, "Cliente %d -> %d\n", peticion.id_cliente, peticion.id_compra);
			if(peticion.id_compra == 0 && peticion.id_cliente == 0)
				pthread_exit(NULL);
		}
		pthread_mutex_unlock(&mutex);
	}
	pthread_exit(NULL);
}

int main (int argc, char **argv)
{
	pthread_t threads_clientes[NUM_THREADS], thread_servidor[1];
	long int i;
	int threadsClientes, threadServidor;
	peticion_t peticion;
	pthread_mutex_init(&mutex, NULL);

	// Creamos los threads clientes
	printf("Creando todos los clientes\n");
	for(i = 1; i <= NUM_THREADS; i++)
	{
		threadsClientes = pthread_create(threads_clientes + i, NULL, (void* (*)(void*))cliente, (void *)i);

		if(threadsClientes != 0)
		{
			printf("Ha ocurrido algún error en la creación del cliente: %d y su código es: %d", i, threadsClientes);
			exit(EXIT_FAILURE);
		}
	}

	// Creamos el thread servidor
	printf("Creando servidor\n");
	threadServidor = pthread_create(thread_servidor, NULL, (void* (*)(void*))servidor, (void *)1);
	if(threadServidor != 0)
	{
		printf("Ha ocurrido algún error en la creación del servidor, su código es: %d", threadServidor);
		exit(EXIT_FAILURE);
	}

	// Esperamos a los threads clientes
	for(i = 1; i <= NUM_THREADS; i++)
		pthread_join(threads_clientes[i], NULL);

	peticion.id_cliente = 0;
	peticion.id_compra = 0;
	sleep(3);
	buffer_peticiones_encolar(&buffer, &peticion);
	pthread_join(thread_servidor[0], NULL); // Esperamos al thread servidor
	pthread_mutex_destroy(&mutex); // Destruimos el mutex
	return 0;
}
