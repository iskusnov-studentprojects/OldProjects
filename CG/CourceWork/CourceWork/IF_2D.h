#pragma once
struct Point2D{
private:
	double x;
	double y;
public:
	Point2D();

	Point2D(double x, double y);

	double getX();
	double getY();
	void setX(double x);
	void setY(double y);
};

class IF_2D
{
private:
	double aX;
	double bX;
	double dX;
	double aY;
	double bY;
	double dY;
	double chance;
public:
	IF_2D(double aX, double bX, double dX, double aY, double bY, double dY, double chance);
	IF_2D();
	~IF_2D();
	double getaX();
	void setaX(double aX);
	double getbX();
	void setbX(double bX);
	double getdX();
	void setdX(double dX);
	double getaY();
	void setaY(double aY);
	double getbY();
	void setbY(double bY);
	double getdY();
	void setdY(double dY);
	double getChance();
	void setChance(double chance);
	Point2D calculate(Point2D point);
};

