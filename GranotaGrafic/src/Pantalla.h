#pragma once
#include "Area.h"
#include "lib\Grafic.h"
#include "Cova.h"
#include "Granota.h"
#include "Carriles.h"
#include "Mosca.h"

// Inici del taulell respecte la cantonada superior esquerre
#define INICI_X 0
#define INICI_Y 0

// Fi del taulell respecte la cantonada superior esquerre
#define FI_X 600
#define FI_Y 450

#define ARRIBA 0
#define ABAJO 1
#define DERECHA 2
#define IZQUIERDA 3


const int posicionXletrasNivel = 0;
const int posicionYletrasNivel = 450;

const int posicionXletrasPuntos = 170;
const int posicionYletrasPuntos = 450;

const int posicionXletrasVidas = 370;
const int posicionYletrasVidas = 450;

const int posicionXletrasTiempo = 0;
const int posicionYletrasTiempo = 480;

class Pantalla
{
public:

	Pantalla(int nivell);
	~Pantalla();
	void Inicializar();
	void inicializarCuevas();
	void dibujarGraficos(int nivell, int vidas);
	void ActualizarGranota(bool bcontar, int direccion);
	void moverGranota(int direccion);
	void dibujarGranotasDentroCueva();
	void PuntosVidas();
	void GraficInformacioNivelPartida(Grafic &graficNivelPArtida);
	void GraficInformacioVidesPartida(int vides, Grafic graficVidas);
	void GraficGameOver();
	void dibujarGraficMosca();
	bool estaGranotaDentroCueva();
	bool TodasCuevasOcupadas();
	bool CuevaParteIzq();
	bool CuevaParteDer();
	void LimpiarCuevas();
	void InicializarColaVehicles(int nivell);
	void moverColaVehicles();
	void dibujarColaVehicles();
	void disminuirVelocidadVehicles();
	void reinicializarVelocidadVehicles(int nivell);
	void PushVehicle(int nivell);
	bool haMuertoGranota();
	bool GranotaComeMosca();
	void SumarPuntos(int puntos);
	void Temporizador(int tiempo);

private:

	bool dentro;

	Grafic graficGranotaArriba;
	Grafic graficGranotaArriba2;
	Grafic graficGranotaAbajo;
	Grafic graficGranotaAbajo2;
	Grafic graficGranotaIzq;
	Grafic graficGranotaIzq2;
	Grafic graficGranotaDer;
	Grafic graficGranotaDer2;
	Grafic graficFons;
	Grafic graficVehicle;
	Grafic graficCoche2;
	Grafic graficCoche3;
	Grafic graficCamion;
	Grafic graficTractor;
	Grafic graficVehicle2;
	Grafic graficCoche2_2;
	Grafic graficCoche3_2;
	Grafic graficCamion2;
	Grafic graficTractor2;
	Grafic graficPuntosyNivel[10];
	Grafic graficVidas;
	Grafic graficCuevas;
	Grafic graficGameOver;
	Grafic graficNivel;
	Grafic graficPuntos;
	Grafic graficVidasLetras;
	Grafic graficTiempo;
	Grafic graficMosca;

	Cova cueva[N_MAX_CUEVAS];
	Granota granota;
	Mosca mosca;
	Vehicle vehicle[N_MAX_CARRILES];
	Carriles carriles[N_MAX_CARRILES];
	ColaVehiclesCarril colaVehicles[N_MAX_CARRILES];

	bool esAccesible();
};