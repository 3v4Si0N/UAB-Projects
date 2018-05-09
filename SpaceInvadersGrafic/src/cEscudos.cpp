#include "cEscudos.h"


cEscudos::cEscudos()
{
}

cEscudos::~cEscudos()
{
}


void cEscudos::InicializarEscudos(int posEscudoX, int posEscudoY, int escudos)
{
	for (int h = 0; h < escudos; h++)
	{
		Escudo[h].posX = posEscudoX;
		Escudo[h].posY = posEscudoY;
		posEscudoX += 200;

		for (int i = 0; i < N_FILAS_ESCUDO; i++)
		{
			for (int j = 0; j < N_COLUMNAS_ESCUDO; j++)
			{
				Escudo[h].estado[i][j] = true;
			}
		}
	}
	
}




bool cEscudos::ColisionEscudosBalasCanon(int balaX, int balaY, cGrafic &graficEscudo)
{
	bool colisionBala = false;
	for (int h = 0; h < 4; h++)
	{
		for (int i = 0; i < N_FILAS_ESCUDO; i++)
		{
			for (int j = 0; j < N_COLUMNAS_ESCUDO; j++)
			{
				if (Escudo[h].estado[i][j] == true)
				{
					//Comprobación de la colisión de las balas de los enemigos con los escudos
					
						if ((balaX > Escudo[h].posX + j * graficEscudo.getScaleX() / 3) &&
							(balaX < Escudo[h].posX + (j + 1) * graficEscudo.getScaleX() / 3) &&
							(balaY < Escudo[h].posY + (i + 1) * graficEscudo.getScaleY() / 3))
						{
							Escudo[h].estado[i][j] = false;
							colisionBala = true;
						}
					
				}
			}
		}
	}
	return colisionBala;
}


void cEscudos::DibujarEscudoRoto(cGrafic graficEscudo, cGrafic &graficEscudoRoto)
{
	for (int h = 0; h < 4; h++)
	{
		for (int i = 0; i < N_FILAS_ESCUDO; i++)
		{
			for (int j = 0; j < N_COLUMNAS_ESCUDO; j++)
			{
				if (Escudo[h].estado[i][j] == false)
					graficEscudoRoto.Dibuixar(Escudo[h].posX + (j * graficEscudo.getScaleX() / 3), Escudo[h].posY + (i * graficEscudo.getScaleY() / 3));
			}
		}
	}
}


int cEscudos::GetPosX(int escudo)
{
	return Escudo[escudo].posX;
}


int cEscudos::GetPosY(int escudo)
{
	return Escudo[escudo].posY;
}



int cEscudos::GetNumFila()
{
	return N_FILAS_ESCUDO;
}


int cEscudos::GetNumCol()
{
	return N_COLUMNAS_ESCUDO;
}


bool cEscudos::GetBoolEstado(int escudo, int posI, int posJ)
{
	return Escudo[escudo].estado[posI][posJ];
}

void cEscudos::SetBoolEstado(bool estado, int escudo, int posI, int posJ)
{	
	Escudo[escudo].estado[posI][posJ]= estado;
}