#pragma once

class Area
{
public:
	Area();
	Area(int minX, int maxX, int minY, int maxY);
	~Area();
	int getMinX();
	int getMaxX();
	int getMinY();
	int getMaxY();
	bool pertenece(int x, int y);
	bool incluir(Area area);
	bool solapar(Area area);
private:
	int m_minX;
	int m_maxX;
	int m_minY;
	int m_maxY;
};

