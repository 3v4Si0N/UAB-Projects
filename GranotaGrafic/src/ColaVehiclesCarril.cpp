#include "ColaVehiclesCarril.h"

ColaVehiclesCarril::ColaVehiclesCarril()
{
	primero = NULL;
	ultimo = NULL;
	numElementos = 0;
}
ColaVehiclesCarril::~ColaVehiclesCarril()
{
	//Finalizar();
}

void ColaVehiclesCarril::Inicializar()
{
	primero = NULL;
	ultimo = NULL;
	numElementos = 0;
}
bool ColaVehiclesCarril::EstaVacia()
{
	return primero == NULL;
}

void ColaVehiclesCarril::Push(Vehicle &vehicle)
{
	t_nodeCola *aux = new t_nodeCola;
	aux->vehicle = vehicle;
	aux->next = NULL;

	if (EstaVacia())
	{
		primero = aux;
		ultimo = aux;
	}
	else
	{
		ultimo->next = aux;
		ultimo = aux;
	}
	numElementos++;
}
Vehicle ColaVehiclesCarril::Pop()
{
	t_nodeCola* aux;
	Vehicle auxvehicle;

	aux = primero;
	auxvehicle = aux->vehicle;

	primero = primero->next;

	delete aux;

	numElementos--;
	return auxvehicle;
}
void ColaVehiclesCarril::Finalizar()
{
	while (!EstaVacia())
	{
		Pop();
	}
}
Vehicle ColaVehiclesCarril::getPrimero()
{
	return primero->vehicle;
}


void ColaVehiclesCarril::MoverVehicleColaDer()
{
	t_nodeCola *aux;
	aux = primero;

	while (aux != NULL)
	{
		aux->vehicle.moverVehicleDer();
		aux = aux->next;
	}
}


void ColaVehiclesCarril::MoverVehicleColaIzq()
{
	t_nodeCola *aux;
	aux = primero;

	while (aux != NULL)
	{
		aux->vehicle.moverVehicleIzq();
		aux = aux->next;
	}
}


void ColaVehiclesCarril::DibujarColaVehicles()
{
	t_nodeCola *aux;
	aux = primero;

	while (aux != NULL)
	{
		aux->vehicle.dibujarVehicle();
		aux = aux->next;
	}
}


bool ColaVehiclesCarril::solaparVehicleCola(Area area)
{
	t_nodeCola *aux;
	bool solapa = false;
	aux = primero;

	while (aux != NULL && !solapa)
	{
		if (aux->vehicle.getAreaVehicle().solapar(area))
			solapa = true;
		aux = aux->next;
	}
	return solapa;
}


void ColaVehiclesCarril::setVelocidad(int velocidad)
{
	t_nodeCola *aux;
	aux = primero;

	while (aux != NULL)
	{
		aux->vehicle.setVelocidad(velocidad);
		aux = aux->next;
	}
}


Grafic ColaVehiclesCarril::getGraficVehicle()
{
	return getPrimero().getGraficVehicle();
}


int ColaVehiclesCarril::getVelocidad()
{
	return getPrimero().getVelocidad();
}


char ColaVehiclesCarril::getDirection()
{
	return getPrimero().getDirectionVehicle();
}


bool ColaVehiclesCarril::ColaTodosCochesDentro()
{
	t_nodeCola *aux;
	bool todosDentro = true;
	aux = primero;

	while (aux != NULL && todosDentro)
	{
		if (aux->vehicle.getAreaVehicle().getMinX() < INICI_X + 50 ||
			aux->vehicle.getAreaVehicle().getMaxX() > FI_X - 50)
			todosDentro = false;
		aux = aux->next;
	}
	return todosDentro;
}


int ColaVehiclesCarril::getNumVehicles()
{
	t_nodeCola *aux;
	int numVehicles = 0;
	aux = primero;

	while (aux != NULL)
	{
		numVehicles++;
		aux = aux->next;
	}
	return numVehicles;
}