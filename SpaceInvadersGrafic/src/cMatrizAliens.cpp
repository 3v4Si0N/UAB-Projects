#include "cMatrizAliens.h"
#include "Joc.h"
#include "lib/cGrafic.h"
#include "lib/event.h"
#include "lib/libreria.h"
#include <stdio.h>
#include <iostream>
#include <string>

const char Experto = '1';
const char Medio = '2';
const char Principiante = '3';


int animStateVar = 0;
int velocidadAnimVar = 0;


//===================================================================================
//	NOM: Constructor y Destructor
//===================================================================================
cMatrizAliens::cMatrizAliens(void)
{
}
cMatrizAliens::~cMatrizAliens(void)
{
}


//===================================================================================
// NOM: SetMatrizPosicionDeInicio
//===================================================================================
// DESCRIOCIÓ: Pone la matriz en la posicion de inicio en el eje X y en el eje Y
//===================================================================================
void cMatrizAliens::SetMatrizPosicionDeInicio()
{
	matriz_posX = INICI_X;
	matriz_posY = INICI_Y;
}


//===================================================================================
// NOM: inicializarMatrizAliens
//===================================================================================
// DESCRIPCIÓ: Inicializa la matriz de los aliens
//===================================================================================
void cMatrizAliens::inicializarMatrizAliens()
{
	for (int fila = 0; fila < num_fila; fila++)
	{
		for (int col = 0; col < num_col; col++)
		{
			//Inicializacion de todos los aliens a vivos
			m_Aliens[fila][col].SetVivo();

			switch (fila)
			{
			case 0:
				m_Aliens[fila][col].SetTipoAlien(ALIEN_1); //Elige el tipo de alien que se va a dibujar en la fila 0
				break;
			case 1:
			case 2:
				m_Aliens[fila][col].SetTipoAlien(ALIEN_2); //Elige el tipo de alien que se va a dibujar en la fila 1 y 2
				break;
			case 3:
			case 4:
				m_Aliens[fila][col].SetTipoAlien(ALIEN_3); //Elige el tipo de alien que se va a dibujar en el afila 3 y 4
				break;
			}
		}
	}
}


//===================================================================================
// NOM: RevivirMatrizAliens
//===================================================================================
// DESCRIPCIÓ: Vuelve a inicializar todos los aliens a vivos
//===================================================================================
void cMatrizAliens::RevivirMatrizAliens()
{
	for (int fila = 0; fila < num_fila; fila++)
	{
		for (int col = 0; col < num_col; col++)
		{
			//Inicializacion de toda la matriz de aliens
			m_Aliens[fila][col].SetVivo();
		}
	}
}


//===================================================================================
//	NOM: inicializarImgAlien
//===================================================================================
// 	DESCRIPCIÓ: Inicializa los graficos de los aliens
//===================================================================================
void cMatrizAliens::inicializarImgAlien()
{
	for (int i = 0; i < tipo_Alien; i++)
	{
		for (int j = 0; j < tipo_Img; j++)
		{
			//Concatenacion de char con int para dibujar los diferentes tipos de imagenes de los aliens
			char ImgsAlien[33];
			sprintf(ImgsAlien, "data/SpaceInvaders/Alien_%d_%d.png", i + 1, j + 1);
			imgAlien[i][j].Crear(ImgsAlien);
		}
	}
}


//===================================================================================
// NOM: seleccionarAlien
//===================================================================================
// 	DESCRIPCIÓ: Selecciona el tipo de alien que quiere dibujar y lo dibuja
//===================================================================================
void cMatrizAliens::seleccionarAlien(int fila, int col, int estadoAnim)
{
	switch (m_Aliens[fila][col].GetTipoAlien())
	{
	case ALIEN_1:
		dibujarAlien(fila, col, ALIEN_1, estadoAnim); //Dibuja el tipo de alien en la fila y columna que se quiere con su animacion correspondiente
		break;
	case ALIEN_2:
		dibujarAlien(fila, col, ALIEN_2, estadoAnim); //Dibuja el tipo de alien en la fila y columna que se quiere con su animacion correspondiente
		break;
	case ALIEN_3:
		dibujarAlien(fila, col, ALIEN_3, estadoAnim); //Dibuja el tipo de alien en la fila y columna que se quiere con su animacion correspondiente
		break;
	}
}


