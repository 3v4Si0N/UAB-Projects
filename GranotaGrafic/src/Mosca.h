#include "Area.h"
#include "lib\Grafic.h"
#include <cstdlib>


// Fi del taulell respecte la cantonada superior esquerre
#define FIN_X 600
#define FIN_Y 450
#define INICIO_X 0
#define INICIO_Y 0

class Mosca
{
public:

	Mosca();
	Mosca(Grafic graficMosca, int posicionInicialX, int posicionInicialY);
	~Mosca();

	Area getAreaMosca();
	void dibujarMosca();
	void moverAPosicionInicial();

private:

	Grafic graficMosca;
	int posicionMoscaX;
	int posicionMoscaY;
	int posicionInicialX;
	int posicionInicialY;
};