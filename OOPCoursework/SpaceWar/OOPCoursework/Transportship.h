#pragma once
#include "SpaceShip.h"
#include "Interfaces.h"

using namespace std;
using namespace System;
using namespace Drawing;
using namespace Interfaces;

ref class Transportship: public SpaceShip, public ICapture{
public:
	Transportship(void): SpaceShip(){//Конструктор по умолчанию
		icon = Image::FromFile("Transportship.png");
	};
	Transportship(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility): SpaceShip(_location,_graph,_speed,_armor,_visibility){//Конструктор с параметрами
		icon = Image::FromFile("Transportship.png");
	};
	Transportship(Transportship% copyobj): SpaceShip(copyobj){//Конструктор копированиия
		icon = Image::FromFile("Transportship.png");
	};
	~Transportship(void){};
	virtual void capture(Rase^ var);//Захват планеты
	virtual void attack(Rootclass^ ship) override{retreat(ship);};//Фиктивная функция, вызывает функцию retreat
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//Отрисовка объекта
	virtual String^ ToString() override;//Преобразует объект в строку
};

