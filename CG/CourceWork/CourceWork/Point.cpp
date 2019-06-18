#pragma once

#include "Point.h"



Point::Point() {
	x = 0;
	y = 0;
}

Point::Point(double x, double y){
	setX(x);
	setY(y);
}


Point::~Point()
{
}

double Point::getX() {
	return x;
}

void Point::setX(double x) {
	this->x = x;
}

double Point::getY() {
	return y;
}

void Point::setY(double y) {
	this->y = y;
}

double Point::getCharacteristic() {
	return characteristic;
}

void Point::setCharacteristic(double characteristic)
{
	this->characteristic = characteristic;
}
