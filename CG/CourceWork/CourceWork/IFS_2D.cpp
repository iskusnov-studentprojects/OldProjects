#pragma once
#include "IFS_2D.h"
#include <random>

using namespace std;

double epsilon = 0.0001;
IF_2D function1;
IF_2D function2;
IF_2D function3;
IF_2D function4;
Point2D* points;

Point2D* start(IF_2D fun1, IF_2D fun2, IF_2D fun3, IF_2D fun4, Point2D base, int iterations) {
	function1 = fun1;
	function2 = fun2;
	function3 = fun3;
	function4 = fun4;
	double c1 = fun2.getChance() + fun1.getChance(),
		c2 = fun3.getChance() + fun2.getChance() + fun1.getChance(),
		c3 = fun4.getChance() + fun3.getChance() + fun2.getChance() + fun1.getChance();
	function2.setChance(c1);
	function3.setChance(c2);
	function4.setChance(c3);
	points = new Point2D[iterations];
	points[0] = findBeginPoint(base);
	Point2D point = points[0],
		nPoint;
	for (int i = 0; i < iterations; i++) {
		int value = rand()%100;
		if (value <= function1.getChance() * 100) {
			nPoint = function1.calculate(point);
			points[i] = nPoint;
			point = nPoint;
			continue;
		}
		if (value <= function2.getChance() * 100) {
			nPoint = function2.calculate(point);
			points[i] = nPoint;
			point = nPoint;
			continue;
		}
		if (value <= function3.getChance() * 100) {
			nPoint = function3.calculate(point);
			points[i] = nPoint;
			point = nPoint;
			continue;
		}
		if (value <= function4.getChance() * 100) {
			nPoint = function4.calculate(point);
			points[i] = nPoint;
			point = nPoint;
			continue;
		}
	}
	return points;
}

Point2D findBeginPoint(Point2D point) {
	Point2D nPoint = point;
	for (int i = 0; i < 100; i++, point = nPoint) {
		int value = rand() % 100;
		if (value <= function1.getChance()*100)
			nPoint = function1.calculate(point);
		if (value <= function2.getChance()*100)
			nPoint = function2.calculate(point);
		if (value <= function3.getChance()*100)
			nPoint = function3.calculate(point);
		if (value <= function4.getChance()*100)
			nPoint = function4.calculate(point);
	}
	return nPoint;
}

double findMinX(Point2D* points, int length) {
	double min = 1000000;
	for (int i = 0; i < length; i++)
		if (min > points[i].getX())
			min = points[i].getX();
	return min;
}

double findMinY(Point2D* points, int length) {
	double min = 1000000;
	for (int i = 0; i < length; i++)
		if (min > points[i].getY())
			min = points[i].getY();
	return min;
}

double findMaxX(Point2D* points, int length) {
	double max = -1000000;
	for (int i = 0; i < length; i++)
		if (max < points[i].getX())
			max = points[i].getX();
	return max;
}

double findMaxY(Point2D* points, int length) {
	double max = -1000000;
	for (int i = 0; i < length; i++)
		if (max < points[i].getY())
			max = points[i].getY();
	return max;
}