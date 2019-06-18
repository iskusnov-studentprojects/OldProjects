#include "SemAnalizer.h"


SemAnalizer::SemAnalizer(Scaner* _scaner)
{
	scaner = _scaner;
	dataTree = new DataTree();
	myStack = new MyStack<int>();
	polis = new MyStack<Data>();
}


SemAnalizer::~SemAnalizer()
{
	delete scaner;
	delete dataTree;
	while (!warnings.empty()) warnings.pop();
}

void SemAnalizer::start(){
	try{
		setlocale(LC_ALL, "Russian");
		recursiveAnalyzer();
	}
	catch (Error* er){
		er->line = scaner->getUK().line;
		er->pos = scaner->getUK().pos;
		throw er;
	}
}

#pragma region Рекурсивный метод анализа
void SemAnalizer::recursiveAnalyzer(){
	TextPointer pointer;
	LexemeTypes type;
	Token lex;
	do{
		pointer = scaner->getUK();
		type = scaner->scan(lex);
		switch (type)
		{
		case Tend: break;
		case Tvoid: scaner->putUK(pointer);
			functionDeclaration();
			break;
		case Tshort:
		case Tlong:
			scaner->putUK(pointer);
			variableDeclaration();
			break;
		default: throw new Error(SyntaxError, "ожидается declaration\0");
		}
	} while (type != Tend);
	if (performFlag) {
		Node* mainNode = dataTree->findUp("main");
		pointer = scaner->getUK();
		scaner->putUK(mainNode->uk);
		block();
		scaner->putUK(pointer);
	}
}

void SemAnalizer::variableDeclaration(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex, typeLex;
	Node* node;

	//Определим тип переменных
	pointer = scaner->getUK();
	ltype = scaner->scan(typeLex);
	if (ltype == Tshort || ltype == Tlong) {
		if(scaner->scan(lex) != Tint)
			throw new Error(SyntaxError, "ожидается int\0");
	}
	stype = typeAnalis(typeLex, ltype);

	//Анализируем список переменных
	do{
		if (scaner->scan(lex) != Tid)
			throw new Error(SyntaxError, "ожидается id\0");
		//Добавить идентификатор в дерево
		node = dataTree->addNode(lex, stype);
		ltype = scaner->scan(lex);
		if (ltype == Tsemicolon) break;
		if (ltype == Tcomma) continue;
		if (ltype != Tassignment)
			throw new Error(SyntaxError, "ожидается =\0");
		SemantTypes stype2, stype3;
		stype2 = expression();
		stype3 = convertType(stype2, stype);
		//Интерпритация
		if (performFlag) {
			/*if (stype3 == STlongint) {
				node->data.longValue = polis->pop().longValue;
			}
			else {
				node->data.shortValue = polis->pop().shortValue;
			}
			*/
			node->data = polis->pop();
			node->semType = stype3;
		}
		//Конец интерпритации

		ltype = scaner->scan(lex);
		if (ltype != Tcomma && ltype != Tsemicolon)
			throw new Error(SyntaxError, "ожидается ;\0");
	} while (ltype != Tsemicolon);
}

void SemAnalizer::functionDeclaration(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type;
	Node* fn;
	Token lex;

	int saveFlag = performFlag;
	performFlag = false;

	ltype = scaner->scan(lex);
	type = typeAnalis(lex, ltype);
	if (scaner->scan(lex) != Tid)
		throw new Error(SyntaxError, "ожидается id\0");
	//Добавить идентификатор в дерево
	fn = dataTree->addNode(lex, type);
	dataTree->openBranch();

	if (scaner->scan(lex) != Topenbracket)
		throw new Error(SyntaxError, "ожидается (\0");
	do {
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
		if (ltype == Tclosebracket) break;

		if (ltype != Tshort && ltype != Tlong && ltype != Tid)
			throw new Error(SyntaxError, "ожидается ожидается type\0");
		if ((ltype == Tshort || ltype == Tlong) && scaner->scan(lex) != Tint)
			throw new Error(SyntaxError, "ожидается int\0");
		type = typeAnalis(lex, ltype);

		if (scaner->scan(lex) != Tid)
			throw new Error(SyntaxError, "ожидается id\0");
		//Добавить идентификатор
		dataTree->addNode(lex, type);
		ltype = scaner->scan(lex);
		if (ltype != Tcomma && ltype != Tclosebracket)
			throw new Error(SyntaxError, "ожидается )\0");
	} while (ltype != Tclosebracket);
	//Сохраняем указатель на тело функции
	fn->uk = scaner->getUK();
	block();
	dataTree->closeBranch();
	performFlag = saveFlag;
}

