#pragma once
#include "defs.h"
#include "Error.h"
#include "Warning.h"
#include "Scaner.h"

class SemAnalizer
{
private:
	Scaner* scaner;
public:
	SemAnalizer(Scaner* _scaner);
	~SemAnalizer();
	void start();
private:
	void functionDeclaration();
	void variableDeclaration();
	void block();
	void oneOperator();
	void callFunction();
	SemantTypes expression();
	SemantTypes expression2();
	SemantTypes expression3();
	SemantTypes expression4();
	SemantTypes expression5();
	SemantTypes expression6();
	SemantTypes expression7();
	SemantTypes expression8();

	SemantTypes typeAnalis(LexemeTypes ltype);
	SemantTypes typeAnalis(Token lex, LexemeTypes ltype);
	SemantTypes compareTypes(SemantTypes type1, SemantTypes type2);
};

