#include "Menu.h"
#include "Extras.h"
#include "Joc.h"
#include "GestioResultats.h"
#include <conio.h>
#include <stdio.h>
#include <windows.h>
#include <stdlib.h>
#include <iostream>

// Definició de les tecles per cadascuna de les opcions del menú
const char OPCIO_JUGAR = '1';
const char OPCIO_NIVELL = '2';
const char OPCIO_PUNTUACIONS = '3';
const char OPCIO_SORTIR = '4';


int main()
{
	char opcio;
	int nivell=3;
	int posicio=-1;
	int punts=0;

	GestionResult objResult;
	objResult.LeerPuntuacion();
	do
	{
		clearScreen();
		MenuPrincipal();
		opcio = getch();

		switch(opcio)
		{
		case OPCIO_JUGAR: 
			// Crida a la funció que executa el joc amb el nivell actual. 
			// Quan s'acaba el joc comprova si la puntuació obtinguda és millor
			// que alguna de les guardades a la taula, i en aquest cas, l'afegeix
			// com a millor puntuació a la posició que toqui
			punts = Joc(nivell);
			clearScreen();
			posicio = objResult.EsMillorPuntuacio(punts);
			if (posicio>=0)
			{
				objResult.DesplacarArray(posicio);
				objResult.EmplenarPosicioArray(posicio, punts);
			}	
			break;
		case OPCIO_NIVELL: 
			do
			{
				clearScreen();
				MenuNivellDificultat();
				nivell = getch() - '0'; // Converteix el caràcter llegit en un nº
				if ((nivell < 1) || (nivell > 3))
				{
					printf("Opcion incorrecta.\n");
					Sleep(200);
				}
			} while((nivell < 1) || (nivell > 3));
			break;
		case OPCIO_PUNTUACIONS:
			clearScreen();
			objResult.EscriuRanking();
			printf ("Presione una tecla para volver al menu principal");
			getch();
			break;
		case OPCIO_SORTIR: 
			break;
		default: 
			printf("Opcion incorrecta\n \n");
			system("PAUSE");
		}
		objResult.EscribirPuntuaciones();
	} while (opcio!=OPCIO_SORTIR);
	return 0;
}