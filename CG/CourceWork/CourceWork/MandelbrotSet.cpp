#include "MandelbrotSet.h"

const double VALUEPI = 3.14159265358979;

void sqr(Point *p) {
	double x = p->getX(),
		y = p->getY();
	p->setX(x*x - y*y);
	p->setY(2 * x*y);
}

Point* check(double x, double y, int limit) {
	int characteristic = limit;
	Point p(x, y);

	while (p.getX()*p.getX() + p.getY()*p.getY() <= 4 && characteristic) {
		sqr(&p);

		p.setX(p.getX() + x);
		p.setY(p.getY() + y);

		characteristic--;
	}

	Point* res = new Point(x, y);
	res->setCharacteristic(characteristic);
	return res;
}

Point* makeMassivePoints(double xbegin, double ybegin, double step, int w, int h, int limit) {
	Point* massive = new Point[w*h];
	double x = xbegin, y = ybegin;
	for (int i = 0; i < w; i++, x += step) {
		y = ybegin;
		for (int j = 0; j < h; j++, y += step)
			massive[i*h + j] = *check(x, y, limit);
	}
	return massive;
}

Color calculateColor(double base, double variation) {
	if ((base - 0) < 0.0000001)
		return Color(0, 0, 0);
	double red = sin(base*VALUEPI / 180)*cos(variation*VALUEPI / 180),
		greed = cos(base*VALUEPI / 180)*cos(variation*VALUEPI / 180),
		blue = cos(base*VALUEPI / 180)*sin(variation*VALUEPI / 180)>0.1? cos(base*VALUEPI / 180)*sin(variation*VALUEPI / 180): cos(base*VALUEPI / 180)*cos(variation*VALUEPI / 180);

	return Color(red<0?red*(-1):red, greed<0?greed*(-1):greed, blue);
}

double calculateHeight(double variable){
	return cos(variable*VALUEPI / 180);
}