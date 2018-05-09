#include "diseno.h"
#include "Joc.h"
#include <stdio.h>
#include <conio.h>
#include "lib/libreria.h"
#include "lib/event.h"
#include "lib/cGrafic.h"
#include "diseno.h"

int animStateVar = 0;
int velocidadAnimVar = 0;
const int velocidadAnimConst = 20;

void portada::inicializarImgPortada()
{
	for (int i = 0; i < tipo_portada; i++)
	{

			//concatenacion de char con int para dibujar los diferentes tipos de imagenes de los aliens
			char ImgsPortada[33];
			sprintf(ImgsPortada, "data/SpaceInvaders/Portada_%d.png", i + 1);
			ImgTipoPortada[i].Crear(ImgsPortada);
		}
	}

void portada::dibujarPortada(int posX, int posY, int tipo_portada)
{
	//Posicion donde se dibuja el alien
	int pos_x = INICI_X;
	int pos_y = INICI_Y;

	//Crea el alien
	ImgTipoPortada[tipo_portada].Dibuixar(pos_x, pos_y);
}

void portada::selectPortada(int posX, int posY)
{
	switch (portadaCoords[posX][posY].GetTipoPortada())
	{
	case PORTADA_1:
		dibujarPortada(posX, posY, PORTADA_1);
		break;
	case PORTADA_2:
		dibujarPortada(posX, posY, PORTADA_2);
		break;
	}
}

void portada::PORTADA(){
        int parpadeo = 0;
        int y = 0;
        int cont = 0;
        bool salida = false;
        bool carga = false;

		while (!salida){
			if (parpadeo < 25) dibujarPortada(posX, posY, PORTADA_1);
			else
				dibujarPortada(posX,posY,PORTADA_2);
			if (Keyboard_GetKeyTrg(KEYBOARD_KEYPAD_ENTER)){
				carga = true;
			}

			if (carga) cont++;
			if (cont > 400) salida = true;

			//Rest(5);
			system("CLS");

			if (++parpadeo == 50) parpadeo = 0;

		}
}
