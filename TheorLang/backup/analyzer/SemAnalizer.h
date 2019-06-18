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
	DataTree* dataTree;
	stack<Warning*> warnings;
	MyStack<int> *myStack;
	bool performFlag;
public:
	SemAnalizer(Scaner* _scaner);
	~SemAnalizer();
	void start();
private:
#pragma region Рекурсивный метод анализа
	void recursiveAnalyzer();
	void variableDeclaration();
	void functionDeclaration();
	void classDeclaration();
	void block();
	void oneOperator();
	void callFunction();
	Node* expression();
	Node* expression2();
	Node* expression3();
	Node* expression4();
	Node* expression5();
	Node* expression6();
	Node* expression7();
	Node* expression8();
	void idHandler();
#pragma endregion

#pragma region Семантические подпрограммы
	Node* typeAnalis(Token lex, LexemeTypes type);
	Node* convertType(Node* sourtheType, Node* targetType);
#pragma endregion

public:
	void LL1Analizer();
private:
	bool isTerminal(int value);
	char* getLexByType(LexemeTypes value);
	char* stringConcatenation(char* firstStr, char* secondStr);
};
