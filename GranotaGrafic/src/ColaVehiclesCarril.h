#include "Vehicle.h"

const int MAX_VEHICLES_CARRIL = 1;
class ColaVehiclesCarril
{
public:

	ColaVehiclesCarril();
	~ColaVehiclesCarril();

	void Inicializar();
	void Push(Vehicle &vehicle);
	Vehicle Pop();
	bool EstaVacia();
	bool solaparVehicleCola(Area AreaSolapa);
	void Finalizar();
	void MoverVehicleColaDer();
	void MoverVehicleColaIzq();
	void DibujarColaVehicles();
	Vehicle getPrimero();
	int getVelocidad();
	void setVelocidad(int velocidad);
	char getDirection();
	Grafic getGraficVehicle();
	bool ColaTodosCochesDentro();
	int getNumVehicles();

private:

	typedef struct nodeCola
	{
		Vehicle vehicle;
		struct nodeCola *next;
	} t_nodeCola;

	t_nodeCola *primero;
	t_nodeCola *ultimo;
	int numElementos;
};