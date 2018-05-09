#include "cAlien.h"

//===================================================================================
// NOM: Constructor y Destructor
//===================================================================================
cAlien::cAlien()
{
}
cAlien::~cAlien()
{
}


//===================================================================================
// NOM: SetAlienType
//===================================================================================
// DESCRIPCIÓ: Selecciona el tipo de alien
//===================================================================================
void cAlien::SetTipoAlien(int tipo)
{
	TipoAlien = tipo;
}


//===================================================================================
// NOM: GetAlienType
//===================================================================================
// DESCRIPCIÓ: Devuelve el tipo de alien
//===================================================================================
int cAlien::GetTipoAlien()
{
	return TipoAlien;
}


//===================================================================================
// NOM: SetVivo
//===================================================================================
// DESCRIPCIÓ: Pone una variable a true para poner al alien vivo
//===================================================================================
void cAlien::SetVivo()
{
	Vivo_Muerto = true;
}


//===================================================================================
// NOM: EstaVivo
//===================================================================================
// DESCRIPCIÓ: Devuelve la variable Vivo_Muerto para saber si esta vivo o muerto.
//            (Si devuelve true es que esta vivo y si devuelve false, esta muerto)
//===================================================================================
bool cAlien::EstaVivo_Muerto()
{
	return Vivo_Muerto;
}


//===================================================================================
// NOM: MatarAlien
//===================================================================================
// DESCRIPCIÓ: Pone variable a falso para saber que el alien esta muerto
//===================================================================================
void cAlien::MatarAlien()
{
	Vivo_Muerto = false;
}


//===================================================================================
// NOM: SetCoordenadaAlien
//===================================================================================
// DESCRIPCIÓ: Selecciona la coordenada X e Y de las coordenadas de la matriz de aliens
//===================================================================================
void cAlien::SetCoordenadaAlien(int coordenada_X, int coordenada_Y)
{
	coordenadaAlien[0] = coordenada_X;
	coordenadaAlien[1] = coordenada_Y;
}


//===================================================================================
// NOM:  GetCoordenadaAlien
//===================================================================================
// DESCRIPCIÓ: Devuelve la coordenada X o la Y dependiendo del char x, y
//===================================================================================
int cAlien::GetCoordenadaAlien(char XoY)
{
	switch (XoY)
	{
	case 'x':
		return coordenadaAlien[0];
		break;
	case 'y':
		return coordenadaAlien[1];
		break;
	}
}
