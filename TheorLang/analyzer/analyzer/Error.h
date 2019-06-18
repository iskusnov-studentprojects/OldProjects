#pragma once
#include "defs.h"

class Error{
public:
	int type;
	char *inf;
	int line;
	int pos;
	Error(ErrorTypes _type, char *_inf){
		type = _type;
		inf = _inf;
	}
	~Error();
};