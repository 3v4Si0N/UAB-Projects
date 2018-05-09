#include "ColaVehiclesCarril.h"

const int N_MAX_CARRILES = 5;

class Carriles
{
public:

	Carriles();
	Carriles(int posicionY, ColaVehiclesCarril &cola);
	~Carriles();

	void setPosY(int posicionY);
	int getPosY();
	void DibujarColaVehicles();
	ColaVehiclesCarril getColaVehicles();

private:

	int posY;
	Vehicle vehicle;
	ColaVehiclesCarril colaVehicles;
};