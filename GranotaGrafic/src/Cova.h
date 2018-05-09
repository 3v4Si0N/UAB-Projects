#pragma once
#include "Area.h"
#include "lib\Grafic.h"

#define BORDE_COVA 20
const int N_MAX_CUEVAS = 5;

class Cova
{
public:

	Cova();
	Cova(Grafic grafic, int posicioX, int posicioY);
	~Cova();
	void DibujarCueva();
	bool esAccesible(Area area);
	bool EstaDentro(Area area);
	bool getTaken();
	void setTaken(bool btaken);
	int getPosX();
	bool CuevaParteIzq(Area area);
	bool CuevaParteDer(Area area);

private:

	int m_posicioX;
	int m_posicioY;
	bool btaken;

	Grafic graficCueva;
	Area m_interior;
	Area paredIzqMedio;
	Area paredDerMedio;
	Area paredIzqInterna;
	Area paredDerInterna;
};

