#include "SemAnalizer.h"


SemAnalizer::SemAnalizer(Scaner* _scaner)
{
	scaner = _scaner;
}


SemAnalizer::~SemAnalizer()
{
}

void SemAnalizer::start(){
	TextPointer pointer;
	LexemeTypes type;
	Token lex;
	try{
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
			case Tlong: scaner->putUK(pointer);
				variableDeclaration();
				break;
			default: throw new Error(SemantError, "expected declaration");
			}
		} while (type != Tend);
	}
	catch (Error* er){
		er->line = scaner->getUK().line;
		er->pos = scaner->getUK().pos;
		throw er;
	}
}

void SemAnalizer::variableDeclaration(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex;
	if ((ltype = scaner->scan(lex)) != Tshort || ltype != Tlong)
		throw new Error(SyntaxError, "expected type");
	stype = typeAnalis(ltype);
	if (scaner->scan(lex) != Tint)
		throw Error(SyntaxError, "expected int");
	do{
		if (scaner->scan(lex) != Tid)
			throw new Error(SyntaxError, "expected id");
		//Добавить идентификатор в дерево
		ltype = scaner->scan(lex);
		if (ltype == Tsemicolon) break;
		if (ltype != Tassignment)
			throw new Error(SyntaxError, "expected =");
		SemantTypes stype2 = expression();
		compareTypes(stype, stype2);
		ltype = scaner->scan(lex);
		if (ltype != Tcomma && ltype != Tsemicolon)
			throw new Error(SyntaxError, "expected ;");
	} while (ltype != Tsemicolon);
}

void SemAnalizer::functionDeclaration(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex;
	if ((ltype = scaner->scan(lex)) != Tvoid)
		throw new Error(SyntaxError, "expected type");
	stype = typeAnalis(ltype);
	if (scaner->scan(lex) != Tid)
		throw new Error(SyntaxError, "expected id");
	//Добавить идентификатор в дерево
	if (scaner->scan(lex) != Topenbracket)
		throw new Error(SyntaxError, "expected (");
	do 
	{
		ltype = scaner->scan(lex);
		if (ltype == Tclosebracket) break;
		if (ltype != Tshort || ltype != Tlong)
			throw new Error(SyntaxError, "expected expected type");
		stype = typeAnalis(ltype);
		if (scaner->scan(lex) != Tint)
			throw new Error(SyntaxError, "expected int");
		if (scaner->scan(lex) != Tid)
			throw new Error(SyntaxError, "expected id");
		//Добавить идентификатор
		ltype = scaner->scan(lex);
		if (ltype != Tcomma && ltype != Tclosebracket)
			throw new Error(SyntaxError, "expected )");
	} while (ltype != Tclosebracket);
	block();
}

void SemAnalizer::block(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex;
	if (scaner->scan(lex)!=Topenblock)
		throw new Error(SyntaxError, "expected {");
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype != Tcloseblock)
	{
		scaner->putUK(pointer);
		oneOperator();
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
}

void SemAnalizer::oneOperator(){
	TextPointer pointer;
	LexemeTypes ltype, ltype2;
	SemantTypes stype;
	Token lex;
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
			throw new Error(SyntaxError, "expected (");
		expression();
		if (scaner->scan(lex) != Tclosebracket)
			throw new Error(SyntaxError, "expected )");
		oneOperator();
		break;
	case Tid:
		 ltype2 = scaner->scan(lex);
		 if (ltype2 == Topenbracket){
			 scaner->putUK(pointer);
			 callFunction();
			 break;
		 }
		 if (ltype2 == Tassignment){
			 expression();
			 if (scaner->scan(lex) != Tsemicolon)
				 throw new Error(SyntaxError, "expected ;");
			 break;
		 }
		 scaner->putUK(pointer);
		 expression();
		 if (scaner->scan(lex) != Tsemicolon)
			 throw new Error(SyntaxError, "expected ;");
		 break;
	case Treturn:
		if (scaner->scan(lex) != Tsemicolon)
			throw new Error(SyntaxError, "expected ;");
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
			throw new Error(SyntaxError, "expected ;");
		break;
	default:
		throw new Error(SyntaxError, "expected operator");
	}
}

