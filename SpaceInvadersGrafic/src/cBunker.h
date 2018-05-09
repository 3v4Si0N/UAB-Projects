#ifndef CBUNKER_H
#define CBUNKER_H

class cBunker
{

private:
	bool Destruido;
	int coordenadaBunker[2];
public:
	bool EstaDestruido();
	void SetDestruido();
	void RomperBunker();
	void SetCoordenadaBunker(int coordenada_X, int coordenada_Y);
	int GetCoordenadaBunker(char XoY);
	
};

#endif