void SemAnalizer::block(){
	TextPointer pointer;
	LexemeTypes ltype;
	Token lex;
	if (scaner->scan(lex)!=Topenblock)
		throw new Error(SyntaxError, "ожидается {\0");
	dataTree->openBranch();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype != Tcloseblock)
	{
		scaner->putUK(pointer);
		oneOperator();
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	dataTree->closeBranch();
}

void SemAnalizer::oneOperator(){
	TextPointer pointer;
	LexemeTypes ltype, ltype2;
	SemantTypes type, type2, type3;
	Node* node;
	Token lex, lex2;
	bool saveFlag;
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	switch (ltype)
	{
	case Tshort:
	case Tlong:
		scaner->putUK(pointer);
		variableDeclaration();
		break;
	case Twhile:
		saveFlag = performFlag;
		//Интерпритация
		TextPointer pos;
		if (scaner->scan(lex) != Topenbracket)
			throw new Error(SyntaxError, "ожидается (\0");
		pos = scaner->getUK();
	whl:
		type3 = expression();
		if (performFlag) {
			Data data = polis->pop();
			if (type3 == STlongint ? data.longValue : data.shortValue)
				performFlag = true;
			else
				performFlag = false;
		}

		if (scaner->scan(lex) != Tclosebracket)
			throw new Error(SyntaxError, "ожидается )\0");

		oneOperator();

		if (performFlag) {
			scaner->putUK(pos);
			goto whl;
		}
		//Конец интерпритации
		performFlag = saveFlag;
		break;
	case Tid:
		node = dataTree->findUp(lex);
		type = node->semType;
		ltype2 = scaner->scan(lex);
		//Функция
		if (ltype2 == Topenbracket) {
			scaner->putUK(pointer);
			callFunction();
			break;
		}

		//Присваивание
		if (ltype2 == Tassignment) {
			type2 = expression();
			type3 = convertType(type2, type);

			//Интерпритация
			if (performFlag) {
				node->data = polis->pop();
				node->semType = type3;
				/*if (STlongint == type3)
					node->data.longValue = polis->pop().longValue;
				else
					node->data.shortValue = polis->pop().shortValue;*/
			}
			//Конец интерпритации

			if (scaner->scan(lex) != Tsemicolon)
				throw new Error(SyntaxError, "ожидается ;\0");
			break;
		}
		scaner->putUK(pointer);

		expression();

		if (scaner->scan(lex) != Tsemicolon)
			throw new Error(SyntaxError, "ожидается ;\0");
		break;
	case Treturn:
		if (scaner->scan(lex) != Tsemicolon)
			throw new Error(SyntaxError, "ожидается ;\0");
		break;
	case Topenblock:
		scaner->putUK(pointer);
		block();
		break;
	case Tsemicolon:
		break;
	case Topenbracket:
	case Tplus:
	case Tplusplus:
	case Tminus:
	case Tminusminus:
	case Tnot:
		scaner->putUK(pointer);
		expression();
		if (scaner->scan(lex) != Tsemicolon)
			throw new Error(SyntaxError, "ожидается ;\0");
		break;
	default:
		throw new Error(SyntaxError, "ожидается оператор\0");
	}
}