//===================================================================================
//	NOM: dibujarAlien
//===================================================================================
// DESCRIPCIÓ: Calcula la posicion en el eje X y el eje Y 
//						donde se va a dibujar cada alien
//===================================================================================
void cMatrizAliens::dibujarAlien(int fila, int col, int tipo_alien, int estadoAnim)
{
	int pos_x, pos_y;
	//Posicion donde se dibuja cada alien
	pos_x = matriz_posX + (col*(imgAlien[tipo_alien][estadoAnim].getScaleX() + espacio_Alien_X));
	pos_y = matriz_posY + (fila*(imgAlien[tipo_alien][estadoAnim].getScaleY() + espacio_Alien_Y));

	//Crea el alien uno a uno
	imgAlien[tipo_alien][estadoAnim].Dibuixar(pos_x, pos_y);

	//Pasa la posicion al objeto m_Aliens
	m_Aliens[fila][col].SetCoordenadaAlien(pos_x, pos_y);
}


//===================================================================================
// NOM: MatrizInfo
//===================================================================================
// DESCRIPCIÓ: Calcula el ancho de la matriz, el alto de la matriz, numero de columnas 
//             a la izq con todos los aliens muertos, e igual por la derecha
//===================================================================================
void cMatrizAliens::DatosMatriz()
{
	int IzqColMuerta = 0, DerechaColMuerta = 0, countVal;
	bool IzqColVal = true, DerechaColVal = true;
	int col = 0, fila = 0, nFila_max = 0;


	//Se itera de izq a derecha mirando cada col, si la columna entera esta muerta entonces se suma 1 a la variable Izq_ColMuerta 
	//que guarda el nunmero de columnas con todos los alien muertos del lado izquierdo
	while (col < num_col && IzqColVal)
	{
		countVal = 0;
		for (fila = 0; fila < num_fila; fila++)
		{
			if (m_Aliens[fila][col].EstaVivo_Muerto() == false)
			{
				countVal++;
				if (countVal == num_fila && IzqColVal)
					IzqColMuerta++;
			}
			else{
				IzqColVal = false;
			}
		}
		col++;
	}
	//Se itera igual que antes pero de derecha a izq, pero como maximo se hace hasta la columna
	//anterior respecto la cual se queda en el while de izq a derecha.
	int N_COLAux = num_col - 1;
	while (N_COLAux > col && DerechaColVal)
	{
		countVal = 0;
		for (fila = 0; fila < num_fila; fila++)
		{
			if (m_Aliens[fila][N_COLAux].EstaVivo_Muerto() == false)
			{
				countVal++;
				if (countVal == num_fila && DerechaColVal)
					DerechaColMuerta++;
			}
			else{
				DerechaColVal = false;
			}
		}
		N_COLAux--;
	}

	//Numero maximo de filas que tiene la matriz
	fila = num_fila-1;
	bool salir = true; //Booleano para poder salir del bucle cuando la condicion se cumpla
	while (0 <= fila && salir)
	{
		col = 0;
	 while (col < num_col && salir)
		{
		 if (m_Aliens[fila][col].EstaVivo_Muerto())
			 salir = false;
			col++;
		}
		nFila_max = fila+1;
		fila--;
	}


	ancho_m = (num_col)*(espacio_Alien_X + 33);  //Ancho de la matriz de aliens
	alto_m = (nFila_max)*(espacio_Alien_Y + 35);   //Alto de la matriz de aliens
	col_izq_lateral_muerta = IzqColMuerta;		   
	col_der_lateral_muerta = DerechaColMuerta;	   

}


//===================================================================================
// NOM: positionMaztrizAliens
//===================================================================================
// DESCRIPCIÓ: Mueve la matriz 1 a 1 hacia la derecha, cuando llega al limite derecho,
//             baja 1 posicion en el eje Y y hace lo mismo hacia el lado opuesto
//===================================================================================
void cMatrizAliens::posicionMatrizAliens(int &Velocidad_Matriz_X, int &Velocidad_Matriz_Y)
{
	DatosMatriz();

	matriz_posX += Velocidad_Matriz_X;
	if (matriz_posX + (col_izq_lateral_muerta*(33 + espacio_Alien_X)) <= INICI_X ||
		ancho_m + matriz_posX >= FI_X + (col_der_lateral_muerta*(33 + espacio_Alien_X)))
	{
		Velocidad_Matriz_X = -Velocidad_Matriz_X;
		matriz_posX += Velocidad_Matriz_X;
		matriz_posY += Velocidad_Matriz_Y;
	}
}


