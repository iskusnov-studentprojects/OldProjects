#pragma once
#include "warship.h"
#include "Interfaces.h"

using namespace std;
using namespace System;
using namespace Drawing;
//using namespace Interfaces;

ref class Regenship: public Warship, public IRegeneration{
public:
	Regenship(void): Warship(){//Конструктор по умолчанию
		icon = Image::FromFile("RegenshipPic.png");
	};
	Regenship(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility,const Int32 _firespeed): Warship(_location,_graph,_speed,_armor,_visibility,_firespeed){//Конструктор с параметрами
		icon = Image::FromFile("RegenshipPic.png");
	};
	Regenship(Regenship% copyobj): Warship(copyobj){//Конструктор копирования
		icon = Image::FromFile("RegenshipPic.png");
	};
	~Regenship(){};
	virtual Void regeneration();//Восстанавливает броню корабля
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//Отрисовывает объект
	virtual String^ ToString() override;//Преобразует объект в строку
};

