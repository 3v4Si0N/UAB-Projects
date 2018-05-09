#pragma once
#include "Area.h"
#include "lib\Grafic.h"

// nº de píxels que es desplaça la granota a cada moviment
#define DESPLACAMENT_GRANOTA 10

class Granota
{
public:

	Granota();
	Granota(Grafic graficGranota, int posicioInicialX, int posicioInicialY);
	~Granota();

	Area getAreaGranota();
	void dibujarGranota();
	void moverArriba();
	void moverAbajo();
	void moverDerecha();
	void moverIzquierda();
	void moverAPosicionInicial();
	void setGrafic(Grafic grafic);

private:

	Grafic graficGranota;
	int posicioGranotaX;
	int posicioGranotaY;
	int posicioInicialX;
	int posicioInicialY;
};

