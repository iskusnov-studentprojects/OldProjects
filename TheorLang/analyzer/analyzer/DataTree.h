#pragma once
#include "defs.h"
#include <stack>

union Data
{
	short int shortValue;
	long int longValue;
};

struct Node{
	bool begin;
	Data data;
	SemantTypes semType;
	Token name;
	TextPointer uk;
	Node* parent;
	Node* left;
	Node* right;
};

class DataTree{
private:
	Node* rootUK;
	Node* curUK;
	Node* lastUK;
public:
	DataTree();
	~DataTree();
	Node* addNode(Token lex, SemantTypes type);
	void openBranch();
	void closeBranch();
	Node* findUp(Token lex);
	Node* findDown(Token lex);
	Node* getCurrent();
	Node* getLast();
	void setLast(Node* node);
	void copy(Node* src, Node*& trg);
	void clearRec(Node*);
private:
	Node* copyNode(Node* src);
};