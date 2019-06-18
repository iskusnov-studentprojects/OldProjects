#pragma once

#include "targetver.h"

#include <stdio.h>
#include <tchar.h>
#include <fstream>

using namespace std;

#define MAXTEXTLEN 100000
#define MAXTOKENLEN 100
#define MAXKEYWORDS 10
#define MAXERRORLEN 20
#define CONVERSIONSNUMBER 10
#define TYPESNUMBER 5

typedef char Token[MAXTOKENLEN];
typedef char Modul[MAXTEXTLEN];

enum LexemeTypes{
	//Идентификатор
	Tid = 1,
	//Константы
	Tconst10 = 21,
	Tconst16 = 22,
	//Ключевые слова
	Tvoid = 31,
	Tshort = 32,
	Tlong = 33,
	Tint = 34,
	Twhile = 35,
	Treturn = 36,
	Tclass = 37,
	//Операции
	Tassignment = 41,
	Tor = 42,
	Tand = 43,
	Tequal = 44,
	Tinequal = 45,
	Tmore = 46,
	Tless = 47,
	Tequalmore = 48,
	Tequalless = 49,
	Tplus = 410,
	Tminus = 411,
	Tmul = 412,
	Tdiv = 413,
	Tpercent = 414,
	Tplusplus = 415,
	Tminusminus = 416,
	Tnot = 417,
	Tdot = 418,
	Tdotdot = 419,
	//Разделители
	Tcomma = 51,
	Tsemicolon = 52,
	//Скобки
	Topenblock = 61,
	Tcloseblock = 62,
	Topenbracket = 63,
	Tclosebracket = 64,
	//Конец исходного модуля
	Tend = 1001,
	//Ошибка
	Terror = 1002,
	lastLexeme = 10000
};

enum NonTerminals {
	NTProgramm = lastLexeme,
	NTDeclarationVariable,
	NTDeclarationFunction,
	NTDeclarationFunctionAddition,
	NTType,
	NTListOfVariables,
	NTListOfVariablesAddition,
	NTElementListOfVariables,
	NTElementListOfVariablesAddition,
	NTConstant,
	NTListOfParameters,
	NTListOfParametersAddition,
	NTElementListOfParameters,
	NTBlock,
	NTOperators,
	NTOperator,
	NTWhile,
	NTReturn,
	NTAssignment,
	NTCallFunction,
	NTCallFunctionAddition,
	NTExpression,
	NTParametr,
	NTParametrAddition,
	NTPriority1,
	NTPriority1Addition,
	NTPriority2,
	NTPriority2Addition,
	NTPriority3,
	NTPriority3Addition,
	NTPriority4,
	NTPriority4Addition,
	NTPriority5,
	NTPriority5Addition,
	NTPriority6,
	NTPriority6Addition,
	NTPriority7,
	NTPriority8,
	NTPriority8Addition,
	NTPriority9,
	lastNT
};

enum SemantProc {
	SPAddNode = lastNT + 1,
	SPTypeAnalis,
	SPFindUp,
	SPConvertType,
	SPEndWhile
};

enum TriadType {
	FUN,
	LINK,
	WHILE,
	JMP,
	NONE,
	DATA
};

enum SemantTypes{
	STempty,
	STshortint,
	STlongint,
	STvoid
};

struct keyword{
	Token name;
	LexemeTypes code;
};
extern keyword keywords[MAXKEYWORDS];

struct TextPointer{
	int uk;
	int pos;
	int line;
};

enum ErrorTypes{
	InvalidCharacter = 0,
	ErrorToken = Terror,
	LenthOfConstant = 1,
	SemantError = 2,
	ConversionError = 3,
	DeclarationError = 4,
	SyntaxError = 6,
	OtherError = 7,
};

enum WarningTypes
{
	ConversionWarning = 0
};

#pragma region ErrorsText
#define INVALIDCHARACTERMESS "Invalid Character: "
#define ERRORTOKENMESS "Error in token: "
#pragma endregion

struct ConversionOfTypes{
	SemantTypes sourceType;
	SemantTypes targetType[TYPESNUMBER];
	bool dataLoss[TYPESNUMBER];
};

extern ConversionOfTypes conversionTable[CONVERSIONSNUMBER];

extern SemantTypes priorityType[TYPESNUMBER];
