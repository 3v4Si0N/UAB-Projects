#include "cGrafic.h"

#include "libreria.h"


cGrafic::cGrafic(void)
{
}


cGrafic::~cGrafic(void)
{
}

bool cGrafic::Crear (char *nom)
{
	sprite = Sprite_Create (nom);
	if (sprite->frame_actual->pixels != NULL)
		return true;
	else 
		return false;
}

int cGrafic::getScaleX()
{
	return sprite->frame_actual->tamx;
}

int cGrafic::getScaleY()
{
	return sprite->frame_actual->tamy;
}
	

void cGrafic::setScale(float scale)
{
	Sprite_SetScale(sprite, scale);
}

void cGrafic::Destruir ()
{
	Sprite_Delete(sprite);
}

void cGrafic::Dibuixar (int x, int y)
{
	Sprite_Draw(sprite, x, y);
}