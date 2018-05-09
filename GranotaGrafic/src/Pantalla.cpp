#include "Pantalla.h"
#include <cstdlib>
#include <ctime>
#include <iostream>
#include "lib\event.h"
using namespace std;

/**
 * Constructor de la Pantalla.
 * Aquesta pantalla té una sola cova imaginària ja que no té parets.
 */
Pantalla::Pantalla(int nivell)
{
	// Carreguem els components gràfics a fer servir.
	graficGranotaArriba.crea("data/GraficsGranota/Granota_Amunt_1.png");
	graficGranotaArriba2.crea("data/GraficsGranota/Granota_Amunt_2.png");
	graficGranotaAbajo.crea("data/GraficsGranota/Granota_Avall_1.png");
	graficGranotaAbajo2.crea("data/GraficsGranota/Granota_Avall_2.png");
	graficGranotaIzq.crea("data/GraficsGranota/Granota_Esquerra_1.png");
	graficGranotaIzq2.crea("data/GraficsGranota/Granota_Esquerra_2.png");
	graficGranotaDer.crea("data/GraficsGranota/Granota_Dreta_1.png");
	graficGranotaDer2.crea("data/GraficsGranota/Granota_Dreta_2.png");
	graficFons.crea("data/GraficsGranota/Fons.png");
	graficVehicle.crea("data/GraficsGranota/Cotxe_1.png");
	graficCoche2.crea("data/GraficsGranota/Cotxe_2.png");
	graficCoche3.crea("data/GraficsGranota/Cotxe_3.png");
	graficCamion.crea("data/GraficsGranota/Camio.png");
	graficTractor.crea("data/GraficsGranota/Tractor.png");
	graficVidas.crea("data/GraficsGranota/vidas.png");
	graficCuevas.crea("data/GraficsGranota/Cova120.png");
	graficGameOver.crea("data/GraficsGranota/GAMEOVER2.png");
	graficNivel.crea("data/GraficsGranota/Nivel.png");
	graficPuntos.crea("data/GraficsGranota/Puntos.png");
	graficVidasLetras.crea("data/GraficsGranota/vidasLetras.png");
	graficTiempo.crea("data/GraficsGranota/tiempo.png");
	graficMosca.crea("data/GraficsGranota/mosca.png");

	granota = Granota(graficGranotaArriba, (FI_X - INICI_X - graficGranotaArriba.getScaleX()) / 2, FI_Y - graficGranotaArriba.getScaleY());

	//Inicializar cada vehiculo en un carril
	InicializarColaVehicles(nivell);

	//Inicializar cuevas
	inicializarCuevas();

	// Fixem l'hora actual com a llavor pel generador d'aleatoris.
	std::srand(std::time(0));
}

/**
 * Destructor per defecte
 */
Pantalla::~Pantalla()
{
	graficFons.destrueix();
	graficGranotaArriba.destrueix();
	graficGranotaArriba2.destrueix();
	graficGranotaAbajo.destrueix();
	graficGranotaAbajo2.destrueix();
	graficGranotaIzq.destrueix();
	graficGranotaIzq2.destrueix();
	graficGranotaDer.destrueix();
	graficGranotaDer2.destrueix();
	graficPuntosyNivel[9].destrueix();
	graficCuevas.destrueix();
	graficVidas.destrueix();
	graficVehicle.destrueix();
	graficCoche2.destrueix();
	graficCoche3.destrueix();
	graficCamion.destrueix();
	graficTractor.destrueix();
	graficNivel.destrueix();
	graficTiempo.destrueix();
	graficMosca.destrueix();
}

/**
 * Inicia la rana a la posicion inicial cada vez que entra en una cueva o la atropellan
 */
void Pantalla::Inicializar()
{
	granota.setGrafic(graficGranotaArriba);
	granota.moverAPosicionInicial();
	mosca = Mosca(graficMosca, graficCuevas.getScaleY(), graficMosca.getScaleY());
	mosca.moverAPosicionInicial();
}


/**
 * Comprova si una àrea donada es troba dins la cova.
 * @param area Area a comprovar si es troba dins una cova.
 * @return true si l'àrea es troba dins la cova i false si no s'hi troba. 
 */
