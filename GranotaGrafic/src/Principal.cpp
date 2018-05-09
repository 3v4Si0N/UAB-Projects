#include "Menu.h"
#include "Extras.h"
#include "Joc.h"
#include "GestioResultats.h"

#define OPCIO_JUGAR '1'
#define OPCIO_CONFIGURAR '2'
#define OPCIO_PUNTUACIO '3'
#define OPCIO_SORTIR '4'

/**
 * Programa principal que permet jugar al joc de la granota (frog) desant puntuacions
 * @return
 */
int main()
{
	char opcio;
	int punts;
	int posicio = -1;
	char nivell = '1';

	GestionResultados GR;
	GR.leerPuntuacion();

	do
	{
		clearScreen();
		mostraMenuPrincipal();
		opcio = _getch(); // Llegeix tecla apretada
		switch (opcio)
		{
			case OPCIO_JUGAR:
				punts = juga(nivell-'0'); // COMPTE!: aquí hi ha una conversió de char a int
				clearScreen();
				posicio = GR.haMejoradoPuntuacion(punts);
				if (posicio >= 0) // Ha millorat puntuacio
				{
					GR.desplazarArray(posicio);
					GR.rellenarPosicioArray(posicio, punts);
				}
				break;
			case OPCIO_CONFIGURAR:
				do
				{
					clearScreen();
					mostraMenuNivellDificultat();
					nivell = _getch(); // Llegeix tecla apretada
					if ((nivell != '1') && (nivell != '2') && (nivell != '3')) // Comprova si tecla es valida
					{
						cout << "Opcion incorrecta.\n"; // Mostra text
						Sleep(2000);
					}
				} while ((nivell != '1') && (nivell != '2') && (nivell != '3')); // Repeteix mentre tecla no valida
				break;
			case OPCIO_PUNTUACIO:
				clearScreen();
				GR.escribirRanking();
				cout << "Presione una tecla para volver al menu principal";
				_getch(); // Llegeix tecla apretada
				break;
		}
		GR.escribirPuntuacion();
	} while (opcio != OPCIO_SORTIR);
	return 0;
}
