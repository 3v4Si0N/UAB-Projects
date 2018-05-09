#ifndef GESTIORESULTATS_H
#define GESTIORESULTATS_H

#include <string.h>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include "extras.h"
#include <conio.h>
#include <windows.h>
using namespace std;

static const int MAX_NOM_JUGADOR = 15;
static const int MAX_MILLORSJUGADORS = 5;

class GestionResult
{

private:

	static const char* PuntuacionesSpace;

	// Registro que sirve para guardar la puntuacion de un jugador
	struct
	{
		char nom[MAX_NOM_JUGADOR];
		int puntuacio;
	}millorsJugadors[MAX_MILLORSJUGADORS];




public:

	//Constructor y destructor
	GestionResult();
	~GestionResult();

	//Funciones
	int EsMillorPuntuacio(int punts);
	void DesplacarArray(int posicio);
	void EmplenarPosicioArray(int posicion, int puntos);
	void EscriuRanking();
	void EscribirPuntuaciones();
	void LeerPuntuacion();
};

#endif