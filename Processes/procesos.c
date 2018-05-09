#define READ 0
#define WRITE 1
#include <unistd.h> //fork(),...
#include <stdio.h> //printf(),...
#include <stdlib.h> //exit(),...
#include <sys/types.h>
#include <sys/wait.h> //wait(),...
#include <string.h>
#include <fcntl.h>
#include <time.h>

int main(int argc, char** argv)
{

	pid_t pid_estacion;
	int nEstaciones = 10, analisis, status, i, j, tiempo = 2;
	int estacion[nEstaciones], pipes[nEstaciones][2];
	srand(time(NULL)); // randomizará nuevamente cada vez que el programa se ejecute
	int nRandMedidas = 1 + rand() % 10;
	double media = 0.0;
	char string[50];
	FILE *rd, *wr;


	rd = fopen("estaciones", "r"); // creamos un fd, el cual sólo se va a usar para leer
	wr = fopen("medidas", "w"); // creamos un fd, el cual sólo se va a usar para escribir

	for(i = 0; i < nEstaciones; i++) // hacemos un for para crear tantos pipes como sean necesarios
	{
		if(pipe(pipes[i]) < 0) // creamos los pipes y si ha habido algún error al crear alguno, lo notificamos
		{
			perror("Error al crear el pipe %d\n"); // notificamos que ha habido algún error a la hora de crear algún pipe
			exit(EXIT_FAILURE); // acaba mal
		}
	}



	printf("Soy el proceso padre \n");
	printf("Numero de medidas: %d\n", nRandMedidas); // mostramos por pantalla el número de medidas que ha creado el padre


	for(i = 0; i < nEstaciones; i++) // hacemos un for para crear tantos procesos hijos (estaciones) como sean necesarios
	{
		pid_estacion = fork();
		if(pid_estacion == 0) // si la llamada fork() == 0, significa que somos uno de los hijos
		{
                        read(pipes[i][READ], &string, sizeof(string)); // cada hijo leerá por la parte del pipe de lectura, la línea que le ha pasado el padre
			srand(i);

			for(j = 0; j < nRandMedidas; j++) // hacemos un for para crear "nRandMedidas" de medidas que serán numeros aleatorios entre 0 y 50
			{
				double medida = (double) (rand() % 50); //transformamos la randomización en un double con el cast
				media += medida; // hacemos la suma de cada medida tomada y la guardamos en media
				printf("N[%d][%d] = %.2f\n", i, j, medida); // printamos por pantalla cada medida
			}

			media = media/(double)nRandMedidas; // hacemos un cast de rRandMedidas a double para transformar el integer a double, para poder hacer la media de cada estación correctamente
			printf("media[%d] = %.2f\n", i, media); // printamos por pantalla la media de cada estación
			printf("Soy la estacion %d y dormire %d segundos \n", i+1, tiempo); // printamos por pantalla el tiempo que dormirá cada estación
			sleep(tiempo); // cada proceso dormirá 10 segundos

			write(pipes[i][WRITE], &string, sizeof(string)); // escribimos por el lado del pipe escritura cada linea del archivo y se la pasamos al proceso análisis
			write(pipes[i][WRITE], &media, sizeof(double)); // escribimos por el lado del pipe escritura cada media tomada de cada estación y se la pasamos al proceso análisis
			exit(EXIT_SUCCESS); // salida exitosa
		}
		else
		{ // somos el padre
			if(pid_estacion == -1) // si la llamada al fork() == -1 significa que ha ocurrido un error a la hora de crear algún hijo
			{
				perror("Error al crear uno de los hijos %d"); // se muestra por pantalla qué hijo no se ha podido crear
				exit(EXIT_FAILURE); // acaba mal
			}

			fgets(string, sizeof(string), rd); // lee línea a línea del archivo "estaciones"
			write(pipes[i][WRITE], string, sizeof(string)); // escribe en el lado del pipe escritura, cada línea del archivo que ha leido anteriormente
			printf("Escribimos en el pipe %d la linea %d del archivo estaciones \n", i+1, i+1);
			wait(&status); // esperamos a todos los hijos para acabar
		}
	}


	if(analisis = fork() == 0)
	{
		//Somos el hijo analisis
		fprintf(wr, "<TempMedia><NumEstacion><NomEstacion> \n\n");

		for(i = 0; i < nEstaciones; i++)
		{
			read(pipes[i][READ], &string, sizeof(string)); // leemos por la parte del pipe lectura, cada línea del archivo "estaciones"
			read(pipes[i][READ], &media, sizeof(double)); // leemos por la parte del pipe de lectura, cada media que ha creado cada estación
			fprintf(wr, "<%.2f>%s", media, string); // Escribimos en el archivo "medidas", cada media con su respectiva estación
		}

		exit(EXIT_SUCCESS); // salida exitosa
	}
	else
	{ // Somos el padre
		if(analisis == -1) // si la llamada al fork == -1 significa que ha ocurrido un error al crear el proceso análisis
		{
			perror("Error al crear el analisis"); // notificamos el error por pantalla
			exit(EXIT_FAILURE); // acaba mal
		}


		for(i = 0; i < nEstaciones; i++) // Cerramos todos los pipes
		{
			close(pipes[i][READ]);
			close(pipes[i][WRITE]);
		}
	}
	return 0;
}
