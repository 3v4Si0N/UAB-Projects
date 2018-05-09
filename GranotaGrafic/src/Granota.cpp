#include "Granota.h"

/**
 * Constructor per defecte.
 */
Granota::Granota()
{
}

/**
 * Constructor de la Granota.
 * @param grafic Grafic amb el que ilustrar la granota
 * @param posicioInicialX Posició inicial en coordenada horitzontal
 * @param posicioInicialY Posició inicial en coordenada vertical
 */
Granota::Granota(Grafic grafic, int posicionInicialX, int posicionInicialY)
{
	graficGranota = grafic;
	posicioInicialX = posicionInicialX;
	posicioInicialY = posicionInicialY;
}

/**
 * Destructor per defecte.
 */
Granota::~Granota()
{
}

/**
 * Obté l'àrea ocupada per la granota.
 * @return Area ocupada
 */
Area Granota::getAreaGranota()
{
	return Area(posicioGranotaX, posicioGranotaX + graficGranota.getScaleX(), posicioGranotaY, posicioGranotaY + graficGranota.getScaleY() + 5);
}

/**
 * Dibuixa la granota a la posició actual.
 */
void Granota::dibujarGranota()
{
	graficGranota.dibuixa(posicioGranotaX, posicioGranotaY);
}

/**
 * Mou la granota cap a l'esquerra.
 */
void Granota::moverIzquierda()
{
	posicioGranotaX -= DESPLACAMENT_GRANOTA;
}

/**
 * Mou la granota cap a la dreta.
 */
void Granota::moverDerecha()
{
	posicioGranotaX += DESPLACAMENT_GRANOTA;
}

/**
 * Mou la granota cap amunt.
 */
void Granota::moverArriba()
{
	posicioGranotaY -= DESPLACAMENT_GRANOTA;
}

/**
 * Mou la granota cap avall.
 */
void Granota::moverAbajo()
{
	posicioGranotaY += DESPLACAMENT_GRANOTA;
}

/**
 * Mou la granota a la seva posició inicial.
 */
void Granota::moverAPosicionInicial()
{
	posicioGranotaX = posicioInicialX;
	posicioGranotaY = posicioInicialY;
}


void Granota::setGrafic(Grafic newGrafic)
{
	graficGranota = newGrafic;
}