//===================================================================================
// NOM: EstadoAnim
//===================================================================================
// DESCRIPCIÓ: Nos devuelve el estado de animacion de cualquier alien
//===================================================================================
int cMatrizAliens::estadoAnim(int &animacion_e, int &velocidad_Animacion)
{
	if (velocidad_Animacion < velocidadAnimConst)
	{
		velocidadAnimVar++;
		return(animacion_e);
	}
	else
	{
		if (animacion_e < tipo_Img - 1) //Resta 1 al tipo de imagen para que la iteracion sea de 2 posiciones (0 y 1)
		{
			animacion_e++;
		}
		else
		{
			animacion_e = 0;
		}
		velocidad_Animacion = 0;
		return(animacion_e);
	}
}


//===================================================================================
// NOM: ejecutar
//===================================================================================
// DESCRIPCIÓ: Ejecuta lo que se necesita para cargar el juego
//===================================================================================
void cMatrizAliens::ejecutar()
{
	posicionMatrizAliens(Velocidad_Matriz_X, Velocidad_Matriz_Y);
	coordenadasAliens(estadoAnim(animStateVar, velocidadAnimVar));
}


//===================================================================================
// NOM: GetMatrizScale_Y
//===================================================================================
// DESCRIPCIÓ: Da la posicion de la parte de abajo de la matriz de aliens.
//===================================================================================
int cMatrizAliens::GetMatrizScale_Y()
{
	return alto_m + matriz_posY;
}


//===================================================================================
// NOM: DestruirImgAliens
//===================================================================================
// DESCRIPCIÓ: Destruye las imagenes de los aliens
//===================================================================================
void cMatrizAliens::DestruirImgAliens()
{
	imgAlien[0][0].Destruir();
}


//===================================================================================
// NOM: GetCoordenadasAlien
//===================================================================================
// DESCRIPCIÓ: Nos devuelve la coordeanda del alien
//===================================================================================
int cMatrizAliens::GetCoordenadasAlien(int fila, int col, char coordenada)
{
	return m_Aliens[fila][col].GetCoordenadaAlien(coordenada);
}


//===================================================================================
//	NOM: coordinadasAliens
//===================================================================================
// DESCRIPCIÓ: Itera fila y columna de la matriz y si en la coordenada X hay un alien 
//             vivo llama al metodo seleccinarAlien
//===================================================================================
void cMatrizAliens::coordenadasAliens(int estadoAnim)
{
	for (int fila = 0; fila < num_fila; fila++)
	{
		for (int col = 0; col < num_col; col++)
		{
			if (m_Aliens[fila][col].EstaVivo_Muerto()) // Condición para saber si un alien esta vivo
			{
				seleccionarAlien(fila, col, estadoAnim);
			}
		}
	}
}


//===================================================================================
// NOM: GetConstFila
//===================================================================================
// DESCRIPCIÓ: Devuelve la constante del numero de filas para poder usarla fuera
//===================================================================================
int cMatrizAliens::GetConstFila()
{
	return num_fila;
}


//===================================================================================
// NOM: GetConstFila
//===================================================================================
// DESCRIPCIÓ: Devuelve la constante del numero de columnas para poder usarla fuera
//===================================================================================
int cMatrizAliens::GetConstCol()
{
	return num_col;
}


//===================================================================================
// NOM: GetVivoMuerto
//===================================================================================
// DESCRIPCIÓ: Con este metodo sabemos si un alien esta vivo o muerto
//===================================================================================
bool cMatrizAliens::GetVivoMuerto(int fila, int col)
{
	return m_Aliens[fila][col].EstaVivo_Muerto();
}


//===================================================================================
// NOM: SetAlienMuerto
//===================================================================================
// DESCRIPCIÓ: Pone al alien en estado muerto
//===================================================================================
void cMatrizAliens::SetAlienMuerto(int fila, int col)
{
	m_Aliens[fila][col].MatarAlien();
}


//===================================================================================
// NOM: SetVelocidadNivel
//===================================================================================
// DESCRIPCIÓ: Segun la dificultad aumenta o disminuye la velocidad de la matriz
//===================================================================================
int cMatrizAliens::SetVelocidadNivel(int dificultad)
{
	switch (dificultad){
	case Experto:
		Velocidad_Matriz_X * 3;
		break;
	case Medio:
		Velocidad_Matriz_X * 2;
		break;
	case Principiante:
		Velocidad_Matriz_X;
		break;
	}
	return Velocidad_Matriz_X;
}

int cMatrizAliens::GetMatrizPosY()
{
	return matriz_posY;
}