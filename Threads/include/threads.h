
#define TAM_BUFFER 5
typedef struct {
	int pos_lectura;
	// Siguiente posicion a
	int pos_escritura;
	// Siguiente posicion a
	int num_peticiones; // Número de peticiones
	peticion_t peticiones[TAM_BUFFER]; // Buffer
} buffer_peticiones;


typedef struct {
	int id_cliente; // Identificador de cliente {1...N}
	int id_compra; // Identificador de compra {1..100}
} peticion_t;
