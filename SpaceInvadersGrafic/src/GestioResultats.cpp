#include "GestioResultats.h"


const char* GestionResult::PuntuacionesSpace = "puntuaciones.txt";


//	Constructor y Destructor
GestionResult::GestionResult()
{
	int i;

	for (i = 0; i<MAX_MILLORSJUGADORS; i++)
	{
		strcpy(millorsJugadors[i].nom, "--------------");
		millorsJugadors[i].puntuacio = 0;
	}
}
GestionResult::~GestionResult()
{
}


//===================================================================================
// NOM: EsMillorPuntuacio
//===================================================================================
// DESCRIPCI�: Mira si la puntuaci� que es passa a "punts" �s millor que alguna de les
// que hi ha guardada a la taula de millors puntuacions. Si �s millor, retorna l'�ndex
// de la primera posici� de la taula que s'ha trobat amb una puntuaci� inferior. Si no 
// en troba cap d'inferior retorna un -1
//===================================================================================
int GestionResult::EsMillorPuntuacio(int punts)
{
	int i=0;
	int trobat=0;

	do
	{
		if(millorsJugadors[i].puntuacio < punts)
		{
			trobat=1;
		}
		i++;

	}while ((i<MAX_MILLORSJUGADORS) && (!trobat));

	if (trobat)
	{
		return (i-1);
	}
	else
	{
		return -1;
	}
}

//===================================================================================
// NOM: DesplacarArray
//===================================================================================
// DESCRIPCI�: Mou tots els registres de la taula a partir de "posicio" una posicio a
// la dreta per deixar lloc a un nou registre de puntuacions
//===================================================================================
void GestionResult::DesplacarArray(int posicio)
{
	int i;

	for (i=MAX_MILLORSJUGADORS-1; i>posicio; i--)
	{
		strcpy(millorsJugadors[i].nom,millorsJugadors[i-1].nom);
		millorsJugadors[i].puntuacio=millorsJugadors[i-1].puntuacio;
	}
}

//===================================================================================
// NOM: EmplenarPosicioArray
//===================================================================================
// DESCRIPCI�: omple un registre de puntuacions ("jugador") amb els punts que es passen
// com a par�metre i el nom que s'introdueix per teclat
//===================================================================================
void GestionResult::EmplenarPosicioArray(int posicion, int puntos)
{
	cout << "Escribe tu nombre\n";
	cin >> millorsJugadors[posicion].nom;
	millorsJugadors[posicion].puntuacio=puntos;
}


//===================================================================================
// NOM: EscriuRanking
//===================================================================================
// DESCRIPCI�: mostra per pantalla les millors puntuacions
//===================================================================================
void GestionResult::EscriuRanking()
{
	int i;
	cout << "<------ Mejores Puntuaciones ------>\n";
	cout << "\n";
	for (i = 0; i<MAX_MILLORSJUGADORS; i++)
	{
		cout << i+1 << ".- " << millorsJugadors[i].nom << " " << millorsJugadors[i].puntuacio << "\n";
	}
	cout << "\n<---------------------------------->\n";
	cout << "\n";
}

//===================================================================================
// NOM: EscribirPuntuaciones
//===================================================================================
// DESCRIPCI�: Guarda la puntuacion en el fichero puntuaciones.txt
//===================================================================================
void GestionResult::EscribirPuntuaciones()
{
	ofstream ficheroSpace;
	ficheroSpace.open(PuntuacionesSpace);

	if (ficheroSpace.is_open())
	{
		for (int i = 0; i<MAX_MILLORSJUGADORS; i++)
		{
			ficheroSpace << millorsJugadors[i].nom << " " << millorsJugadors[i].puntuacio << "\n";

			}
			ficheroSpace.close();
		}
	}



//===================================================================================
// NOM: LeerPuntuaciones
//===================================================================================
// DESCRIPCI�: Lee la puntuacion que est� guardada en el fichero puntuaciones.txt
//===================================================================================
void GestionResult::LeerPuntuacion()
{
	ifstream leerFicheroSpace;
	bool final;
	int count;

	leerFicheroSpace.open(PuntuacionesSpace);
	if (leerFicheroSpace.is_open())
	{
		final = leerFicheroSpace.eof();
		count = 0;
		while (!final && count<MAX_MILLORSJUGADORS)
		{

			leerFicheroSpace >> millorsJugadors[count].nom;
			leerFicheroSpace >> millorsJugadors[count].puntuacio;
			final = leerFicheroSpace.eof();
			count++;
		}
		leerFicheroSpace.close();
	}

}