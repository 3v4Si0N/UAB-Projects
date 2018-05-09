#ifndef CLISTABALAS_H
#define CLISTABALAS_H

#include "cBala.h"
#include "cEscudos.h"
#include "cMatrizAliens.h"
#include <stdio.h>
#include <stdlib.h>
#include "lib/libreria.h"
#include "lib/event.h"
#include "lib/cGrafic.h"
#include <windows.h>


typedef struct nodoLista
{
	cBala valor;
	struct nodoLista *next;
}t_nodoLista;

typedef t_nodoLista* t_posicionLista;
const t_posicionLista POSICIO_NULL=NULL;


class cListaBalas
{
private:

	t_nodoLista *primero;
	t_nodoLista *posActual;
	t_nodoLista *posAnterior;

public:

	cListaBalas();
	~cListaBalas();

	void Inicializar();
	void Finalizar();
	bool EstaVacio();

	t_posicionLista InicializarRecorrido();
	t_posicionLista Next();
	t_nodoLista* GetPosActual();

	void InsertarPosicion(cBala &Bala);
	void MoverBalas(int graficBalaY);
	void ColisionCanon(int canoX, int canoY, int &vidas, int graficBalaY, int graficCanoX, bool &canonMuerto, cGrafic graficCanonMuerto);
	bool ColisionEscudosBalasAliens(cGrafic &graficEscudo, cEscudos &escudos, int graficBalaY);
	bool FinalRecorrido();
	cBala GetActual();
	void SetActual(cBala &Bala);
	void Eliminar();
};

#endif