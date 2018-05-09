#include "cListaBalas.h"


//===================================================================================
// NOM: Constructor & Destructor
//===================================================================================
cListaBalas::cListaBalas()
{
	Inicializar();
}

cListaBalas::~cListaBalas()
{
	Finalizar();
}


//===================================================================================
// NOM: Inicializar
//===================================================================================
// DESCRIPCI�: Inicializa la lista
//===================================================================================
void cListaBalas::Inicializar()
{
	primero = NULL;
	posActual = NULL;
	posAnterior = NULL;
}


//===================================================================================
// NOM: Eliminar
//===================================================================================
// DESCRIPCI�: Elimina la lista
//===================================================================================
void cListaBalas::Eliminar()
{
	t_nodoLista *aux;
	//Caso 1 donde la bala est� en la primera posici�n de la lista
	if ((posActual == primero) && (posAnterior == NULL))
	{
		aux = primero;
		primero = primero->next;
		posActual = NULL;
	}
	//Caso 2 donde la bala est� en el medio de la lista
	else if ((posActual->next == NULL) && (posAnterior != NULL)){
		aux = posActual;
		posAnterior->next = NULL;
		posActual = NULL;
	}
	//Caso 3 donde la bala est� en la �ltima posici�n de la lista
	else if ((posActual->next != NULL) && (posAnterior != NULL))
	{
		aux = posActual;
		posAnterior->next = posActual->next;
		posActual = posActual->next;
	}
	delete aux;
}


//===================================================================================
// NOM: Finalizar
//===================================================================================
// DESCRIPCI�: Finaliza la lista
//===================================================================================
void cListaBalas::Finalizar()
{
	posAnterior = NULL;
	posActual = primero;
	while(primero != NULL)
	{
		Eliminar();
		posActual = primero;
	}
}


//===================================================================================
// NOM: EstaVacio
//===================================================================================
// DESCRIPCI�: Para saber si la lista est� vac�a
//===================================================================================
bool cListaBalas::EstaVacio()
{
	return(primero == NULL);
}


//===================================================================================
// NOM: InicializarRecorrido
//===================================================================================
// DESCRIPCI�: Inicializa el recorrido de la lista
//===================================================================================
t_posicionLista cListaBalas::InicializarRecorrido()
{
	posAnterior = NULL;
	posActual = primero;
	return posActual;
}


//===================================================================================
// NOM: Next
//===================================================================================
// DESCRIPCI�: Apunta al siguiente de la lista
//===================================================================================
t_posicionLista cListaBalas::Next()
{
	posAnterior = posActual;
	posActual = posActual->next;
	return posActual;
}


//===================================================================================
// NOM: FinalRecorrido
//===================================================================================
// DESCRIPCI�: Para saber que se ha terminado
//===================================================================================
bool cListaBalas::FinalRecorrido()
{
	return (posActual == NULL);
}


//===================================================================================
// NOM: InsertarPosicion
//===================================================================================
// DESCRIPCI�: Inserta un elemento en la lista al principio
//===================================================================================
void cListaBalas::InsertarPosicion(cBala &Bala)
{
	t_nodoLista *nuevo;
	
	
	nuevo = new t_nodoLista;
	nuevo->valor = Bala;
	
	nuevo->next = primero;
	primero = nuevo;
}


//===================================================================================
// NOM: SetActual
//===================================================================================
// DESCRIPCI�: Poner la posici�n actual de la bala
//===================================================================================
void cListaBalas::SetActual(cBala &Bala)
{
	posActual->valor = Bala;
}


//===================================================================================
// NOM: GetActual
//===================================================================================
// DESCRIPCI�: Recupera la posici�n actual de la bala
//===================================================================================
cBala cListaBalas::GetActual()
{
	return posActual->valor;
}


//===================================================================================
// NOM: GetPosActual
//===================================================================================
// DESCRIPCI�: Devuelve la variable poActual
//===================================================================================
t_nodoLista* cListaBalas::GetPosActual()
{
	return posActual;
}


