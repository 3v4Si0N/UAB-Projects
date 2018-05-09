#include "Vehicle.h"

/**
 * Constructor per defecte.
 */
Vehicle::Vehicle()
{
}

/**
 * Constructor del Vehicle.
 * @param grafic Grafic amb el que ilustrar el vehicle
 * @param velocitat Velocitat de moviment
 */
Vehicle::Vehicle(Grafic grafic, int velocidadVehicle, int posicionY, char directionVehicle)
{
	graficVehicle = grafic;
	velocidad = velocidadVehicle;
	posicioVehicleY = posicionY;
	direction = directionVehicle;
}

/**
 * Destructor per defecte.
 */
Vehicle::~Vehicle()
{
}

/**
 * Retorna l'area ocupada pel vehicle.
 */
Area Vehicle::getAreaVehicle()
{
	return Area(posicioVehicleX, posicioVehicleX + graficVehicle.getScaleX(), posicioVehicleY, posicioVehicleY + graficVehicle.getScaleY());
}

/**
 * Dibuixa el vehicle a la posició actual.
 */
void Vehicle::dibujarVehicle()
{
	graficVehicle.dibuixa(posicioVehicleX, posicioVehicleY);
}

/**
 * Mou el vehicle tenint en compte la velocitat d'aquest.
 */
void Vehicle::moverVehicleDer()
{
	if (getDirectionVehicle() == RIGHT_CAR && espacioPermitido(getAreaVehicle()))
		posicioVehicleX += velocidad;
	else
		moverAInicioCarril(INICI_X - getGraficVehicle().getScaleX(), posicioVehicleY);
}

void Vehicle::moverVehicleIzq()
{
	if (getDirectionVehicle() == LEFT_CAR && espacioPermitido(getAreaVehicle()))
		posicioVehicleX -= velocidad;
	else
		moverAInicioCarril(FI_X - 1, posicioVehicleY);
}


Grafic Vehicle::getGraficVehicle()
{
	return graficVehicle;
}


void Vehicle::setVelocidad(int nuevaVelocidad)
{
	velocidad = nuevaVelocidad;
}


int Vehicle::getVelocidad()
{
	return velocidad;
}


char Vehicle::getDirectionVehicle()
{
	return direction;
}

/**
 * Mou el vehicle a l'inici d'un carril.
 * @param iniciXCarril Coordenada X inicial del carril
 * @param iniciYCarril Coordenada Y del carril
 */
void Vehicle::moverAInicioCarril(int inicioXCarril, int inicioYCarril)
{
	posicioVehicleX = inicioXCarril;
	posicioVehicleY = inicioYCarril;
}


/**
* Comprova si una àrea donada es troba dins l'espai permés de moviment.
* @param area Area a comprovar si es troba dins l'espai permés de moviment.
* @return true si l'àrea es troba dins l'espai permés de moviment i false si no és així.
*/
bool Vehicle::espacioPermitido(Area area)
{
	return areaTotal.incluir(area);
}