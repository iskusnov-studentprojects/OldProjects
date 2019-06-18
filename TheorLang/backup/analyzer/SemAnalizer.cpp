#include "SemAnalizer.h"


SemAnalizer::SemAnalizer(Scaner* _scaner)
{
	scaner = _scaner;
	dataTree = new DataTree();
	myStack = new MyStack<int>();
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
		case Tid: scaner->putUK(pointer);
			variableDeclaration();
			break;
		case Tclass:
			classDeclaration();
			break;
		default: throw new Error(SyntaxError, "ожидается declaration\0");
		}
	} while (type != Tend);

}

void SemAnalizer::variableDeclaration(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node* type;
	Token lex, typeLex;

	//Определим тип переменных
	pointer = scaner->getUK();
	ltype = scaner->scan(typeLex);
	if (ltype == Tshort || ltype == Tlong) {
		if(scaner->scan(lex) != Tint)
			throw new Error(SyntaxError, "ожидается int\0");
		strcat(typeLex, " ");
		strcat(typeLex, lex);
	}
	if (ltype == Tid) {
		scaner->putUK(pointer);
		idHandler();
		type = dataTree->getCurrent();
	}else type = typeAnalis(typeLex, ltype);

	//Анализируем список переменных
	do{
		if (scaner->scan(lex) != Tid)
			throw new Error(SyntaxError, "ожидается id\0");
		//Добавить идентификатор в дерево
		dataTree->addNode(lex, type);
		ltype = scaner->scan(lex);
		if (ltype == Tsemicolon) break;
		if (ltype == Tcomma) continue;
		if (ltype != Tassignment)
			throw new Error(SyntaxError, "ожидается =\0");
		Node* type2 = expression();
		convertType(type2, type);
		ltype = scaner->scan(lex);
		if (ltype != Tcomma && ltype != Tsemicolon)
			throw new Error(SyntaxError, "ожидается ;\0");
	} while (ltype != Tsemicolon);
}

void SemAnalizer::functionDeclaration(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node* type;
	Node* fn;
	Token lex;
	Token lex2;
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
		if (ltype == Tshort || ltype == Tlong) {
			if (scaner->scan(lex2) != Tint)
				throw new Error(SyntaxError, "ожидается int\0");
			strcat(lex, " ");
			strcat(lex, lex2);
		}
		if (ltype == Tid) {
			scaner->putUK(pointer);
			idHandler();
			if(dataTree->getCurrent()->semType != STclass)
				throw new Error(SyntaxError, "ожидается ожидается type\0");
			type = dataTree->getCurrent();
		}else type = typeAnalis(lex, ltype);

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
}

void SemAnalizer::classDeclaration() {
	TextPointer pointer;
	LexemeTypes ltype;
	Token lex;
	if(scaner->scan(lex) != STclass)
		throw new Error(SyntaxError, "ожидается class\0");
	dataTree->addNode(lex, dataTree->findUp(lex));
	dataTree->openBranch();
	ltype = scaner->scan(lex);
	if (ltype == Tdotdot) {
		ltype = scaner->scan(lex);
		if(ltype != Tid)
			throw new Error(SyntaxError, "ожидается id\0");
		Node* cl = dataTree->findUp(lex);
		if (cl->semType != STclass)
			throw new Error(SyntaxError, stringConcatenation(lex, " не является именем класса\0"));
		dataTree->copyFromCurrentToLast();
	}
	if(ltype != Topenblock)
		throw new Error(SyntaxError, "ожидается {\0");
	do {
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
		switch (ltype)
		{
		case Tcloseblock: break;
		case Tvoid: scaner->putUK(pointer);
			functionDeclaration();
			break;
		case Tshort:
		case Tlong:
		case Tid: scaner->putUK(pointer);
			variableDeclaration();
			break;
		case Tclass:
			classDeclaration();
			break;
		default: throw new Error(SyntaxError, "ожидается declaration\0");
		}
	} while (ltype != Tcloseblock);
	dataTree->closeBranch();
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
	Node *type, *type2;
	Token lex, lex2;
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
		if (scaner->scan(lex) != Topenbracket)
			throw new Error(SyntaxError, "ожидается (\0");
		expression();
		if (scaner->scan(lex) != Tclosebracket)
			throw new Error(SyntaxError, "ожидается )\0");
		oneOperator();
		break;
	case Tid:
		scaner->putUK(pointer);
		//Обработать id
		idHandler();
		type = dataTree->getCurrent();
		
		ltype2 = scaner->scan(lex2);
		//Функция
		if (ltype2 == Topenbracket) {
			scaner->putUK(pointer);
			callFunction();
			break;
		}

		//Присваивание
		if (ltype2 == Tassignment) {
			type2 = expression();
			convertType(type2, type);
			if (scaner->scan(lex) != Tsemicolon)
				throw new Error(SyntaxError, "ожидается ;\0");
			break;
		}
		scaner->putUK(pointer);

		//Если id это класс
		if (type->semType == STclass)
			variableDeclaration();
		else {//В других случаях выражение
			expression();
			if (scaner->scan(lex) != Tsemicolon)
				throw new Error(SyntaxError, "ожидается ;\0");
		}
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
	SemantTypes stype;
	Token lex;
	Node* cur;

	idHandler();
	cur = dataTree->getCurrent();
	if (dataTree->getCurrent() == NULL)
		throw new Error(DeclarationError, "функция не объявлена\0");
	cur = cur->right->left;
	if (scaner->scan(lex) != Topenbracket)
		throw new Error(SyntaxError, "ожидается (\0");
	do {
		Node* type = expression();
		if (convertType(type, cur) != NULL)
			throw new Error(SemantError, "не верный список параметров\0");
		ltype = scaner->scan(lex);
		if (ltype != Tclosebracket && ltype != Tcomma)
			throw new Error(SyntaxError, "ожидается )\0");
	} while (ltype != Tclosebracket);
	if(cur != NULL)
		throw new Error(SemantError, "не верный список параметров\0");
	if (scaner->scan(lex) != Tsemicolon)
		throw new Error(SyntaxError, "ожидается ;\0");
}

