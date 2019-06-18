#pragma once
#include "defs.h"

class Warning
{
	int line;
	int pos;
	char* inf;
	WarningTypes type;
public:
	Warning(WarningTypes _type, char* _inf){
		type = _type;
		inf = _inf;
	};
	~Warning(){};
};