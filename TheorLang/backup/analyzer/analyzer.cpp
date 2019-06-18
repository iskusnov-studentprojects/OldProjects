// analyzer.cpp: определяет точку входа для консольного приложения.
//
#include <fstream>
#include <iostream>
#include <vector>
#include <conio.h>
#include "Scaner.h"
#include "SemAnalizer.h"

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	Scaner* sc = new Scaner("input.c\0");
	SemAnalizer analizer(sc);
	try{
		analizer.start();
		puts("Complete.");
	}
	catch (Error* err){
		printf("%s. Line: %d, pos: %d", err->inf, err->line, err->pos);
	}
	getch();
	return 0;
}