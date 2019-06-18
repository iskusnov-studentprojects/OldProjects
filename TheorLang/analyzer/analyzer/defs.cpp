#include "defs.h"

keyword keywords[MAXKEYWORDS] = {
	{ "void\0", Tvoid },
	{ "short\0", Tshort },
	{ "long\0", Tlong },
	{ "int\0", Tint },
	{ "while\0", Twhile },
	{ "return\0", Treturn },
	{ "return\0", Tclass },
	{ "endoflist\0", Tend }
};

ConversionOfTypes conversionTable[CONVERSIONSNUMBER] = {
	{ STvoid, { STvoid }, { false } },
	{ STshortint, { STshortint, STlongint }, { false, false } },
	{ STlongint, { STshortint, STlongint }, { true, false } },
	{ STempty, { STempty }, { false } }
};

SemantTypes priorityType[TYPESNUMBER] = { STlongint, STshortint, STempty };