void SemAnalizer::callFunction() {
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type;
	Token lex;
	Node *cur, *data, *last, *fn;

	scaner->scan(lex);
	fn = dataTree->findUp(lex);
	data = fn->right->left;

	//Интерпритация
	if (performFlag) {
		dataTree->addNode("", STempty);
		dataTree->openBranch();
		last = dataTree->getLast();
		cur = dataTree->getLast();
		if(data != NULL)
			cur->left = new Node();
		dataTree->copy(data,cur->left);
		if(cur->left!=NULL)
			cur->left->parent = last;
		while (cur->left!=NULL){
			cur = cur->left;
		}
		dataTree->setLast(cur);
	}
	//Конец интерпритации

	if (scaner->scan(lex) != Topenbracket)
		throw new Error(SyntaxError, "ожидается (\0");
	cur = data;
	do {
		if (cur != NULL) {
			SemantTypes type = expression();

			//Интерпритация
			if (performFlag) {
				cur->data = polis->pop();
			}
			//Конец интерпритации

			if (convertType(type, cur->semType) == STempty)
				throw new Error(SemantError, "не верный список параметров\0");
			cur = cur->left;
		}
		ltype = scaner->scan(lex);
		if (ltype != Tclosebracket && ltype != Tcomma)
			throw new Error(SyntaxError, "ожидается )\0");
	} while (ltype != Tclosebracket);
	if(cur != NULL)
		throw new Error(SemantError, "не верный список параметров\0");
	if (scaner->scan(lex) != Tsemicolon)
		throw new Error(SyntaxError, "ожидается ;\0");

	//Интерпритация
	if (performFlag) {
		pointer = scaner->getUK();
		scaner->putUK(fn->uk);

		oneOperator();

		scaner->putUK(pointer);
		dataTree->setLast(last);
		dataTree->clearRec(last->left);
		last->left = NULL;
		dataTree->closeBranch();
	}
	//Конец интерпритации
}

