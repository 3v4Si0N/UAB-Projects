#include "extras.h"


void clearScreen()
{
	//Método que limpia la pantalla
#ifdef _WIN32
	HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
	COORD coord = { 0, 0 };
	DWORD count;
	CONSOLE_SCREEN_BUFFER_INFO csbi;
	GetConsoleScreenBufferInfo(hStdOut, &csbi);
	FillConsoleOutputCharacter(hStdOut, ' ',
		csbi.dwSize.X * csbi.dwSize.Y,
		coord, &count);
	SetConsoleCursorPosition(hStdOut, coord);
#else
	system("clear");
#endif
}


int RealRandom()
{
	int array[100];
	int valorFinal = 0;

	for (int i = 0; i<100; i++)
		array[i] = i;

	srand(time(NULL));

	int arrayB[10];

	for (int i = 0; i<10; i++)
		arrayB[i] = array[rand() % 100];

	for (int i = 0; i<5; i++)
		valorFinal += arrayB[rand() % 10];

	return (valorFinal%10);

}