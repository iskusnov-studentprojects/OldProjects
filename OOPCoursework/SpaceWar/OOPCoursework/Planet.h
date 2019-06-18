#pragma once
#include "Interfaces.h"
#include "Rootclass.h"

using namespace std;
using namespace System;
using namespace Drawing;
using namespace Interfaces;

ref class Planet: public Rootclass, public ICreation, public IPic{
private:
	Int32 resource;//Колличество ресурсов на планете
	Boolean captured;//Пометка захвата
	Int32 visibility;//Радиус видимости
protected:
	Image^ icon;//Картинка
public:
	Planet(Void);//Конструктор по умолчанию
	Planet(Point^ _location,Graph^ _graph, const Int32 _resourses,const Boolean _captured, const Int32 visibility);//Конструктор с параметрами
	Planet(Planet% copyobj);//Конструктор копирования
	~Planet(Void){};
	virtual property Int32 Resource{//Свойство осуществаляющее доступ к параметру resource
		Void set(Int32 Value);
		Int32 get();
	};
	virtual property Boolean Captured{//Свойство осуществаляющее доступ к параметру captured
		Void set(Boolean Value);
		Boolean get();
	};
	virtual property Int32 Visibility{//Свойство осуществаляющее доступ к параметру visibility
		Void set(Int32 Value);
		Int32 get();
	};
	static const Int32 MAXCAPTURE = 3;//Константа, показывающая сколько кораблей нужно высадить для захвата планеты
	virtual String^ ToString() override;//Преобразовать объект в строку
	virtual Rootclass^ create_warship();//Создать Warship
	virtual Rootclass^ create_transportship();//Создать Transportship
	virtual Rootclass^ create_ship_radar();//Создать Ship_radar
	virtual Rootclass^ create_regenship();//Создать Regenship
	virtual Rootclass^ create_stealthship();//Создать Stealthship
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//Отрисовать объект
};

