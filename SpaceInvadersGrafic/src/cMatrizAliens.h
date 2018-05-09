#ifndef CMATRIZALIENS_H
#define CMATRIZALIENS_H

#include "lib/cGrafic.h"
#include "cAlien.h"


class cMatrizAliens
{

private:

	//Inicializacion de variables de velocidad de matriz
	int Velocidad_Matriz_X = 1;
	int Velocidad_Matriz_Y = 7;

	//Inicializacion del numero de filas y columnas de la matriz
	static const int num_fila = 5;
	static const int num_col = 11;

	cAlien m_Aliens[num_fila][num_col];

	//Inicializacion del numero de aliens y el numero de imagenes de cada tipo
	static const int tipo_Alien = 3; // Numero de tipo de aliens
	static const int tipo_Img = 2; // Numero de imagenes de alien

	cGrafic imgAlien[tipo_Alien][tipo_Img+2];

	//Espacio entre cada enemigo
	const int espacio_Alien_X = 10;
	const int espacio_Alien_Y = 7;

	//Velocidad constante entre cada animacion
	const int velocidadAnimConst = 25;

	//ENUMERACION
	typedef enum{
		ALIEN_1, ALIEN_2, ALIEN_3
	};


	//Guarda la posicion actual de la matriz
	int matriz_posX;
	int matriz_posY;

	//Variables de la posicion de la matriz
	int ancho_m, alto_m, col_izq_lateral_muerta, col_der_lateral_muerta;

public:

	//Constructor y destructor
	cMatrizAliens(void);
	~cMatrizAliens(void);

	//Metodos de inicializacion de la matriz y de las imagenes de los enemigos
	void inicializarMatrizAliens();
	void inicializarImgAlien();
	void RevivirMatrizAliens();

	//Metodos del main
	void DatosMatriz();
	void seleccionarAlien(int fila, int col, int estadoAnim);
	int estadoAnim(int &animacion_e, int &velocidad_Animacion);
	void dibujarAlien(int fila, int col, int tipo_alien, int estadoAnim);
	void coordenadasAliens(int estadoAnim);
	void posicionMatrizAliens(int &Mover_Matriz_X, int &Mover_Matriz_Y);
	void ejecutar();



	//Métodos SET y GET
	int GetMatrizScale_Y();
	void SetMatrizPosicionDeInicio();
	int GetCoordenadasAlien(int fila, int col, char coordenada);
	bool GetVivoMuerto(int fila, int col);
	void SetAlienMuerto(int fila, int col);
	int SetVelocidadNivel(int dificultad);
	int GetConstFila();
	int GetConstCol();
	int GetMatrizPosY();

	//Destruir imagenes de los enemigos
	void DestruirImgAliens();

};

#endif