#ifndef _CGRAFIC
#define _CGRAFIC

#include "libreria.h"

class cGrafic
{
private:
	struct T_SPRITE *sprite;

public:
	cGrafic(void);
	~cGrafic(void);

	bool Crear (char *nom);
	int getScaleX();
	int getScaleY();
	void setScale(float scale);
	void Destruir ();
	void Dibuixar (int x, int y);
};

#endif