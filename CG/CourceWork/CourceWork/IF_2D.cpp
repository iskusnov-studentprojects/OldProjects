#include "IF_2D.h"

Point2D::Point2D() {
	setX(0);
	setY(0);
}

Point2D::Point2D(double x, double y) {
	setX(x);
	setY(y);
}

double Point2D::getX() {
	return x;
}
double Point2D::getY() {
	return y;
}
void Point2D::setX(double x) {
	this->x = x;
}
void Point2D::setY(double y) {
	this->y = y;
}

IF_2D::IF_2D(double aX, double bX, double dX, double aY, double bY, double dY, double chance) {
	setaX(aX);
	setbX(bX);
	setdX(dX);
	setaY(aY);
	setbY(bY);
	setdY(dY);
	setChance(chance);
}
IF_2D::IF_2D() {
	setaX(0);
	setbX(0);
	setdX(0);
	setaY(0);
	setbY(0);
	setdY(0);
	setChance(0);
}
IF_2D::~IF_2D() {

}
double IF_2D::getaX() {
	return aX;
}

void IF_2D::setaX(double aX) {
	this->aX = aX;
}

double IF_2D::getbX() {
	return bX;
}

void IF_2D::setbX(double bX) {
	this->bX = bX;
}

double IF_2D::getdX() {
	return dX;
}

void IF_2D::setdX(double dX) {
	this->dX = dX;
}

double IF_2D::getaY() {
	return aY;
}

void IF_2D::setaY(double aY) {
	this->aY = aY;
}

double IF_2D::getbY() {
	return bY;
}

void IF_2D::setbY(double bY) {
	this->bY = bY;
}

double IF_2D::getdY() {
	return dY;
}

void IF_2D::setdY(double dY) {
	this->dY = dY;
}

double IF_2D::getChance() {
	return chance;
}

void IF_2D::setChance(double chance) {
	this->chance = chance;
}

Point2D IF_2D::calculate(Point2D point) {
	return Point2D(getaX()*point.getX() + getbX()*point.getY() + getdX(), getaY()*point.getX() + getbY()*point.getY() + getdY());
}