//===================================================================================
// NOM: MoverBalas
//===================================================================================
// DESCRIPCI�: Mueve las balas de los enemigos
//===================================================================================
void cListaBalas::MoverBalas(int velocidadBalas)
{
	velocidadBalas = 5;
	posActual = primero;
	while (posActual != NULL)
	{
		posActual->valor.SetY(posActual->valor.GetY() + velocidadBalas);
		posActual = posActual->next;
	}
}


//===================================================================================
// NOM: Colisi�nCa�on
//===================================================================================
// DESCRIPCI�: Compueba que las balas de los enemigos chocan con el ca��n
//===================================================================================
void cListaBalas::ColisionCanon(int canoX, int canoY, int &vidas, int graficBalaY, int graficCanoX, bool &canonMuerto, cGrafic graficCanonMuerto)
{
	canonMuerto = false;
	posActual = primero;
	posAnterior = NULL;
	while (posActual != NULL)
	{
		//Comprobaci�n de la colisi�n de las balas con el ca��n
		if ((posActual->valor.GetY() + graficBalaY >= canoY) &&
			(posActual->valor.GetX() >= canoX) &&
			(posActual->valor.GetX() <= canoX + graficCanoX))
		{
			canonMuerto = true;
			vidas--;
			Eliminar();
		}
		//Comprobaci�n de la colisi�n de las balas con el l�mite inferior
		else if (posActual->valor.GetY() + graficBalaY >= 550)
		{
			Eliminar();
		}

		if (posActual != NULL)
		{
			posAnterior = posActual;
			posActual = posActual->next;
		}
	}
}


bool cListaBalas::ColisionEscudosBalasAliens(cGrafic &graficEscudo, cEscudos &escudos, int graficBalaY)
{
	int h = 0, i = 0, j = 0;
	bool colisionBala = false;
	posActual = primero;
	posAnterior = NULL;

	while (posActual != NULL)
	{
		/*for (int h = 0; h < 4; h++)
		{
			for (int i = 0; i < escudos.GetNumFila(); i++)
			{
				for (int j = 0; j < escudos.GetNumCol(); j++)
				{
					if (escudos.GetBoolEstado(h, i, j) == true)
					{
						//Comprobaci�n de la colisi�n de las balas de los enemigos con los escudos
						if ((posActual->valor.GetX() > escudos.GetPosX(h) + j * (graficEscudo.getScaleX() / 3)) &&
							(posActual->valor.GetX() < escudos.GetPosX(h) + (graficEscudo.getScaleX() / 3) * (j+1)) &&
							(posActual->valor.GetY() > escudos.GetPosY(h) + i * (graficEscudo.getScaleY() / 3)))
						{
							escudos.SetBoolEstado(false, h, j, i); 
							colisionBala = true;

						}
					}
				}
				if (colisionBala)
				{
					Eliminar();
				}
			}
		}
		*/

		while (!colisionBala && h < 4)
		{
			i = 0;
			while (!colisionBala && i < escudos.GetNumFila())
			{
				j = 0;
				while (!colisionBala && j < escudos.GetNumCol())
				{
					if (escudos.GetBoolEstado(h, i, j) == true)
					{
						if ((posActual->valor.GetX() >= escudos.GetPosX(h) + j * (graficEscudo.getScaleX() / 3)) &&
							(posActual->valor.GetX() <= escudos.GetPosX(h) + (graficEscudo.getScaleX() / 3) * (j + 1)) &&
							((posActual->valor.GetY() + graficBalaY) >= escudos.GetPosY(h) + i * (graficEscudo.getScaleY() / 3)))
						{
							escudos.SetBoolEstado(false, h, i, j);
							colisionBala = true;
							Eliminar();
						}

					}
					j++;
				}
				i++;
			}
			h++;
		}
		if (posActual != NULL)
		{
			posAnterior = posActual;
			posActual = posActual->next;
		}
	}
	
	return colisionBala;
}