Node* SemAnalizer::expression(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node *type, *type2;
	Token lex;
	type = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tor){
		type2 = expression2();
		type = convertType(type, type2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

Node* SemAnalizer::expression2(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node *type, *type2;
	Token lex;
	type = expression3();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tand){
		type2 = expression3();
		type = convertType(type, type2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

Node* SemAnalizer::expression3(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node *type, *type2;
	Token lex;
	type = expression4();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tequal || ltype == Tinequal){
		type2 = expression4();
		type = convertType(type, type2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

Node* SemAnalizer::expression4(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node *type, *type2;
	Token lex;
	type = expression5();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tmore || ltype == Tless || ltype == Tequalmore || ltype == Tequalless){
		type2 = expression5();
		type = convertType(type, type2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

Node* SemAnalizer::expression5(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node *type, *type2;
	Token lex;
	type = expression6();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tplus || ltype == Tminus){
		type2 = expression6();
		type = convertType(type, type2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

Node* SemAnalizer::expression6(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node *type, *type2;
	Token lex;
	type = expression7();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tmul || ltype == Tdiv || ltype == Tpercent){
		type2 = expression7();
		type = convertType(type, type2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return type;
}

Node* SemAnalizer::expression7(){
	TextPointer pointer;
	LexemeTypes ltype;
	Token lex;
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	if (!(ltype == Tnot || ltype == Tminus || ltype == Tplus))
		scaner->putUK(pointer);
	return expression8();
}

Node* SemAnalizer::expression8(){
	TextPointer pointer;
	LexemeTypes ltype;
	Node* type;
	Token lex;
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	switch (ltype)
	{
	case Tconst10:
	case Tconst16:
		type = typeAnalis(lex, ltype);
		break;
	case Tminusminus:
	case Tplusplus:
		pointer = scaner->getUK();
		if ((ltype = scaner->scan(lex)) != Tid)
			throw new Error(SyntaxError, "ожидается id\0");
		scaner->putUK(pointer);
	case Tid:
		scaner->putUK(pointer);
		idHandler();
		type = dataTree->getCurrent();
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
		if (!(ltype == Tplusplus || ltype == Tminusminus)){
			scaner->putUK(pointer);
		}
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

void SemAnalizer::idHandler() {
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex;

	if(scaner->scan(lex) != Tid)
		throw new Error(SyntaxError, "ожидается id");
	dataTree->findUp(lex);
	pointer = scaner->getUK();
	while(scaner->scan(lex) == Tdot){
		if(scaner->scan(lex) != Tid)
			throw new Error(SyntaxError, "ожидается id");
		dataTree->findDown(lex);
		pointer = scaner->getUK();
	}
	scaner->putUK(pointer);
}
#pragma endregion

#pragma region Семантические подпрограммы
Node* SemAnalizer::typeAnalis(Token lex, LexemeTypes type){
	Node* res;
	switch (type)
	{
	case Tid:
		res = dataTree->findUp(lex);
		break;
	case Tconst10:
		if (strlen(lex) <= 5 && lex[0] <= '3')
			res = dataTree->findUp("short int");
		else
			res = dataTree->findUp("long int");
		break;
	case Tconst16:
		if (strlen(lex) <= 4 && lex[2] <= '7')
			res = dataTree->findUp("short int");
		else
			res = dataTree->findUp("long int");
		break;
	default:
		res = NULL;
	}
	return res;
}

Node* SemAnalizer::convertType(Node* sourtheType, Node* targetType){
	if (sourtheType->semType == STclass || targetType->semType == STclass || 
		sourtheType->semType == STobject || targetType->semType == STobject)
		return NULL;
	int i, j;
	for (i = 0; conversionTable[i].sourceType != STempty && sourtheType->semType != conversionTable[i].sourceType; i++);
	for (j = 0; conversionTable[i].targetType[j] != STempty && targetType->semType != conversionTable[i].targetType[j]; j++);
	if (conversionTable[i].sourceType == STempty) throw new Error(ConversionError, "unknown type\0");
	if (conversionTable[i].targetType[j] == STempty) throw new Error(ConversionError, "type can not be converted\0");
	if (conversionTable[i].dataLoss[j]) warnings.push(new Warning(ConversionWarning, "possible loss of data\0"));
	return NULL;
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