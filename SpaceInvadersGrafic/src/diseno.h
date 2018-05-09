#include "lib/cGrafic.h"
#include "cAlien.h"
#include "cMtxAliens.h"

#ifndef DISENO_H_INCLUDED
#define DISENO_H_INCLUDED

class portada{
private:

	//Inicializacion del numero de aliens y el numero de imagenes de cada tipo
	static const int tipo_portada = 2; // Numero de tipo de portada
	static const int tipo_instruc = 2; //Numero de tipo de instrucciones
	static const int posX = INICI_X;
	static const int posY = INICI_Y;

	cGrafic ImgTipoPortada[tipo_portada];
	cGrafic ImgTipoInstruc[tipo_instruc];
	cAlien portadaCoords[posX][posY];

	//ENUMERACION
	typedef enum{
		PORTADA_1, PORTADA_2, INSTRUCCION_1, INSTRUCCION_2
	};



public:

	void PORTADA();
	void inicializarImgPortada();
	void dibujarPortada(int posX, int posY, int tipo_portada);
	void selectPortada(int posX, int posY);
	int estadoAnim(int &animStateVar, int &velocidadAnimVar);

	//SET and GET
	
};



#endif // DISENO_H_INCLUDED