bool Pantalla::estaGranotaDentroCueva()
{
	bool estaDentro = false;
	for (int i = 0; i < N_MAX_CUEVAS; i++)
	{
		if (cueva[i].EstaDentro(granota.getAreaGranota()) && dentro)
		{
			estaDentro = true;
			cueva[i].setTaken(true);
		}
	}
	return estaDentro;
}


/**
 * Dibuixa tots els elements grafics a la posició on es troben.
 */
void Pantalla::dibujarGraficos(int nivell, int vidas)
{
	graficFons.dibuixa(INICI_X, INICI_Y);
	dibujarGranotasDentroCueva();
	granota.dibujarGranota();
	dibujarColaVehicles();
	graficNivel.dibuixa(posicionXletrasNivel, posicionYletrasNivel);
	graficPuntos.dibuixa(posicionXletrasPuntos, posicionYletrasPuntos);
	graficVidasLetras.dibuixa(posicionXletrasVidas, posicionYletrasVidas);
	graficTiempo.dibuixa(posicionXletrasTiempo, posicionYletrasTiempo);
	GraficInformacioVidesPartida(vidas, graficVidas);
	GraficInformacioNivelPartida(graficPuntosyNivel[nivell]);
}


void Pantalla::inicializarCuevas()
{
	for (int i = 0; i < N_MAX_CUEVAS; i++)
		cueva[i] = Cova(graficCuevas, INICI_X + (graficCuevas.getScaleX()*i), INICI_Y);
}


void Pantalla::InicializarColaVehicles(int nivell)
{
	int i, desplazamiento = 10;
	int posicionYcarril[N_MAX_CARRILES];

	for (i = 0; i < N_MAX_CARRILES; i++)
	{
		posicionYcarril[i] = (INICI_Y + graficCuevas.getScaleY() + desplazamiento + (graficVehicle.getScaleY() * i));
		desplazamiento += 20;
	}

	//Inicializar cada vehiculo a su carril
	vehicle[0] = Vehicle(graficVehicle, DESPLACAMENT_COTXE1 * nivell, posicionYcarril[0], RIGHT_CAR);
	vehicle[2] = Vehicle(graficCoche2, DESPLACAMENT_COTXE2 * nivell, posicionYcarril[2], RIGHT_CAR);
	vehicle[4] = Vehicle(graficCoche3, DESPLACAMENT_COTXE3 * nivell, posicionYcarril[4], RIGHT_CAR);
	vehicle[3] = Vehicle(graficCamion, DESPLACAMENT_CAMIO * nivell, posicionYcarril[3], LEFT_CAR);
	vehicle[1] = Vehicle(graficTractor, DESPLACAMENT_TRACTOR * nivell, posicionYcarril[1], LEFT_CAR);


	for (int i = 0; i < N_MAX_CARRILES; i++)
	{
		colaVehicles[i].Push(vehicle[i]);
		carriles[i] = Carriles(posicionYcarril[i], colaVehicles[i]);
	}

	carriles[0].getColaVehicles().setVelocidad(DESPLACAMENT_COTXE1 + nivell - 1);
	carriles[3].getColaVehicles().setVelocidad(DESPLACAMENT_CAMIO + nivell - 1);
	carriles[2].getColaVehicles().setVelocidad(DESPLACAMENT_COTXE2 + nivell - 1);
	carriles[1].getColaVehicles().setVelocidad(DESPLACAMENT_TRACTOR + nivell - 1);
	carriles[4].getColaVehicles().setVelocidad(DESPLACAMENT_COTXE3 + nivell - 1);
}


void Pantalla::PushVehicle(int nivell)
{
	ColaVehiclesCarril auxCola;
	int randomnivell, randomCarril;
	randomnivell = rand() % 100 / nivell;
	randomCarril = rand() % 5;
	if (randomnivell == 1)
	{
		if ((carriles[randomCarril].getColaVehicles().getNumVehicles() < MAX_VEHICLES_CARRIL + nivell) && 
			carriles[randomCarril].getColaVehicles().ColaTodosCochesDentro())
		{
			auxCola = carriles[randomCarril].getColaVehicles();
			auxCola.Push(Vehicle(carriles[randomCarril].getColaVehicles().getGraficVehicle(), 
				carriles[randomCarril].getColaVehicles().getVelocidad(), carriles[randomCarril].getPosY(), 
				carriles[randomCarril].getColaVehicles().getDirection()));
		}
	}
}


