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
		analizer.performFlag = true;
		analizer.start();
		Node* node = analizer.dataTree->getLast();
		while (node->parent!=NULL){
			node = node->parent;
		}
		node = node->left;
		printf("result = %d\n", node->data.shortValue);
		puts("Complete.");
	}
	catch (Error* err){
		printf("%s. Line: %d, pos: %d", err->inf, err->line, err->pos);
	}
	getch();
	return 0;
}