void SemAnalizer::callFunction(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex;
	ltype = scaner->scan(lex);
	if (ltype != Tid)
		throw new Error(SyntaxError, "expected id");
	//Найти позицию функции в дереве
	if (scaner->scan(lex) != Topenbracket)
		throw new Error(SyntaxError, "expected (");
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	if (ltype != Tclosebracket){
		scaner->putUK(pointer);
		do{
			stype = expression();
			//Проверить параметр функции
			ltype = scaner->scan(lex);
			if (ltype != Tcomma && ltype != Tclosebracket)
				throw new Error(SyntaxError, "expected )");
		} while (ltype != Tclosebracket);
	}
	if (scaner->scan(lex) != Tsemicolon)
		throw new Error(SyntaxError, "expected ;");
}

SemantTypes SemAnalizer::expression(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype, stype2;
	Token lex;
	stype = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tor){
		stype = typeAnalis(ltype);
		stype2 = expression2();
		stype = compareTypes(stype, stype2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return stype;
}

SemantTypes SemAnalizer::expression2(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype, stype2;
	Token lex;
	stype = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tand){
		stype2 = expression2();
		stype = compareTypes(stype, stype2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return stype;
}

SemantTypes SemAnalizer::expression3(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype, stype2;
	Token lex;
	stype = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tequal || ltype == Tinequal){
		stype = typeAnalis(ltype);
		stype2 = expression2();
		stype = compareTypes(stype, stype2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return stype;
}

SemantTypes SemAnalizer::expression4(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype, stype2;
	Token lex;
	stype = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tmore || ltype == Tless || ltype == Tequalmore || ltype == Tequalless){
		stype = typeAnalis(ltype);
		stype2 = expression2();
		stype = compareTypes(stype, stype2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return stype;
}

SemantTypes SemAnalizer::expression5(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype, stype2;
	Token lex;
	stype = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tplus || ltype == Tminus){
		stype = typeAnalis(ltype);
		stype2 = expression2();
		stype = compareTypes(stype, stype2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return stype;
}

SemantTypes SemAnalizer::expression6(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype, stype2;
	Token lex;
	stype = expression2();
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	while (ltype == Tmul || ltype == Tdiv || ltype == Tpercent){
		stype = typeAnalis(ltype);
		stype2 = expression2();
		stype = compareTypes(stype, stype2);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
	}
	scaner->putUK(pointer);
	return stype;
}

SemantTypes SemAnalizer::expression7(){
	TextPointer pointer;
	LexemeTypes ltype;
	Token lex;
	pointer = scaner->getUK();
	ltype = scaner->scan(lex);
	if (ltype != Tplus && ltype != Tminus && ltype != Tnot)
		scaner->putUK(pointer);
	return expression8();
}

SemantTypes SemAnalizer::expression8(){
	TextPointer pointer;
	LexemeTypes ltype;
	SemantTypes stype;
	Token lex;
	ltype = scaner->scan(lex);
	switch (ltype)
	{
	case Tconst10:
	case Tconst16:
		stype = typeAnalis(lex, ltype);
		break;
	case Tplusplus:
	case Tminusminus:
		ltype = scaner->scan(lex);
		if (ltype != Tid)
			throw Error(SemantError, "expected id");
	case Tid:
		stype = typeAnalis(lex, ltype);
		pointer = scaner->getUK();
		ltype = scaner->scan(lex);
		if (ltype != Tplusplus && ltype != Tminusminus)
			scaner->putUK(pointer);
		break;
	case Topenbracket:
		stype = expression();
		if (scaner->scan(lex) != Tclosebracket)
			throw new Error(SemantError, "expected )");
		break;
	default:
		throw new Error(SemantError, "expected expression");
	}
	return stype;
}