/**
 * Mou el vehicle.
 */
void Pantalla::moverColaVehicles()
{
	ColaVehiclesCarril auxCola;

	for (int i = 0; i < N_MAX_CARRILES; i++)
	{
		auxCola = carriles[i].getColaVehicles();

		if (auxCola.getDirection() == LEFT_CAR)
			auxCola.MoverVehicleColaIzq();
		else
			auxCola.MoverVehicleColaDer();
	}
}



void Pantalla::dibujarColaVehicles()
{
	for (int i = 0; i < N_MAX_CARRILES; i++)
		carriles[i].DibujarColaVehicles();
}


void Pantalla::disminuirVelocidadVehicles()
{
	if (GranotaComeMosca())
	{
		for (int i = 0; i < N_MAX_CARRILES; i++)
			carriles[i].getColaVehicles().setVelocidad(1);
	}
}


void Pantalla::reinicializarVelocidadVehicles(int nivell)
{
	carriles[0].getColaVehicles().setVelocidad(DESPLACAMENT_COTXE1 + nivell - 1);
	carriles[3].getColaVehicles().setVelocidad(DESPLACAMENT_CAMIO + nivell - 1);
	carriles[2].getColaVehicles().setVelocidad(DESPLACAMENT_COTXE2 + nivell - 1);
	carriles[1].getColaVehicles().setVelocidad(DESPLACAMENT_TRACTOR + nivell - 1);
	carriles[4].getColaVehicles().setVelocidad(DESPLACAMENT_COTXE3 + nivell - 1);
}

/**
 * Comprova si la granota ha mort.
 * @return true si la granota és morta i false si és viva.
 */
bool Pantalla::haMuertoGranota()
{
	bool haMortLaGranota = false;
	int i;
	for (i = 0; i < N_MAX_CARRILES; i++)
	{
		if (carriles[i].getColaVehicles().solaparVehicleCola(granota.getAreaGranota()))
			haMortLaGranota = true;
	}
	return haMortLaGranota;
}


bool Pantalla::GranotaComeMosca()
{
	return granota.getAreaGranota().solapar(mosca.getAreaMosca());
}


void Pantalla::ActualizarGranota(bool bcontar, int direccion)
{
	if (!bcontar)
	{
		dentro = true;
		switch (direccion)
		{
		case ARRIBA:
			granota.setGrafic(graficGranotaArriba);
			break;
		case ABAJO:
			granota.setGrafic(graficGranotaAbajo);
			break;
		case DERECHA:
			granota.setGrafic(graficGranotaDer);
			break;
		case IZQUIERDA:
			granota.setGrafic(graficGranotaIzq);
			break;
		}
	}
	else
	{
		dentro = false;
		switch (direccion)
		{
		case ARRIBA:
			granota.setGrafic(graficGranotaArriba2);
			break;
		case ABAJO:
			granota.setGrafic(graficGranotaAbajo2);
			break;
		case DERECHA:
			granota.setGrafic(graficGranotaDer2);
			break;
		case IZQUIERDA:
			granota.setGrafic(graficGranotaIzq2);
			break;
		}
	}
}

/**
 * Mou la granota en la direcció donada.
 * @param direccio Direcció cap on s'ha de moure la granota. Fer servir constants AMUNT, AVALL, DRETA i ESQUERRA.
 */
void Pantalla::moverGranota(int direccion)
{
	switch (direccion)
	{
	case ARRIBA:
		if (esAccesible())
			granota.moverArriba();
		break;

	case ABAJO:
		if (granota.getAreaGranota().getMaxY() + DESPLACAMENT_GRANOTA < FI_Y)
			granota.moverAbajo();
		break;

	case DERECHA:
		if (!CuevaParteDer() && granota.getAreaGranota().getMaxX() + DESPLACAMENT_GRANOTA < FI_X)
			granota.moverDerecha();
		break;

	case IZQUIERDA:
		if (!CuevaParteIzq() && granota.getAreaGranota().getMinX() > INICI_X)
			granota.moverIzquierda();
		break;
	}
}


