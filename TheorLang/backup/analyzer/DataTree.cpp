#include "DataTree.h"
#include "Error.h"

DataTree::DataTree()
{
	rootUK = new Node();
	rootUK->semType = STempty;
	lastUK = rootUK;

	//Добавление в дерево базовых типов
	//short int
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, "short int");
	lastUK->semType = STshortint;
	lastUK->right = NULL;

	//long int
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, "long int");
	lastUK->semType = STlongint;
	lastUK->right = NULL;

	//void
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, "void");
	lastUK->semType = STvoid;
	lastUK->right = NULL;

	//class
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, "class");
	lastUK->semType = STclass;
	lastUK->right = NULL;

	//object
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, "object");
	lastUK->semType = STobject;
	lastUK->right = NULL;
}


DataTree::~DataTree()
{
	clearRec(rootUK);
}

Node* DataTree::addNode(Token lex, Node* type) {
	curUK = lastUK;
	while (curUK->semType != STempty)
		if (strcmp(lex, curUK->name) == 0)
			throw new Error(DeclarationError, strcat(lex, " already declared"));
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, lex);
	if (type->semType == STclass)
		lastUK->semType = STobject;
	else
		lastUK->semType = type->semType;
	lastUK->right = type->right;
	TextPointer point;
	point.line = -1;
	point.pos = -1;
	point.uk = -1;
	lastUK->uk = point;
	return lastUK;
}

void DataTree::openBranch(){
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	lastUK->semType = STempty;
}

void DataTree::closeBranch(){
	while (lastUK->semType != STempty) {
		lastUK = lastUK->parent;
		if (lastUK->parent == NULL)
			throw new Error(OtherError, "System error");
	}
	lastUK = lastUK->parent;
}

Node* DataTree::findUp(Token lex){
	curUK = lastUK;
	while (strcmp(lex, curUK->name) != 0 && curUK->parent != NULL)
		curUK = curUK->parent;
	if (curUK->parent == NULL)
		throw new Error(DeclarationError, strcat(lex, " is not declared"));
	return curUK;
}

Node* DataTree::findDown(Token lex){
	curUK = curUK->right;
	while(curUK != NULL && strcmp(lex, curUK->name) != 0)
	if (curUK == NULL)
		throw new Error(DeclarationError, strcat(lex, " is not declared"));
	return curUK;
}

Node* DataTree::getCurrent() {
	return curUK;
}

void DataTree::clearRec(Node* current){
	if (current->left != NULL)
		clearRec(current->left);
	if (current->right != NULL)
		clearRec(current->right);
	delete current;
}

void DataTree::copyFromCurrentToLast() {
	if(curUK != NULL && curUK->right != NULL && curUK->right->left != NULL)
		copy(curUK->right->left, lastUK);
}

Node* DataTree::copyNode(Node* src) {
	Node* res = new Node();
	res->parent = src->parent;
	strcpy(lastUK->name, src->name);
	res->semType = src->semType;
	return res;
}

void DataTree::copy(Node* src, Node* tg)
{
	if (src == NULL)
		tg = NULL;
	//проход, пока исходное дерево не достигло конца
	if (src != NULL)
	{
		//создание нового элемента дерева
		if(tg == NULL)
			tg = copyNode(src);
		if (src->left != NULL)
		{
			copy(src->left, tg->left);
		}
		else
			tg->left = NULL;

		if (src->right != NULL)
		{
			copy(src->right, tg->right);
		}
		else
			tg->right = NULL;
	}
}