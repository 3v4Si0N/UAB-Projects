#include "cBala.h"

//===================================================================================
// NOM: Constructor & Destructor
//===================================================================================
cBala::cBala()
{
}

cBala::~cBala()
{
}

int cBala::GetX()
{
 	return posX;
}

int cBala::GetY()
{
	return posY;
}

void cBala::SetX(int FposX)
{
	posX = FposX;
}

void cBala::SetY(int FposY)
{
	posY = FposY;
}
