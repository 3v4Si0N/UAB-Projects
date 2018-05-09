#include "Mosca.h"

Mosca::Mosca()
{
}


Mosca::Mosca(Grafic m_graficMosca, int posicionMinY, int posicionMaxY)
{
	graficMosca = m_graficMosca;
	posicionInicialX = rand() % (FIN_X - graficMosca.getScaleX());
	posicionInicialY = (rand() % (FIN_Y - posicionMinY - posicionMaxY )) + posicionMinY;
}


Mosca::~Mosca()
{
}


Area Mosca::getAreaMosca()
{
	return Area(posicionMoscaX, posicionMoscaX + graficMosca.getScaleX(), posicionMoscaY, posicionMoscaY + graficMosca.getScaleY());
}


void Mosca::dibujarMosca()
{
	graficMosca.dibuixa(posicionMoscaX, posicionMoscaY);
}


void Mosca::moverAPosicionInicial()
{
	posicionMoscaX = posicionInicialX;
	posicionMoscaY = posicionInicialY;
}