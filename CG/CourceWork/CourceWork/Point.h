#pragma once
class Point
{
private:
	double characteristic;
	double x;
	double y;
public:
	Point();
	Point(double x, double y);
	~Point();
	double getX();
	void setX(double x);
	double getY();
	void setY(double y);
	double getCharacteristic();
	void setCharacteristic(double characteristic);
};

