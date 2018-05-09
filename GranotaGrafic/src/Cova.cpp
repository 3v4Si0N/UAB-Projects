#include "Cova.h"

/**
 * Constructor per defecte.
 */
Cova::Cova()
{
}

/**
 * Constructor de la cova.
 * Nota: Aquesta cova és imaginària. El seu interior ocupa tota l'amplada de la pantalla, no té cap paret que faci d'obstacle per accedir-hi.
 * @param grafic Grafic ilustrant la cova
 * @param posicioX Coordenada horitzontal de la cova
 * @param posicioY Coordenada vertical de la cova
 */
Cova::Cova(Grafic grafic, int posicioX, int posicioY)
{
	m_posicioX = posicioX;
	m_posicioY = posicioY;
	graficCueva = grafic;
	btaken = false;

	m_interior = Area(m_posicioX, m_posicioX + graficCueva.getScaleX(), m_posicioY, m_posicioY + graficCueva.getScaleY() + 5);
	paredIzqMedio = Area(m_posicioX, m_posicioX, m_posicioY, m_posicioY + graficCueva.getScaleY() + 10);
	paredDerMedio = Area(m_posicioX + graficCueva.getScaleX(), m_posicioX + graficCueva.getScaleX(), m_posicioY, m_posicioY + graficCueva.getScaleY() + 10);
	paredIzqInterna = Area(m_posicioX, m_posicioX + 15, m_posicioY, m_posicioY + graficCueva.getScaleY());
	paredDerInterna = Area(m_posicioX + graficCueva.getScaleX() - 20, m_posicioX + graficCueva.getScaleX(), m_posicioY, m_posicioY + graficCueva.getScaleY());
}

/**
 * Destructor per defecte.
 */
Cova::~Cova(void)
{
}

/**
 * Dibuixa la cova a la seva posició.
 */
void Cova::DibujarCueva()
{
	graficCueva.dibuixa(m_posicioX, m_posicioY);
}

/**
 * Comprova si una àrea donada no es solapa amb cap paret de la cova.
 * @param area Area a comprovar
 * @returns true si l'àrea no es solapa amb cap parets de la cova.
 */
bool Cova::esAccesible(Area area)
{
	bool esAccesible = true;

	if ((area.solapar(paredIzqMedio) || area.solapar(paredDerMedio)) || (area.solapar(m_interior) && getTaken()))
		esAccesible = false;

	return esAccesible;
}

/**
 * Comprova si l'àrea donada es troba totalment a l'interior de la cova.
 * @param area Area a comprovar
 * @returns true si l'àrea es troba totalment dins la cova i false si no és així.
 */
bool Cova::EstaDentro(Area area)
{
	return m_interior.incluir(area);
}


bool Cova::getTaken()
{
	return btaken;
}


void Cova::setTaken(bool newbtaken)
{
	btaken = newbtaken;
}


int Cova::getPosX()
{
	return m_posicioX;
}


bool Cova::CuevaParteIzq(Area area)
{
	bool golpea = false;
	if (area.solapar(paredIzqInterna))
		golpea = true;
	return golpea;
}

bool Cova::CuevaParteDer(Area area)
{
	bool golpea = false;
	if (area.solapar(paredDerInterna))
		golpea = true;
	return golpea;
}