#include "GestioResultats.h"

const char* GestionResultados::PuntuacionesFrogger = "puntuaciones.txt";


//	Constructor y Destructor
GestionResultados::GestionResultados()
{
	int i;

	for (i = 0; i<MAX_MEJORESJUGADORES; i++)
	{
		strcpy(TmejoresJugadores[i].nombre, "--------------");
		TmejoresJugadores[i].puntuacion = 0;
	}
}
GestionResultados::~GestionResultados()
{
}

int GestionResultados::haMejoradoPuntuacion(int puntos)
{
	int i = 0;
	int trobat = 0;

	do
	{
		if (TmejoresJugadores[i].puntuacion < puntos)
		{
			trobat = 1;
		}
		i++;
	} while ((i < MAX_MEJORESJUGADORES) && (!trobat));
	if (trobat)
	{
		return (i - 1);
	}
	else
	{
		return -1;
	}
}

void GestionResultados::desplazarArray(int posicion)
{
	int i;

	for (i = MAX_MEJORESJUGADORES - 1; i > posicion; i--)
	{
		strcpy(TmejoresJugadores[i].nombre, TmejoresJugadores[i - 1].nombre);
		TmejoresJugadores[i].puntuacion = TmejoresJugadores[i - 1].puntuacion;
	}
}

void GestionResultados::rellenarPosicioArray(int posicion, int puntos)
{
	cout << "Escribe tu nombre: ";
	cin >> TmejoresJugadores[posicion].nombre;
	TmejoresJugadores[posicion].puntuacion = puntos;
}

void GestionResultados::escribirRanking()
{
	int i;
	cout << "<------ Mejores Puntuaciones ------>\n";
	cout << "\n";
	for (i = 0; i<MAX_MEJORESJUGADORES; i++)
	{
		cout << i + 1 << ".- " << TmejoresJugadores[i].nombre << " " << TmejoresJugadores[i].puntuacion << "\n";
	}
	cout << "\n<---------------------------------->\n";
	cout << "\n";
}


//===================================================================================
// NOM: EscribirPuntuaciones
//===================================================================================
// DESCRIPCIÓ: Guarda la puntuacion en el fichero puntuaciones.txt
//===================================================================================
void GestionResultados::escribirPuntuacion()
{
	ofstream ficheroFrogger;
	ficheroFrogger.open(PuntuacionesFrogger);

	if (ficheroFrogger.is_open())
	{
		for (int i = 0; i<MAX_MEJORESJUGADORES; i++)
		{
			ficheroFrogger << TmejoresJugadores[i].nombre << " " << TmejoresJugadores[i].puntuacion << "\n";

		}
		ficheroFrogger.close();
	}
}



//===================================================================================
// NOM: LeerPuntuaciones
//===================================================================================
// DESCRIPCIÓ: Lee la puntuacion que está guardada en el fichero puntuaciones.txt
//===================================================================================
void GestionResultados::leerPuntuacion()
{
	ifstream leerFicheroFrogger;
	bool final;
	int count;

	leerFicheroFrogger.open(PuntuacionesFrogger);
	if (leerFicheroFrogger.is_open())
	{
		final = leerFicheroFrogger.eof();
		count = 0;
		while (!final && count<MAX_MEJORESJUGADORES)
		{

			leerFicheroFrogger >> TmejoresJugadores[count].nombre;
			leerFicheroFrogger >> TmejoresJugadores[count].puntuacion;
			final = leerFicheroFrogger.eof();
			count++;
		}
		leerFicheroFrogger.close();
	}

}