SemantTypes SemAnalizer::expression(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2, type3;
	Token lex;
	type = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tor){
		type2 = expression2();
		type3 = convertType(type, type2);
		
		//Интерпритация
		if (performFlag) {
			tmpdata2 = polis->pop();
			tmpdata = polis->pop();
			Data res;
			if (STlongint == type3)
				res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) || (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
			else
				res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) || (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
			polis->push(res);
		}
		//Конец интерпритации

		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

SemantTypes SemAnalizer::expression2(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2, type3;
	Token lex;
	type = expression3();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tand){
		type2 = expression3();
		type3 = convertType(type, type2);

		//Интерпритация
		if (performFlag) {
			tmpdata2 = polis->pop();
			tmpdata = polis->pop();
			Data res;
			if (STlongint == type3)
				res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) && (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
			else
				res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) && (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
			polis->push(res);
		}
		//Конец интерпритации

		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

SemantTypes SemAnalizer::expression3(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2, type3;
	Token lex;
	type = expression4();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tequal || ltype == Tinequal){
		type2 = expression4();
		type3 = convertType(type, type2);

		//Интерпритация
		if (performFlag) {
			tmpdata2 = polis->pop();
			tmpdata = polis->pop();
			Data res;
			if (ltype == Tequal) {
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) == (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) == (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			else
			{
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) != (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) != (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
		}
		//Конец интерпритации

		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

SemantTypes SemAnalizer::expression4(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2, type3;
	Token lex;
	type = expression5();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tmore || ltype == Tless || ltype == Tequalmore || ltype == Tequalless){
		type2 = expression5();
		type3 = convertType(type, type2);

		//Интерпритация
		if (performFlag) {
			tmpdata2 = polis->pop();
			tmpdata = polis->pop();
			Data res;
			if (ltype == Tmore) {
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) > (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) > (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			if (ltype == Tless)
			{
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) < (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) < (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			if (ltype == Tequalmore)
			{
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) >= (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) >= (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			if (ltype == Tequalless)
			{
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) <= (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) <= (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
		}
		//Конец интерпритации

		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

SemantTypes SemAnalizer::expression5(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2, type3;
	Token lex;
	type = expression6();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tplus || ltype == Tminus){
		type2 = expression6();
		type3 = convertType(type, type2);

		//Интерпритация
		if (performFlag) {
			tmpdata2 = polis->pop();
			tmpdata = polis->pop();
			Data res;
			if (ltype == Tplus) {
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) + (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) + (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			if(ltype == Tminus){
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) - (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) - (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
		}
		//Конец интерпритации

		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

SemantTypes SemAnalizer::expression6(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2, type3;
	Token lex;
	type = expression7();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tmul || ltype == Tdiv || ltype == Tpercent){
		type2 = expression7();
		type3 = convertType(type, type2);

		//Интерпритация
		if (performFlag) {
			tmpdata2 = polis->pop();
			tmpdata = polis->pop();
			Data res;
			if (ltype == Tmul) {
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) * (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) * (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			if (ltype == Tdiv) {
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) / (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) / (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
			if (ltype == Tpercent) {
				if (STlongint == type3)
					res.longValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) % (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				else
					res.shortValue = (type == STlongint ? tmpdata.longValue : tmpdata.shortValue) % (type == STlongint ? tmpdata2.longValue : tmpdata2.shortValue);
				polis->push(res);
			}
		}
		//Конец интерпритации

		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

SemantTypes SemAnalizer::expression7(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type, type2;
	Token lex;
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	if (!(ltype == Tnot || ltype == Tminus || ltype == Tplus))
		scaner->putUK(pointer);
	type = expression8();

	//Интерпритация
	if (performFlag && (ltype == Tnot || ltype == Tminus)) {
		tmpdata = polis->pop();
		Data res;
		if (ltype == Tnot) {
			if (STlongint == type)
				res.longValue = !(type == STlongint ? tmpdata.longValue : tmpdata.shortValue);
			else
				res.shortValue = !(type == STlongint ? tmpdata.longValue : tmpdata.shortValue);
			polis->push(res);
		}
		if (ltype == Tminus) {
			if (STlongint == type)
				res.longValue = -(type == STlongint ? tmpdata.longValue : tmpdata.shortValue);
			else
				res.shortValue = -(type == STlongint ? tmpdata.longValue : tmpdata.shortValue);
			polis->push(res);
		}
	}
	//Конец интерпритации

	return type;
}

SemantTypes SemAnalizer::expression8(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes type;
	Node* node;
	Token lex;
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	switch (ltype)
	{
	case Tconst10:
	case Tconst16:
		type = typeAnalis(lex, ltype);
		
		//Интерпритация
		if (performFlag) {
			Data res;
			if (STlongint == type)
				res.longValue = atoi(lex);
			else
				res.shortValue = atoi(lex);
			polis->push(res);
		}
		//Конец интерпритации

		break;
	case Tminusminus:
	case Tplusplus:
		pointer = scaner->getUK();
		if ((ltype = scaner->scan(lex)) != Tid)
			throw new Error(SyntaxError, "ожидается id\0");
		node = dataTree->findUp(lex);
		type = node->semType;

		//Интерпритация
		if (performFlag) {
			if (Tminusminus == ltype) {
				if (STlongint == node->semType)
					node->data.longValue = node->data.longValue - 1;
				else
					node->data.shortValue = node->data.shortValue - 1;
			}
			if (Tplusplus == ltype)
				if (STlongint == node->semType)
					node->data.longValue = node->data.longValue + 1;
				else
					node->data.shortValue = node->data.shortValue + 1;
		}
		//Конец интерпритации

		scaner->putUK(pointer);
	case Tid:
		node = dataTree->findUp(lex);
		type = node->semType;
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
		if (!(ltype == Tplusplus || ltype == Tminusminus)){
			scaner->putUK(pointer);
		}
		//Интерпритация
		if (performFlag) {
			if (Tminusminus == ltype) {
				if (STlongint == node->semType)
					node->data.longValue = node->data.longValue - 1;
				else
					node->data.shortValue = node->data.shortValue - 1;
			}
			if(Tplusplus == ltype) {
				if (STlongint == node->semType)
					node->data.longValue = node->data.longValue + 1;
				else
					node->data.shortValue = node->data.shortValue + 1;
			}
			polis->push(node->data);
		}
		//Конец интерпритации

		break;
	case Topenbracket:
		type = expression();
		if ((ltype = scaner->scan(lex)) != Tclosebracket)
			throw new Error(SyntaxError, "ожидается )\0");
		break;
	default:
		throw new Error(SyntaxError, "ожидается expression\0");
	}
	return type;
}
#pragma endregion

#pragma region Семантические подпрограммы
SemantTypes SemAnalizer::typeAnalis(Token lex, LexemeTypes type){
	SemantTypes res;
	switch (type)
	{
	case Tshort:
		res = STshortint;
		break;
	case Tlong:
		res = STlongint;
		break;
	case Tconst10:
		if (strlen(lex) <= 5 && lex[0] <= '3')
			res = STshortint;
		else
			res = STlongint;
		break;
	case Tconst16:
		if (strlen(lex) <= 4 && lex[2] <= '7')
			res = STshortint;
		else
			res = STlongint;
		break;
	default:
		res = STempty;
	}
	return res;
}

SemantTypes SemAnalizer::convertType(SemantTypes sourtheType, SemantTypes targetType) {
	int i, j;
	for (i = 0; conversionTable[i].sourceType != STempty && sourtheType != conversionTable[i].sourceType; i++);
	for (j = 0; conversionTable[i].targetType[j] != STempty && targetType != conversionTable[i].targetType[j]; j++);
	if (conversionTable[i].sourceType == STempty) throw new Error(ConversionError, "unknown type\0");
	if (conversionTable[i].targetType[j] == STempty) throw new Error(ConversionError, "type can not be converted\0");
	if (conversionTable[i].dataLoss[j]) warnings.push(new Warning(ConversionWarning, "possible loss of data\0"));
	return conversionTable[i].targetType[j];
}
#pragma endregion

void SemAnalizer::LL1Analizer(){
	myStack->clear();
	myStack->push(Tend);
	myStack->push(NTProgramm);
	Token lex, lexNext;
	LexemeTypes tLex, tLexNext;
	tLex = scaner->scan(lex);
	while (true){
		if (isTerminal(myStack->peek())){
			if (tLex == myStack->peek()){
				if (tLex == Tend){
					myStack->pop();
					break;
				}
				else{
					tLex = scaner->scan(lex);
					myStack->pop();
				}
			}
			else{
				if (tLex == Tint)
					throw new Error(SyntaxError, "ожидается short или long");
				if (tLex == Tvoid)
					throw new Error(SyntaxError, "неверное объявление функции");
				throw new Error(SyntaxError, stringConcatenation("ожидается ", getLexByType((LexemeTypes)myStack->pop())));
			}
		}
		else{
			switch (myStack->pop())
			{
			case NTAssignment:
				myStack->push(Tsemicolon);
				myStack->push(NTExpression);
				myStack->push(Tassignment);
				myStack->push(Tid);
				break;
			case NTBlock:
				myStack->push(Tcloseblock);
				myStack->push(NTOperators);
				myStack->push(Topenblock);
				break;
			case NTCallFunction:
				myStack->push(Tsemicolon);
				myStack->push(Tclosebracket);
				myStack->push(NTCallFunctionAddition);
				myStack->push(Topenbracket);
				myStack->push(Tid);
				break;
			case NTCallFunctionAddition:
				myStack->push(NTParametr);
				break;
			case NTConstant:
				if (tLex == Tconst10)
					myStack->push(Tconst10);
				else
					myStack->push(Tconst16);
				break;
			case NTDeclarationFunction:
				myStack->push(NTBlock);
				myStack->push(Tclosebracket);
				myStack->push(NTDeclarationFunctionAddition);
				myStack->push(Topenbracket);
				myStack->push(Tid);
				myStack->push(Tvoid);
				break;
			case NTDeclarationFunctionAddition:
				if (Tshort == tLex || Tlong == tLex)
					myStack->push(NTListOfParameters);
				break;
			case NTDeclarationVariable:
				myStack->push(Tsemicolon);
				myStack->push(NTListOfVariables);
				myStack->push(NTType);
				break;
			case NTElementListOfParameters:
				myStack->push(Tid);
				myStack->push(NTType);
				break;
			case NTElementListOfVariables:
				myStack->push(NTElementListOfVariablesAddition);
				myStack->push(Tid);
				break;
			case NTElementListOfVariablesAddition:
				if (tLex == Tassignment){
					myStack->push(NTExpression);
					myStack->push(Tassignment);
				}
				break;
			case NTExpression:
				myStack->push(NTPriority1);
				break;
			case NTListOfParameters:
				myStack->push(NTListOfParametersAddition);
				myStack->push(NTElementListOfParameters);
				break;
			case NTListOfParametersAddition:
				if (tLex == Tcomma){
					myStack->push(NTListOfParametersAddition);
					myStack->push(NTElementListOfParameters);
					myStack->push(Tcomma);
				}
				break;
			case NTListOfVariables:
				myStack->push(NTListOfVariablesAddition);
				myStack->push(NTElementListOfVariables);
				break;
			case NTListOfVariablesAddition:
				if (tLex == Tcomma){
					myStack->push(NTListOfVariablesAddition);
					myStack->push(NTElementListOfVariables);
					myStack->push(Tcomma);
				}
				break;
			case NTOperator:{
								switch (tLex)
								{
								case Tid:{
											 TextPointer save = scaner->getUK();
											 tLexNext = scaner->scan(lexNext);
											 scaner->putUK(save);
											 if (tLexNext == Topenbracket){
												 myStack->push(NTCallFunction);
											 }
											 else if (tLexNext == Tassignment){
												 myStack->push(NTAssignment);
											 }
											 else {
												 myStack->push(Tsemicolon);
												 myStack->push(NTExpression);
											 }
											 break;
								}
								case Tshort:
								case Tlong:
									myStack->push(NTDeclarationVariable);
									break;
								case Twhile:
									myStack->push(NTWhile);
									break;
								case Treturn:
									myStack->push(NTReturn);
									break;
								case Tconst10:
								case Tconst16:
								case Tplus:
								case Tplusplus:
								case Tminus:
								case Tminusminus:
								case Tnot:
								case Topenbracket:
									myStack->push(Tsemicolon);
									myStack->push(NTExpression);
									break;
								case Tsemicolon:
									myStack->push(Tsemicolon);
									break;
								case Topenblock:
									myStack->push(NTBlock);
									break;
								default:
									throw new Error(SyntaxError, "ожидается оператор\0");
								}
								break;
			}
			case NTOperators:
				if (tLex == Tid ||
					tLex == Tshort ||
					tLex == Tlong ||
					tLex == Twhile ||
					tLex == Treturn ||
					tLex == Tconst10 ||
					tLex == Tconst16 ||
					tLex == Tplus ||
					tLex == Tplusplus ||
					tLex == Tminusminus ||
					tLex == Tminus ||
					tLex == Tnot ||
					tLex == Tsemicolon ||
					tLex == Topenblock ||
					tLex == Topenbracket){
					myStack->push(NTOperators);
					myStack->push(NTOperator);
				}
				break;
			case NTParametr:
				if (tLex == Tid ||
					tLex == Tconst10 ||
					tLex == Tconst16 ||
					tLex == Tplus ||
					tLex == Tplusplus ||
					tLex == Tminus ||
					tLex == Tminusminus ||
					tLex == Tnot ||
					tLex == Topenbracket){
					myStack->push(NTParametrAddition);
					myStack->push(NTExpression);
				}
				break;
			case NTParametrAddition:
				if (tLex == Tcomma){
					myStack->push(NTParametrAddition);
					myStack->push(NTParametr);
					myStack->push(Tcomma);
				}
				break;
			case NTPriority1:
				myStack->push(NTPriority1Addition);
				myStack->push(NTPriority2);
				break;
			case NTPriority1Addition:
				if (tLex == Tor){
					myStack->push(NTPriority1);
					myStack->push(Tor);
				}
				break;
			case NTPriority2:
				myStack->push(NTPriority2Addition);
				myStack->push(NTPriority3);
				break;
			case NTPriority2Addition:
				if (tLex == Tand){
					myStack->push(NTPriority2);
					myStack->push(Tand);
				}
				break;
			case NTPriority3:
				myStack->push(NTPriority3Addition);
				myStack->push(NTPriority4);
				break;
			case NTPriority3Addition:
				if (tLex == Tequal){
					myStack->push(NTPriority3);
					myStack->push(Tequal);
				}
				if (tLex == Tinequal){
					myStack->push(NTPriority3);
					myStack->push(Tinequal);
				}
				break;
			case NTPriority4:
				myStack->push(NTPriority4Addition);
				myStack->push(NTPriority5);
				break;
			case NTPriority4Addition:
				if (tLex == Tmore){
					myStack->push(NTPriority4);
					myStack->push(Tmore);
				}
				if (tLex == Tless){
					myStack->push(NTPriority4);
					myStack->push(Tless);
				}
				if (tLex == Tequalmore){
					myStack->push(NTPriority4);
					myStack->push(Tequalmore);
				}
				if (tLex == Tequalless){
					myStack->push(NTPriority4);
					myStack->push(Tequalless);
				}
				break;
			case NTPriority5:
				myStack->push(NTPriority5Addition);
				myStack->push(NTPriority6);
				break;
			case NTPriority5Addition:
				if (tLex == Tplus){
					myStack->push(NTPriority5);
					myStack->push(Tplus);
				}
				if (tLex == Tminus){
					myStack->push(NTPriority5);
					myStack->push(Tminus);
				}
				break;
			case NTPriority6:
				myStack->push(NTPriority6Addition);
				myStack->push(NTPriority7);
				break;
			case NTPriority6Addition:
				if (tLex == Tmul){
					myStack->push(NTPriority6);
					myStack->push(Tmul);
				}
				if (tLex == Tdiv){
					myStack->push(NTPriority6);
					myStack->push(Tdiv);
				}
				if (tLex == Tpercent){
					myStack->push(NTPriority6);
					myStack->push(Tpercent);
				}
				break;
			case NTPriority7:{
								 myStack->push(NTPriority8);
								 switch (tLex)
								 {
								 case Tplus:
									 myStack->push(Tplus);
									 break;
								 case Tminus:
									 myStack->push(Tminus);
									 break;
								 case Tplusplus:
									 myStack->push(Tplusplus);
									 break;
								 case Tminusminus:
									 myStack->push(Tminusminus);
									 break;
								 case Tnot:
									 myStack->push(Tnot);
									 break;
								 }
			}
				break;
			case NTPriority8:
				myStack->push(NTPriority8Addition);
				myStack->push(NTPriority9);
				break;
			case NTPriority8Addition:
				if (tLex == Tplusplus)
					myStack->push(Tplusplus);
				if (tLex == Tminusminus)
					myStack->push(Tminusminus);
				break;
			case NTPriority9:{
								 switch (tLex)
								 {
								 case Tid:
									 myStack->push(Tid);
									 break;
								 case Tconst10:
									 myStack->push(Tconst10);
									 break;
								 case Tconst16:
									 myStack->push(Tconst16);
									 break;
								 case Topenbracket:
									 myStack->push(Tclosebracket);
									 myStack->push(NTPriority1);
									 myStack->push(Topenbracket);
									 break;
								 default:
									 throw new Error(SyntaxError, "ожидается выражение");
								 }
			}
				break;
			case NTProgramm:
				if (tLex == Tvoid){
					myStack->push(NTProgramm);
					myStack->push(NTDeclarationFunction);
				}
				if (tLex == Tshort ||
					tLex == Tlong){
					myStack->push(NTProgramm);
					myStack->push(NTDeclarationVariable);
				}
				break;
			case NTReturn:
				myStack->push(Tsemicolon);
				myStack->push(Treturn);
				break;
			case NTType:
				if (tLex == Tshort){
					myStack->push(Tint);
					myStack->push(Tshort);
				}
				if (tLex == Tlong){
					myStack->push(Tint);
					myStack->push(Tlong);
				}
				break;
			case NTWhile:
				myStack->push(NTOperator);
				myStack->push(Tclosebracket);
				myStack->push(NTExpression);
				myStack->push(Topenbracket);
				myStack->push(Twhile);
				break;
			}
		}
	}
}


bool SemAnalizer::isTerminal(int value){
	if (value > 0 && value < lastLexeme || value == Terror || value == Tend)
		return true;
	else
		return false;
}

//Служебные пп
char* SemAnalizer::stringConcatenation(char* firstStr, char* secondStr){
	int len = strlen(firstStr) + strlen(secondStr) + 1;
	char* nstr = new char[len];
	for (int i = 0; i < len; i++)
		nstr[i] = '\0';
	int i = 0;
	for (; firstStr[i] != '\0'; i++)
		nstr[i] = firstStr[i];
	for (int j = 0; secondStr[j] != '\0'; i++, j++)
		nstr[i] = secondStr[j];
	return nstr;
}

char* SemAnalizer::getLexByType(LexemeTypes value){
	char* str;
	switch (value)
	{
	case Tid:
		str = "идентификатор\0";
		break;
	case Tconst10:
	case Tconst16:
		str = "константа\0";
		break;
	case Tvoid:
		str = "void\0";
		break;
	case Tshort:
		str = "short\0";
		break;
	case Tlong:
		str = "long\0";
		break;
	case Tint:
		str = "int\0";
		break;
	case Twhile:
		str = "while\0";
		break;
	case Treturn:
		str = "return\0";
		break;
	case Tassignment:
		str = "=\0";
		break;
	case Tor:
		str = "||\0";
		break;
	case Tand:
		str = "&&\0";
		break;
	case Tequal:
		str = "==\0";
		break;
	case Tinequal:
		str = "!=\0";
		break;
	case Tmore:
		str = ">\0";
		break;
	case Tless:
		str = "<\0";
		break;
	case Tequalmore:
		str = ">=\0";
		break;
	case Tequalless:
		str = "<=\0";
		break;
	case Tplus:
		str = "+\0";
		break;
	case Tminus:
		str = "-\0";
		break;
	case Tmul:
		str = "*\0";
		break;
	case Tdiv:
		str = "/\0";
		break;
	case Tpercent:
		str = "%\0";
		break;
	case Tplusplus:
		str = "++\0";
		break;
	case Tminusminus:
		str = "--\0";
		break;
	case Tnot:
		str = "!\0";
		break;
	case Tcomma:
		str = ",\0";
		break;
	case Tsemicolon:
		str = ";\0";
		break;
	case Topenblock:
		str = "{\0";
		break;
	case Tcloseblock:
		str = "}\0";
		break;
	case Topenbracket:
		str = "(\0";
		break;
	case Tclosebracket:
		str = ")\0";
		break;
	}
	return str;
}