void Pantalla::dibujarGranotasDentroCueva()
{
	for (int i = 0; i < N_MAX_CARRILES; i++)
	{
		cueva[i].DibujarCueva();
		if (cueva[i].getTaken())
			graficGranotaArriba.dibuixa(cueva[i].getPosX() + (graficCuevas.getScaleX() / 2) - graficGranotaArriba.getScaleX() / 2, INICI_Y + graficCuevas.getScaleY() / 2 - 10);
	}
}


bool Pantalla::TodasCuevasOcupadas()
{
	bool alltaken = true;

	for (int i = 0; i < N_MAX_CUEVAS; i++)
	{
		if (!cueva[i].getTaken())
			alltaken = false;
	}
	return alltaken;
}

void Pantalla::LimpiarCuevas()
{
	for (int i = 0; i < N_MAX_CUEVAS; i++)
		cueva[i].setTaken(false);
}

bool Pantalla::esAccesible()
{
	bool esAccesible = true;
	for (int i = 0; i < N_MAX_CUEVAS; i++)
	{
		if (!cueva[i].esAccesible(granota.getAreaGranota()))
			esAccesible = false;
	}
	return esAccesible;
}

bool Pantalla::CuevaParteIzq()
{
	bool golpea = false;
	for (int i = 0; i < N_MAX_CUEVAS; i++)
	{
		if (cueva[i].CuevaParteIzq(granota.getAreaGranota()))
			golpea = true;
	}
	return golpea;
}


bool Pantalla::CuevaParteDer()
{
	bool golpea = false;
	for (int i = 0; i < N_MAX_CUEVAS; i++)
	{
		if (cueva[i].CuevaParteDer(granota.getAreaGranota()))
			golpea = true;
	}
	return golpea;
}

void Pantalla::PuntosVidas()
{
	for (int i = 0; i <= 9; i++)
	{
		// Concatenar int i char per treure els punts
		char string[29]; //Longitud del string (ruta de l'arxiu)
		sprintf_s(string, "data/numeros/numero000%d.png", i);
		graficPuntosyNivel[i].crea(string);
	}
}

/**
* Mostra el n�mero de vides que li queden al jugador en aquesta partida
* @param vides
* @param graficVidas
*/
void Pantalla::GraficInformacioVidesPartida(int vides, Grafic graficVidas)
{
	int posicioXvides = 450;
	int posicioYvides = 450;
	for (int i = 0; i < vides; i++)
	{
		graficVidas.dibuixa(posicioXvides, posicioYvides); // Posición de las vidas en el eje X y en el eje Y
		posicioXvides += 60;								// Distancia que hay entre cada vida
	}
}


/**
* Mostra el nivell del jugador a la partida
* @param nivel
* @param hScreen
*/
void Pantalla::GraficInformacioNivelPartida(Grafic &graficNivelPArtida)
{
	int posicioX_Nivel = 70;
	int posicioY_Nivel = 450;
	graficNivelPArtida.dibuixa(posicioX_Nivel, posicioY_Nivel); // Posición del nivel en el eje X y en el eje Y
}


void Pantalla::GraficGameOver()
{
	graficGameOver.dibuixa(INICI_X, INICI_Y);
}


void Pantalla::dibujarGraficMosca()
{
	mosca.dibujarMosca();
}


void Pantalla::SumarPuntos(int puntos)
{
	//Sumar puntos
	if (!estaGranotaDentroCueva())
	{
		int divPuntos = puntos;
		int posicioXpuntos = 300; // coordenadas X
		int posicioYpuntos = 450; // coordenadas Y
		do
		{
			int num = divPuntos % 10;
			graficPuntosyNivel[num].dibuixa(posicioXpuntos, posicioYpuntos); // posición donde se dibujan los puntos en el eje X y en el eje Y
			divPuntos = divPuntos / 10;
			posicioXpuntos -= 15; //Separación entre cada numero
		} while (divPuntos);
	}
}


void Pantalla::Temporizador(int tiempo)
{
	int SEGUNDO, ML;
	int posicionX = 125;
	int posicionY = 485;
	int num;

	for (SEGUNDO = 3; SEGUNDO >= 0; SEGUNDO--)
	{
		num = tiempo % 10;
		graficPuntosyNivel[num].dibuixa(posicionX, posicionY);
		tiempo = tiempo / 10;
		posicionX -= 15;
	}
}