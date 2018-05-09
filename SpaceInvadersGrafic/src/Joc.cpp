#include "Joc.h"
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include "lib/libreria.h"
#include "lib/event.h"
#include "lib/cGrafic.h"
#include "cMatrizAliens.h"
#include "cListaBalas.h"
#include "cBala.h"
#include "cEscudos.h"
#include "cAlien.h"
#include <windows.h>


cMatrizAliens objAliens; //Objeto de la clase cMatrizAliens
cListaBalas objLista;	//Objeto de la clase cListaBalas
cBala objBala;			//Objeto de la clase cBala
cEscudos objEscudos;

// Variables para guardar los grñaficos
cGrafic graficEscudos;
cGrafic graficFons;
cGrafic graficCano;
cGrafic graficBala;
cGrafic graficVides;
cGrafic graficPunts[10]; //Variable que guarda los 9 números de los puntos (de 0 a 9)
cGrafic graficOvni;
cGrafic graficCanonMuerto;
cGrafic graficEscudoRoto1;
cGrafic graficEscudoRoto2;
cGrafic graficEscudoRoto3;



//===================================================================================
// NOM: MoureCano
//===================================================================================
// DESCRIPCIÓ: Mueve el cañón en el eje X en la dirección DirX (1=derecha / -1 = izquierda)
//             Devuelve la nueva coordenada X de la posición del cañón
//===================================================================================
int MoureCano(int x, int dirX, cGrafic &graficCano)
{
	x += dirX*D_X;
	return x;
}


//===================================================================================
// NOM: MoureBala
//===================================================================================
// DESCRIPCIÓ: Mueve la bala una posición
//             Devuelve la nueva coordenada Y de la posició de la bala
//===================================================================================
int MoureBala(int x, int y, int BalaYY)
{
	y -= BalaYY;
	return y;
}

//===================================================================================
// NOM: MoverOvni
//===================================================================================
// DESCRIPCIÓ: Mueve el ovni una posición y actualiza el valor de sus coordenadas
//===================================================================================
int MoverOvni(int &x, int &dirX, int graficOvni)
{
		x -= dirX;
		return x;
}

//===================================================================================
// NOM: graficInfoVides
//===================================================================================
// DESCRIPCIÓ: Función que hace la gráfica de las vidas
//===================================================================================

void graficInfoVides(int vides, cGrafic graficVides)
{
	int posicioXvides = 100;
	int posicioYvides = 60;
	for (int i = 0; i < vides; i++)
	{
		graficVides.Dibuixar(posicioXvides, posicioYvides); // Posición de las vidas en el eje X y en el eje Y
		posicioXvides += 60;								// Distancia que hay entre cada vida
	}
}


//===================================================================================
// NOM: graficInfoEscudos
//===================================================================================
// DESCRIPCIÓ: Función que hace el gráfico de los escudos
//===================================================================================
void graficInfoEscudos(int escudos, cGrafic &graficEscudos)
{
	int posXescudos = 150;
	int posYescudos = 390;

	for (int i = 0; i < escudos; i++)
	{	
		graficEscudos.Dibuixar(posXescudos, posYescudos);  // Posición de los escudsos en el eje X y en el eje Y
		posXescudos += 200;								   // Distancia que hay entre cada escudo	
	}
}


//===================================================================================
// NOM: GenerarListaDisparos
//===================================================================================
// DESCRIPCIÓ: Función que genera los disparos de los enemigos aleatoriamente
//===================================================================================
void GenerarListaDisparos()
{
	int fila, col;

	int nAleatori = rand() % 55;

	if (nAleatori == 10)
	{
		fila = rand() % (objAliens.GetConstFila());
		col = rand() % (objAliens.GetConstCol());

		while (!objAliens.GetVivoMuerto(fila, col) == true)
		{
			fila = rand() % (objAliens.GetConstFila());
			col = rand() % (objAliens.GetConstCol());
		}

		int posX = objAliens.GetCoordenadasAlien(fila, col, 'x') + 20;
		int posY = objAliens.GetCoordenadasAlien(fila, col, 'y') + 40;
		objBala.SetX(posX);
		objBala.SetY(posY);
		objLista.InsertarPosicion(objBala);
	}
}


