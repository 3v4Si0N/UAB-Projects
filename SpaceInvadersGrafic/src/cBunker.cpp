#include "cBunker.h"



bool cBunker::EstaDestruido()
{
	return Destruido;
}

void cBunker::SetDestruido()
{
	Destruido = true;
}

void cBunker::RomperBunker()
{
	Destruido = false;
}

int cBunker::GetCoordenadaBunker(char XoY)
{
	switch (XoY)
	{
	case 'x':
		return coordenadaBunker[0];
		break;
	case 'y':
		return coordenadaBunker[1];
		break;
	}
}