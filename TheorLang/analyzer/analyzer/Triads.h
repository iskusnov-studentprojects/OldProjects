#pragma once
#include "defs.h"
#include "Error.h"
#include "Warning.h"
#include "Scaner.h"
#include "DataTree.h"
#include "MyStack.h"

struct Triad {
	SemantTypes operation;
	Triad* firstOperand;
	Triad* secondOperand;
	Node* link;
	Node* constanta;
};

class Triads
{
	MyStack<Triad*> triads;

	MyStack<Triad*> address;
	MyStack<LexemeTypes> operators;
	MyStack<Node*> operands;
public:
	Triads();
	~Triads();

};