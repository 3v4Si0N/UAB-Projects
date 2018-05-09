#include "Joc.h"

/**
 * Juga una partida de tres vides
 *
 * @param nivell nivell de la partida
 * @return
 */
int juga(int nivell)
{
	t_programStatus estat;

	//------INICIALIZACION DE VARIABLES------//
	bool haMortLaGranota, GranotaComeLaMosca, nivelSuperado, bcontar;
	int vidas = 3, puntos = 0, contador = 0, direction = ARRIBA, contador2 = 0, contador3 = 0, tiempo;
	bool bcontadorMosca = false;
	bool bcontadorVel = false;
	bool temporizadorAcero = false;

	// Inicialitzacions necessàries
	InitGame(estat);

	// Mostrem la finestra
	Video_ShowWindow();

	// Inizialitza llavor per a la funcio rand()
	srand((unsigned)time(NULL));

	Pantalla pantalla(nivell);
	pantalla.PuntosVidas();

	do
	{
		haMortLaGranota = false;
		GranotaComeLaMosca = false;
		nivelSuperado = false;
		bcontar = false;
		pantalla.Inicializar(); // Inicializa la granota y la mosca
		tiempo = (2000*nivell) - 1000;

		do
		{
			ProcessEvents(estat); // Captura els events que s'han produit en el darrer cicle

			if (Keyboard_GetKeyTrg(KEYBOARD_LEFT))
			{
				bcontar = true;
				pantalla.moverGranota(IZQUIERDA);
				direction = IZQUIERDA;
			}

			if (Keyboard_GetKeyTrg(KEYBOARD_RIGHT))
			{
				bcontar = true;
				pantalla.moverGranota(DERECHA);
				direction = DERECHA;
			}

			if (Keyboard_GetKeyTrg(KEYBOARD_UP))
			{
				bcontar = true;
				pantalla.moverGranota(ARRIBA);
				direction = ARRIBA;
				if (pantalla.estaGranotaDentroCueva())
				{
					nivelSuperado = true;
					if (rand() % 5 == 0)
						bcontadorMosca = true;
					tiempo = (2000*nivell) - 1000;
				}
			}

			if (Keyboard_GetKeyTrg(KEYBOARD_DOWN))
			{
				bcontar = true;
				pantalla.moverGranota(ABAJO);
				direction = ABAJO;
			}

			//Contador para dibujar los 2 movimientos de la rana
			if (bcontar)
			{
				contador++;
				if (contador >= 5)
				{
					contador = 0;
					bcontar = false;
				}
				pantalla.ActualizarGranota(bcontar, direction);
			}

			// Dibuixa els gràfics
			pantalla.dibujarGraficos(nivell, vidas);

			pantalla.moverColaVehicles();

			//Contador para dibujar una mosca durante un determinado tiempo
			if (bcontadorMosca)
			{
				contador2++;
				if (contador2 >= 400)
				{
					contador2 = 0;
					bcontadorMosca = false;
				}
				pantalla.dibujarGraficMosca();
				if (pantalla.GranotaComeMosca())
				{
					GranotaComeLaMosca = true;
					bcontadorMosca = false;
					puntos += 2000;
					bcontadorVel = true;
				}
			}

			//Contador para realentizar el movimiento de los coches cuando la rana se come una mosca
			if (bcontadorVel)
			{
				contador3++;
				if (contador3 >= 600)
				{
					contador3 = 0;
					bcontadorVel = false;
					pantalla.reinicializarVelocidadVehicles(nivell);
				}
				pantalla.disminuirVelocidadVehicles();
			}


			//colision con el coche
			if (pantalla.haMuertoGranota())
				haMortLaGranota = true;

			pantalla.PushVehicle(nivell); //Insertar un vehiculo en las colas
			pantalla.SumarPuntos(puntos); // Sumar puntos partida
			tiempo--;
			pantalla.Temporizador(tiempo);

			VideoUpdate(estat); // Actualitza la pantalla

		} while ((tiempo > 0) && (!nivelSuperado) && (!haMortLaGranota) && (!Keyboard_GetKeyTrg(KEYBOARD_ESCAPE)));

		if (nivelSuperado == true)
		{
			puntos += 100* nivell;
			if (pantalla.TodasCuevasOcupadas())
			{
				nivell++;
				vidas++;
				pantalla.LimpiarCuevas();
				pantalla.InicializarColaVehicles(nivell);
			}
		}

		if (haMortLaGranota || tiempo == 0)
			vidas--;

	} while ((nivell < 4) && (vidas > 0) && (!Keyboard_GetKeyTrg(KEYBOARD_ESCAPE)) && (!estat.bExit));

	if (vidas == 0 || (Keyboard_GetKeyTrg(KEYBOARD_ESCAPE)))
		pantalla.GraficGameOver();
	VideoUpdate(estat);
	Sleep(2000);

	//Libera y cierra la ventana del juego
	Video_Release();

	return puntos;
}
