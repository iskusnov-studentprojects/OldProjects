#include "DataTree.h"
#include "Error.h"

DataTree::DataTree()
{
	rootUK = new Node();
	rootUK->semType = STempty;
	lastUK = rootUK;
}


DataTree::~DataTree()
{
	clearRec(rootUK);
}

Node* DataTree::addNode(Token lex, SemantTypes type) {
	curUK = lastUK;
	while (curUK->semType != STempty) {
		if (strcmp(lex, curUK->name) == 0) {
			throw new Error(DeclarationError, strcat(lex, " already declared"));
		}
		curUK = curUK->parent;
	}
	lastUK->left = new Node();
	lastUK->left->parent = lastUK;
	lastUK = lastUK->left;
	strcpy(lastUK->name, lex);
	lastUK->semType = type;
	lastUK->right;
	TextPointer point;
	point.line = -1;
	point.pos = -1;
	point.uk = -1;
	lastUK->uk = point;
	return lastUK;
}

void DataTree::openBranch(){
	lastUK->right = new Node();
	lastUK->right->parent = lastUK;
	lastUK = lastUK->right;
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

Node* DataTree::getLast() {
	return lastUK;
}

void DataTree::setLast(Node* node) {
	lastUK = node;
}

void DataTree::clearRec(Node* current){
	if (current == NULL)
		return;
	if (current->left != NULL)
		clearRec(current->left);
	if (current->right != NULL)
		clearRec(current->right);
	delete current;
}

Node* DataTree::copyNode(Node* src) {
	Node* res = new Node();
	strcpy(res->name, src->name);
	res->semType = src->semType;
	res->data = src->data;
	res->uk = src->uk;
	return res;
}

void DataTree::copy(Node* src, Node*& tg)
{
	//проход, пока исходное дерево не достигло конца
	if (src != NULL)
	{
		//создание нового элемента дерева
		tg = copyNode(src);
		if (src->left != NULL)
		{
			tg->left = new Node();
			copy(src->left, *&tg->left);
			tg->left->parent = tg;
		}
		else
			tg->left = NULL;
	}
}