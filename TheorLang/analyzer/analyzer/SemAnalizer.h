#pragma once
#include "defs.h"
#include "Error.h"
#include "Warning.h"
#include "Scaner.h"
#include "DataTree.h"
#include "MyStack.h"

class SemAnalizer
{
private:
	Scaner* scaner;
	stack<Warning*> warnings;
	MyStack<int> *myStack;
	MyStack<Data> *polis;
	Data tmpdata;
	Data tmpdata2;
public:
	DataTree* dataTree;
	SemAnalizer(Scaner* _scaner);
	~SemAnalizer();
	bool performFlag;
	void start();
private:
#pragma region Рекурсивный метод анализа
	void recursiveAnalyzer();
	void variableDeclaration();
	void functionDeclaration();
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
#pragma endregion

#pragma region Семантические подпрограммы
	SemantTypes typeAnalis(Token lex, LexemeTypes type);
	SemantTypes convertType(SemantTypes sourtheType, SemantTypes targetType);
#pragma endregion

public:
	void LL1Analizer();
private:
	bool isTerminal(int value);
	char* getLexByType(LexemeTypes value);
	char* stringConcatenation(char* firstStr, char* secondStr);
};
