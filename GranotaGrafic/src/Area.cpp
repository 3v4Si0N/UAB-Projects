#include "Area.h"

/**
 * Constructor per defecte.
 */
Area::Area()
{
}

/**
 * Constructor d'una �rea rectangular no inclinada.
 * @param minX M�nima coordenada horitzontal
 * @param maxX M�xima coordenada horitzontal
 * @param minY M�nima coordenada vertical
 * @param maxY M�xima coordenada vertical
 */
Area::Area(int minX, int maxX, int minY, int maxY)
{
	m_minX = minX;
	m_maxX = maxX;
	m_minY = minY;
	m_maxY = maxY;
}

/**
 * Destructor per defecte.
 */
Area::~Area()
{
}

/**
 * Obt� la m�nima coordenada horitzontal.
 * @return m�nima coordenada horitzontal
 */
int Area::getMinX()
{
	return m_minX;
}

/**
 * Obt� la m�xima coordenada horitzontal.
 * @return m�xima coordenada horitzontal
 */int Area::getMaxX()
{
	return m_maxX;
}

/**
 * Obt� la m�nima coordenada vertical.
 * @return m�nima coordenada vertical
 */
int Area::getMinY()
{
	return m_minY;
}

/**
 * Obt� la m�xima coordenada vertical.
 * @return m�xima coordenada vertical
 */
int Area::getMaxY()
{
	return m_maxY;
}

/**
 * Comprova si un punt donat pertany a l'area.
 * @param x Coordenada horitzontal del punt
 * @param y Coordenada vertical del punt
 * @return true si hi pertany i false si no hi pertany.
 */
bool Area::pertenece(int x, int y)
{
	return ((x <= getMaxX() && x >= getMinX()) && (y <= getMaxY() && y >= getMinY()));
}

/**
 * Comprova si una �rea donada est� inclosa dins l'�rea.
 * @param area L'�rea donada que es vol comprovar.
 * @return true si l'�rea donada est� inclosa, false si no.
 */
bool Area::incluir(Area area)
{
	return (pertenece(area.getMinX(), area.getMinY()) && pertenece(area.getMaxX(), area.getMaxY()));
}

/**
 * Comprova si l'�rea donada es solapa amb l'�rea.
 * Nota: dues �rees se solapen si la intersecci� entre elles no �s buida.
 * Assumpci�: El cas en qu� dos rectangles formen una creu no cal que el tingueu en compte, no es donar� en aquest projecte.
 * @param area L'�rea a comprovar
 * @return true si es solapen i false si no.
 */
bool Area::solapar(Area area)
{
	return (incluir(area) ||
		(pertenece(area.getMinX(), area.getMinY())) || (area.pertenece(getMinX(), getMinY())) ||
		(pertenece(area.getMaxX(), area.getMinY())) || (pertenece(area.getMinX(), area.getMaxY())));
}