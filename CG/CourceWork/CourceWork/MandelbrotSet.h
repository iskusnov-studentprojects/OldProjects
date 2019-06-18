#pragma once

#include "Point.h";
#include "Color.h"
#include <math.h>

void sqr(Point *p);

Point* check(double x, double y, int limit);

Point* makeMassivePoints(double xbegin, double ybegin, double step, int w, int h, int limit);

Color calculateColor(double base, double variation);