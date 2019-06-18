#pragma once
#include "defs.h"
#include <stack>

struct Node{
	bool begin;
	SemantTypes semType;
	Token name;
	TextPointer uk;
	Node* parent;
	Node* left;
	Node* right;

	Token* getClassName() {
		if (semType == STclass)
			return &name;
		else
			return &right->parent->name;
	}
};

class DataTree{
private:
	Node* rootUK;
	Node* curUK;
	Node* lastUK;
public:
	DataTree();
	~DataTree();
	Node* addNode(Token lex, Node* type);
	void openBranch();
	void closeBranch();
	Node* findUp(Token lex);
	Node* findDown(Token lex);
	Node* getCurrent();
	void copyFromCurrentToLast();
private:
	void clearRec(Node*);
	void copy(Node* src, Node* trg);
	Node* copyNode(Node* src);
};