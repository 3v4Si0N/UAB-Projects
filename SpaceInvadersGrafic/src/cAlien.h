#ifndef CALIEN_H
#define CALIEN_H

class cAlien
{

private:

	//===================================================================================
	//Variables
	//===================================================================================
	int TipoAlien;
	bool Vivo_Muerto;
	int coordenadaAlien[2];

public:

	//===================================================================================
	//Constructor y destructor
	//===================================================================================
	cAlien();
	~cAlien();

	//===================================================================================
	//SET & GET
	//===================================================================================
	void SetTipoAlien(int tipo);
	int GetTipoAlien();
	void SetVivo();
	bool EstaVivo_Muerto();
	void SetCoordenadaAlien(int coordenada_X, int coordenada_Y);
	int GetCoordenadaAlien(char XoY);
	void MatarAlien();

};

#endif