//===================================================================================
// NOM: DibujarBalas
//===================================================================================
// DESCRIPCIÓ: Función que dibuja los disparos de los enemigos
//===================================================================================

void DibujarBalas()
{
	objLista.InicializarRecorrido();
	while (objLista.GetPosActual() != NULL)
	{
		objBala = objLista.GetActual();
		graficBala.Dibuixar(objBala.GetX(), objBala.GetY());
		objLista.Next();
	}
}



//===================================================================================
// NOM: Joc
//===================================================================================
// DESCRIPCIÓ: Función del bucle principal de la ejecución del juego
//===================================================================================
int Joc(int nivell)
{
	// variable que controla el estado del juego
	t_programStatus estat;

	int ovniX, ovniY;			// Coordenades del ovni
	int ovni_DX;				// nº de posiciones que es desplaza el ovni en cada movimiento
	int canoX, canoY;			//Coordenadas del cañón
	int balaX, balaY;			//Coordenadas de la bala
	int count = 0;				//Variable contador
	int velocidadBala = 35;		//Velocidad de la bala
	bool tret;					//Variable per saber si se ha disparado
	int px_alien = 33;			//Pixeles de las imagenes de los aliens
	bool aliensMuertos;			//Variable per saber si se ha matado a un alien
	bool ovniMuerto;
	bool canonMuerto;
	bool pintarOvni = false;
	int contAlien, contBala;	//Contadores que controlan cada cuantas iteraciones del bucle se mueven los aliens o la bala
	int puntos = 0;
	int vidas = 3;
	int golpes = 0;
	int escudos = 4;
	int velocidadJuego;
	int posXescudos = 150, posYescudos = 390;

	

	velocidadJuego = nivell;		// Determina la velocidad en función del nivel del juego

	objAliens.inicializarMatrizAliens(); //Inicializa la matriz de aliens
	objAliens.SetVelocidadNivel(nivell); //Pone la velocidad en funcion del nivel

	//Inicializaciones necesarias para utilizar la librería gráfica
	InitGame(estat);

	
	// Muestra la ventana del juego
	Video_ShowWindow();



	//Creación de los gráficos del fondo, del cañón, de los aliens, de los escudos y de la bala. Se lee la información de los ficheros especificados
	graficCano.Crear("data/SpaceInvaders/SpaceShip.png");
	graficFons.Crear("data/SpaceInvaders/Background.png");
	graficBala.Crear("data/SpaceInvaders/Disparo.png");
	graficVides.Crear("data/SpaceInvaders/SpaceShip.png");
	graficEscudos.Crear("data/SpaceInvaders/DefendingBlock.png");
	graficOvni.Crear("data/SpaceInvaders/ovni.png");
	graficCanonMuerto.Crear("data/SpaceInvaders/SpaceShipDestroyed.png");
	graficEscudoRoto1.Crear("data/SpaceInvaders/escudos/escudo_dano_1_1.png");
	graficEscudoRoto2.Crear("data/SpaceInvaders/escudos/escudo_dano_1_2.png");
	graficEscudoRoto3.Crear("data/SpaceInvaders/DefendingBlockDestroyed_3.png");
	


	for (int i = 0; i <= 9; i++)
	{
		// Concatenar int i char per treure els punts
		char string[29]; //Longitud del string (ruta de l'arxiu)
		sprintf(string, "data/numeros/numero000%d.png", i);
		graficPunts[i].Crear(string);
	}

	objAliens.inicializarImgAlien(); //Inicialización las imágenes de los aliens

	//Inicialización de la posición del cañón tanto en el eje X como en el eje Y
	canoX = INICI_X + ((FI_X - INICI_X) / 2);
	canoY = FI_Y;

	//Inicialización de la posición del onvi tanto en el eje X como en el eje Y y la velocidad
	ovniY = INICI_Y;
	ovni_DX = 4;				// Desplazamiento del ovni en el eje X

	for (int i = 0; i < escudos; i++)
	{
		objEscudos.InicializarEscudos(posXescudos, posYescudos, escudos);
		
	}

	objAliens.SetMatrizPosicionDeInicio(); //inicialización de la matriz de aliens
	do
	{

		tret = false;				//Inicialización de la variable disparo a falso
		canonMuerto = false;		//Inicialización de la variable CañonMuerto a falso
		aliensMuertos = false;		//Inicialización de la variable AlienMuerto a falso
		ovniX = FI_X + 50;
		ovniMuerto = false;			//Inicialización de la variable onviMuerto a falso

		do
		{
			//Captura los eventos del teclado
			ProcessEvents(estat);



			//Comprueba si se ha presionado alguna de las flechas y modifica la posición del cañón segun la tecla presionada
			if (Keyboard_GetKeyTrg(KEYBOARD_LEFT))
			//Si la posición del cañón es más grande que el límite de la izquierda + el desplazamiento de cada movimiento
			if (canoX > (INICI_X + D_X))
			{
				// Mueve el cañón a la izquierda
				canoX -= D_X;
			}
			if (Keyboard_GetKeyTrg(KEYBOARD_RIGHT))
			//Si la posición del cañón + el tamaño en horizontal del cañón es más pequeña que el límite derecho - el desplazamiento de cada movimiento
			if ((canoX + graficCano.getScaleX()) < (FI_X - D_X))
			{
				// Mueve el cañón a la derecha
				canoX += D_X;
			}


			if (Keyboard_GetKeyCnt(KEYBOARD_SPACE))
			{

				//Si no hay disparo activo se genera un nuevo disparo en la posición del cañón
				if (!tret)
				{
					tret = true;
					contBala = velocidadJuego;
					balaX = canoX + (graficCano.getScaleX() / 2);
					balaY = FI_Y - (graficCano.getScaleY());
				}
			}
			//Si hay un disparo activo, hacemos el movimiento de la bala
			if (tret)
			{
				contBala--;
				//La bala nada más se mueve si el contador ha llegado a 0
				if (!contBala)
				{
					//Comprueba que la bala no ha llegado al límite superior
					if (balaY >= (INICI_X + graficBala.getScaleY()))
					{
						balaY = MoureBala(balaX, balaY, velocidadBala);

						//Comprueba que la bala ha chocado con el ovni
 						if ((balaX >= ovniX - (graficOvni.getScaleX()/2)) && (balaX <= ovniX + graficOvni.getScaleX()) && (balaY <= ovniY + graficOvni.getScaleY()))
						{
							puntos += 1200;
							ovniMuerto = true;
						}

						if (ovniMuerto)
							ovniX = FI_X + 50;	//Inicialización de la posición del onvi tanto en el eje X

						for (int i = 0; i < escudos; i++)
						{
							if (objEscudos.ColisionEscudosBalasCanon(balaX, balaY + graficBala.getScaleY(), graficEscudos))
								tret = false;
						}

						if (balaY <= objAliens.GetMatrizScale_Y()){
							int fila = objAliens.GetConstFila();

							do
							{
								int col = 0;
								do
								{
									//Condicion de la colision de la bala con el alien
									if (((balaY <= objAliens.GetCoordenadasAlien(fila, col, 'y') + px_alien) &&
										(balaX >= objAliens.GetCoordenadasAlien(fila, col, 'x') &&
										balaX <= objAliens.GetCoordenadasAlien(fila, col, 'x') + px_alien)) &&
										objAliens.GetVivoMuerto(fila, col))
									{

										tret = false;
										objAliens.SetAlienMuerto(fila, col);
										puntos += objAliens.GetCoordenadasAlien(fila, col, 'y');

										//Reaparecer la matriz de aliens cuando todos estan muertos
										do{
											count++;
											if (count == (objAliens.GetConstFila()*objAliens.GetConstCol())){
												objAliens.RevivirMatrizAliens();
												vidas++;
												objAliens.SetMatrizPosicionDeInicio();
												count = 0; //Reinicializa la variable count para poder llegar de nuevo a 55
												ovniMuerto = false;
											}
										} while (aliensMuertos);
									}
									col++;
								} while (col <= objAliens.GetConstCol() && tret);
								fila--;
							} while (fila >= 0 && tret);
						}

					}
					else
					{
						tret = false;
					}
					//Si el contador consigue llegar a 0 volvemos a reiniciar para comenzar la cuenta de iteraciones
					contBala = velocidadJuego;
				}
			}

			//Llamada al método moverBalas de los enemigos
			objLista.MoverBalas(velocidadBala);
			//Dibuja cada gráfico en su posición actual
			graficFons.Dibuixar(0, 0);
			//Llamada al método GenerarDisparos de los enemigos
			GenerarListaDisparos();

			//Llamada al método colisiónCañon de la clase cLista
			objLista.ColisionEscudosBalasAliens(graficEscudos, objEscudos, graficBala.getScaleY());
			objLista.ColisionCanon(canoX, canoY, vidas, graficBala.getScaleY(), graficCano.getScaleX(), canonMuerto, graficCanonMuerto);
			


			//Llamada al método graficInfoEscudos
			graficInfoEscudos(escudos, graficEscudos);
			objEscudos.DibujarEscudoRoto(graficEscudos, graficEscudoRoto3);

			//Llamada al método ejecutar de la clase cMatrizAliens
			objAliens.ejecutar();
			
			//Llamada al método que dibuja las balas de los enemigos
			DibujarBalas();

			//Si el cañon está vivo dibuja la imagen normal y si el cañon ha sufrido un impacto se dibuja la imagen del impacto
			if (!canonMuerto)
				graficCano.Dibuixar(canoX, canoY);
			else
				graficCanonMuerto.Dibuixar(canoX, canoY);


				//if (golpes == 0)
				
				/*else if (golpes == 1)
				graficEscudoRoto1.Dibuixar(posXescudos, posYescudos);
				else if (golpes == 2)
				graficEscudoRoto2.Dibuixar(posXescudos, posYescudos);
				else if (golpes == 3)
				graficEscudoRoto3.Dibuixar(posXescudos, posYescudos);*/

			//Sumar puntos
			if (!aliensMuertos)
			{
				int divPunts = puntos;
				int posicioXpunts = 860; //posició en el eje X
				int posicioYpunts = 70;	 //posició en el eje Y

				do
				{
					int num = divPunts % 10;
					graficPunts[num].Dibuixar(posicioXpunts, posicioYpunts); // posición donde se dibujan los puntos en el eje X y en el eje Y
					divPunts = divPunts / 10;
					posicioXpunts -= 15; //Separación entre cada numero
				} while (divPunts);
			}


			//si el disparo está activo se dibuja la bala
			if (tret)
				graficBala.Dibuixar(balaX, balaY);
				
			//Si hay algun alien vivo o el cañon está vivo, se dibujan las vidas
			if ((!aliensMuertos) && (!canonMuerto))
				graficInfoVides(vidas, graficVides);

				
				//Condición para que el ovni se dibuje solo cuando pasa la coordenada 180 en el eje Y y la posición en X sea > 0 y que pintarOvni sea true
				if ((objAliens.GetMatrizPosY() > 180) && (ovniX > 0) && (!ovniMuerto))
				{
					graficOvni.Dibuixar(ovniX, ovniY);
					ovniX = MoverOvni(ovniX, ovni_DX, graficOvni.getScaleX());
				}

				
			//Muestra por pantalla todos los gráficos que se hayan dibujado desde la última vez
			VideoUpdate(estat);

		//Salimos si un alien llega al final o presionamos la tecla X o el cañon muere
		} while ((!aliensMuertos) && (!canonMuerto) && (objAliens.GetMatrizScale_Y() <= FI_Y) && (!Keyboard_GetKeyTrg(KEYBOARD_X)));

		if ((!aliensMuertos) && (!canonMuerto))
		{
			vidas--;
			objAliens.RevivirMatrizAliens();
			objAliens.SetMatrizPosicionDeInicio();
			count = 0;
		}

		if (canonMuerto)
			Sleep(800);

		//Se acaba el juego si nos hemos quedado sin vidas o se ha presionado la tecla X
	} while ((vidas > 0) && (!Keyboard_GetKeyTrg(KEYBOARD_X)) && (!estat.bExit));


	// Destruimos los gráficos
	graficCano.Destruir();
	graficFons.Destruir();
	graficBala.Destruir();
	graficOvni.Destruir();
	graficEscudos.Destruir();
	graficPunts[9].Destruir();
	objAliens.DestruirImgAliens();
	objLista.~cListaBalas();

	//Libera y cierra la ventana del juego
	Video_Release();

	return(puntos);
}