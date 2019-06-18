#pragma once
#include "defs.h"
#include "Error.h"

class Scaner
{
private:
	Modul text; //�������� ����� ������
	int uk; //��������� ������� � ������
	int line;
	int pos;
public:
	Scaner(char *path);
	~Scaner();
	LexemeTypes scan(Token token);
	TextPointer getUK();
	void putUK(TextPointer pointer);
private:
	void newLine();
};

