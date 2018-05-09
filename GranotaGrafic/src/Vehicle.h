#pragma once
#include "Area.h"
#include "lib\Grafic.h"

// nº de píxels que es desplaça el cotxe a cada moviment del nivell 1
#define DESPLACAMENT_COTXE1 2
#define DESPLACAMENT_COTXE2 3
#define DESPLACAMENT_COTXE3 1
#define DESPLACAMENT_CAMIO 2
#define DESPLACAMENT_TRACTOR 3

#define INICI_X 0
#define FI_X 600

const char RIGHT_CAR = 'R';
const char LEFT_CAR = 'L';

class Vehicle
{
public:

	Vehicle();
	Vehicle(Grafic graficVehicle, int velocidadVehicle, int posicioVehicleY, char directionVehicle);
	~Vehicle();

	Area getAreaVehicle();
	void dibujarVehicle();
	void moverVehicleDer();
	void moverVehicleIzq();
	void moverAInicioCarril(int inicioXCarril, int inicioYCarril);
	Grafic getGraficVehicle();
	char getDirectionVehicle();
	void SetDirectionVehicle(char direction);
	void setVelocidad(int nuevaVelocidad);
	int getVelocidad();

private:

	Grafic graficVehicle;
	Area areaTotal = Area(INICI_X - 80, FI_X + 90, 0, 450);

	int posicioVehicleX;
	int posicioVehicleY;
	int velocidad;
	char direction;

	bool espacioPermitido(Area area);
};

