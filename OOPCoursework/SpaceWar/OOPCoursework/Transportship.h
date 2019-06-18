#pragma once
#include "SpaceShip.h"
#include "Interfaces.h"

using namespace std;
using namespace System;
using namespace Drawing;
using namespace Interfaces;

ref class Transportship: public SpaceShip, public ICapture{
public:
	Transportship(void): SpaceShip(){//����������� �� ���������
		icon = Image::FromFile("Transportship.png");
	};
	Transportship(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility): SpaceShip(_location,_graph,_speed,_armor,_visibility){//����������� � �����������
		icon = Image::FromFile("Transportship.png");
	};
	Transportship(Transportship% copyobj): SpaceShip(copyobj){//����������� ������������
		icon = Image::FromFile("Transportship.png");
	};
	~Transportship(void){};
	virtual void capture(Rase^ var);//������ �������
	virtual void attack(Rootclass^ ship) override{retreat(ship);};//��������� �������, �������� ������� retreat
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//��������� �������
	virtual String^ ToString() override;//����������� ������ � ������
};

