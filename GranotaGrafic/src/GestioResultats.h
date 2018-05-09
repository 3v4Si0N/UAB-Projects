#ifndef GESTIORESULTADOS_H
#define GESTIORESULTADOS_H

#include <string.h>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include "extras.h"
#include <conio.h>
#include <windows.h>

using namespace std;

static const int MAX_NOMBRE_JUGADOR = 15;
static const int MAX_MEJORESJUGADORES = 5;

class GestionResultados
{

private:

	static const char* PuntuacionesFrogger;

	// Registro que sirve para guardar la puntuacion de un jugador
	struct
	{
		char nombre[MAX_NOMBRE_JUGADOR];
		int puntuacion;
	}TmejoresJugadores[MAX_MEJORESJUGADORES];




public:

	//Constructor y destructor
	GestionResultados();
	~GestionResultados();

	//Funciones
	int haMejoradoPuntuacion(int puntos);
	void desplazarArray(int posicion);
	void rellenarPosicioArray(int posicion, int puntos);
	void escribirRanking();
	void leerPuntuacion();
	void escribirPuntuacion();
};

#endif
