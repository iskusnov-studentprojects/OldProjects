#pragma once
#include "IF_2D.h";
Point2D* start(IF_2D fun1, IF_2D fun2, IF_2D fun3, IF_2D fun4, Point2D base, int iterations);
Point2D findBeginPoint(Point2D point);
double findMaxY(Point2D* points, int length);
double findMaxX(Point2D* points, int length);
double findMinY(Point2D* points, int length);
double findMinX(Point2D* points, int length);