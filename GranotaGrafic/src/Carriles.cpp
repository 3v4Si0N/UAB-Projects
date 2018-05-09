#include "Carriles.h"

Carriles::Carriles()
{}


Carriles::Carriles(int posicionY, ColaVehiclesCarril &cola)
{
	posY = posicionY;
	colaVehicles = cola;
}


Carriles::~Carriles()
{}

void Carriles::setPosY(int posicionY)
{
	posY = posicionY;
}


int Carriles::getPosY()
{
	return posY;
}


ColaVehiclesCarril Carriles::getColaVehicles()
{
	return colaVehicles;
}


void Carriles::DibujarColaVehicles()
{
	colaVehicles.DibujarColaVehicles();
}
