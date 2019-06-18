#include "Scaner.h"

Scaner::Scaner(char *path)
{
	for (int i = 0; i < MAXTEXTLEN; i++)
		text[i] = 0;
	int len;
	ifstream file;
	file.open("input.c");
	file.seekg(0, ios::end);
	len = file.tellg();
	file.seekg(0, ios::beg);
	file.read(text, len < MAXTEXTLEN? len: MAXTEXTLEN);
	file.close();
	uk = 0;
	line = 1;
	pos = 1;
}

Scaner::~Scaner()
{
}

LexemeTypes Scaner::scan(Token token){
	int len;
	for (len = 0; len < MAXTOKENLEN; len++) token[len] = 0;
	len = 0;
start:
	if (text[uk] == 0) return Tend;
	//Пропуск незначимых символов
	while (text[uk] == ' ' || text[uk] == '\n' || text[uk] == '\t'){
		if (text[uk] == '\n') newLine();
		else if (text[uk] == '\t') pos += 4;
		else pos++;
		uk++;
		goto start;
	};
	//Пропуск многострочного комментария
	if (text[uk] == '/' && text[uk + 1] == '*'){
		uk += 2; pos += 2;
		while (true)
		{
			if (text[uk] == '*' && text[uk+1] == '/'){
				uk += 2; pos += 2;
				goto start;
			}
			if (text[uk] == '\0') goto start;
			if (text[uk] == '\n') newLine();
			else if (text[uk] == '\t') pos += 4;
			else pos++;
			uk++;
		}
		goto start;
	}
	//Пропуск однострочного комментария
	if (text[uk] == '/' && text[uk + 1] == '/'){
		uk += 2; pos += 2;
		while (true)
		{
			if (text[uk] == '\n'){ newLine(); uk++; goto start; }
			if (text[uk] == '\0') goto start;
			if (text[uk] == '\t') pos += 4;
			else pos++;
			uk++;
		}
		goto start;
	}
	//Скан идентификатора
	if (text[uk] >= 'a' && text[uk] <= 'z' ||
		text[uk] >= 'A' &&  text[uk] <= 'Z'){
		while (text[uk] >= 'a' && text[uk] <= 'z' ||
			text[uk] >= 'A' &&  text[uk] <= 'Z' ||
			text[uk] >= '0' && text[uk] <= '9' ||
			text[uk] == '_') if (len < MAXTOKENLEN - 1) { token[len++] = text[uk++]; pos++; }
			else { uk++; pos++; };
		int i = 0;
		while (keywords[i].code != Tend){ if (strcmp(keywords[i].name, token) == 0) return keywords[i].code; i++; }
		return Tid;
	}
	//Десятичная константа
	if (text[uk] >= '1' && text[uk] <= '9'){
	number: while (text[uk] >= '0' && text[uk] <= '9') { 
		if (len < MAXTOKENLEN - 1) { token[len++] = text[uk++]; pos++; }
		else { uk++; pos++; }
	}
		if (len >= 9) throw new Error(LenthOfConstant, token);
		return Tconst10;
	}
	switch (text[uk]){
	case '0': //Шестнадцатиричная константа
		token[len++] = text[uk++]; pos++;
		if (text[uk] >= '0' && text[uk] <= '9') goto number;
		if (text[uk] == 'x' || text[uk] == 'X'){
			{ token[len++] = text[uk++]; pos++; }
			while (text[uk] >= '0' && text[uk] <= '9' ||
				text[uk] >= 'A' && text[uk] <= 'F') {
				if (len < MAXTOKENLEN - 1) { token[len++] = text[uk++]; pos++; }
				else { uk++; pos++; }
			}
			if (len >= 9) throw new Error(LenthOfConstant, token);
			return Tconst16;
		}
		return Tconst10;
	case '=': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '='){ token[len++] = text[uk++]; pos++; return Tequal; }
		else return Tassignment;
	case '|': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '|') { token[len++] = text[uk++]; pos++; return Tor; }
		else throw new Error(ErrorToken, token);
	case '&': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '&') { token[len++] = text[uk++]; pos++; return Tand; }
		else throw new Error(ErrorToken, token);
	case '!': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '=') { token[len++] = text[uk++]; pos++; return Tinequal; }
		else return Tnot;
	case '+': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '+') { token[len++] = text[uk++]; pos++; return Tplusplus; }
		else return Tplus;
	case '-': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '-') { token[len++] = text[uk++]; pos++; return Tminusminus; }
		else return Tminus;
	case  '>': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '=') { token[len++] = text[uk++]; pos++; return Tequalmore; }
		else return Tmore;
	case '<': { token[len++] = text[uk++]; pos++; }
		if (text[uk] == '=') { token[len++] = text[uk++]; pos++; return Tequalless; }
		else return Tless;
	case '/': { token[len++] = text[uk++]; pos++; } return Tdiv;
	case '*': { token[len++] = text[uk++]; pos++; } return Tmul;
	case '%': { token[len++] = text[uk++]; pos++; } return Tpercent;
	case ',': { token[len++] = text[uk++]; pos++; } return Tcomma;
	case ';': { token[len++] = text[uk++]; pos++; } return Tsemicolon;
	case '{': { token[len++] = text[uk++]; pos++; } return Topenblock;
	case '}': { token[len++] = text[uk++]; pos++; } return Tcloseblock;
	case '(': { token[len++] = text[uk++]; pos++; } return Topenbracket;
	case ')': { token[len++] = text[uk++]; pos++; } return Tclosebracket;
	case '.': { token[len++] = text[uk++]; pos++; } return Tdot;
	case ':': { token[len++] = text[uk++]; pos++; } return Tdotdot;
	default: { token[len++] = text[uk++]; pos++; } throw new Error(InvalidCharacter, token);
	}
}

void Scaner::newLine(){
	pos = 1;
	line++;
}

void Scaner::putUK(TextPointer pointer){
	line = pointer.line;
	pos = pointer.pos;
	uk = pointer.uk;
}

TextPointer Scaner::getUK(){
	TextPointer pointer;
	pointer.line = line;
	pointer.pos = pos;
	pointer.uk = uk;
	return pointer;
}