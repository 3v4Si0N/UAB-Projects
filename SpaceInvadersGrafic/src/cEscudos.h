#ifndef CESCUDOS_H
#define CESCUDOS_H

#include <stdio.h>
#include <stdlib.h>
#include "lib/libreria.h"
#include "lib/event.h"
#include "lib/cGrafic.h"
#include <windows.h>


class cEscudos
{
private:

	int N_FILAS_ESCUDO = 3;
	int N_COLUMNAS_ESCUDO = 3;
	typedef struct{
		bool estado[3][3];
		int posX;
		int posY;
	}escudo;
	
	escudo Escudo[4];

public:

	cEscudos();
	~cEscudos();

	bool ColisionEscudosBalasCanon(int balaX, int balaY, cGrafic &graficEscudo);
	void InicializarEscudos(int posEscudoX, int posEscudoY, int escudos);
	void DibujarEscudoRoto(cGrafic graficEscudo, cGrafic &graficEscudoRoto);
	int GetPosX(int escudo);
	int GetPosY(int escudo);
	int GetNumFila();
	int GetNumCol();
	bool GetBoolEstado(int escudo, int posI, int posJ);
	void SetBoolEstado(bool estado, int escudo, int posI, int posJ);
};

#endif