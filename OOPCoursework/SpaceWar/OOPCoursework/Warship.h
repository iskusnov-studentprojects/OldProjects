#pragma once
#include "spaceship.h"

using namespace std;
using namespace System;
using namespace Drawing;

ref class Warship:	public SpaceShip{
private:
	Int32 firespeed;//Скорость стрельбы
protected:
	Int32 MAXSPEED;//Максимальная скорость
	Int32 MAXARMOR;//Максимальная броня
	Int32 MAXVISIBILITY;//Максимальный радиус видимости
	Int32 MAXFIRESPEED;//Максимальная скорость стрельбы
public:
	Warship();//Конструктор по умолчанию
	Warship(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility,const Int32 _firespeed);//Конструктор с параметрами
	Warship(Warship%);//Конструктор копирования
	virtual property Int32 FireSpeed{//Свойство осуществляющее доступ к параметру firespeed
		Void set(Int32 Value);
		Int32 get();
	};

	virtual property Int32 Speed{//Свойство осуществляющее доступ к параметру speed
		Void set(Int32 Value) override;
		Int32 get() override{return SpaceShip::Speed;};
	}

	virtual property Int32 Armor{//Свойство осуществляющее доступ к параметру armor
		Void set(Int32 Value) override;
		Int32 get() override{return SpaceShip::Armor;}
	}

	virtual property Int32 Visibility{//Свойство осуществляющее доступ к параметру visibility
		Void set(Int32 Value) override;
		Int32 get() override{return SpaceShip::Visibility;}
	}
	virtual ~Warship();
	bool fire(SpaceShip^);//Наносит урон кораблю
	virtual String^ ToString() override;//Преобразует объект в строку
	virtual void attack(Rootclass^ ship) override;//Атака
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//Отрисовывает объект
};

