#pragma once
#include "Rootclass.h"

using namespace std;
using namespace System;
using namespace Collections;
using namespace Generic;

ref class Rase{
private:
	Int32 capturedprogress;//Показывает на сколько захвачена планета
	Rootclass^ nextplanet;//Указатель на захватываемую планету
	Int32 numberwarship;//Количество боевых кораблей
	Int32 numbership_radar;//Количество кораблей-радаров
	Int32 numbertransportship;//Количество транспортирующих кораблей
	Int32 numberregenship;//Количество кораблей с регенерирующей броней
	Int32 numberstealthship;//Количество кораблей невидимок
	String^ name;//Название расы
	Int32 rasetype;//Тип расы(использует корабли невидимки или корабли с регенерирующей броней)
public: 
	static const Int32 MAXWARSHIP = 10;//Максимальное количество боевых кораблей
	static const Int32 MAXSHIP_RADAR = 5;//Максимальное количество кораблей-радаров
	static const Int32 MAXTRANSPORTSHIP = 5;//Максимальное количество транспортирующих кораблей
	static const Int32 MAXREGENSHIP = 6;//Максимальное количество кораблей с регенерирующей броней
	static const Int32 MAXSTEALTHSHIP = 6;//Максимальное количество кораблей невидимок
	List<Rootclass^> Memory;//Контейнер, хранящий корабли и планеты
public: 
	Rase(void);//Конструктор по умолчанию
	~Rase(Void){};
	Void Add(Rootclass^ var);//Добавить объект в контейнер
	Void Erase(Rootclass^ var);//Исключить объект из контейнера
	virtual String^ ToString() override;//Возвращает имя расы
	virtual property Int32 CapturedProgress{//Свойство осуществляющее доступ к параметру capturedprogress
				Void set(Int32 Value){capturedprogress = Value;}
				Int32 get(){return capturedprogress;}
			};
	virtual property Rootclass^ NextPlanet{//Свойство осуществляющее доступ к параметру nextplanet
				Void set(Rootclass^ Value);
				Rootclass^ get();
			};
	public: virtual property Int32 NumberWarship{//Свойство осуществляющее доступ к параметру numberwarship
				private: Void set(Int32 Value){numberwarship = Value;}
				public: Int32 get(){return numberwarship;}
			};

	public: virtual property Int32 NumberShip_radar{//Свойство осуществляющее доступ к параметру numbership_radar
				private: Void set(Int32 Value){numbership_radar = Value;}
				public: Int32 get(){return numbership_radar;}
			};

	public: virtual property Int32 NumberTransportship{//Свойство осуществляющее доступ к параметру numbertransportship
				private: Void set(Int32 Value){numbertransportship = Value;}
				public: Int32 get(){return numbertransportship;}
			};

	public: virtual property Int32 NumberRegenship{//Свойство осуществляющее доступ к параметру numberregenship
				private: Void set(Int32 Value){numberregenship = Value;}
				public: Int32 get(){return numberregenship;}
			};

	public: virtual property Int32 NumberStealthship{//Свойство осуществляющее доступ к параметру numberstealthship
				private: Void set(Int32 Value){numberstealthship = Value;}
				public: Int32 get(){return numberstealthship;}
			};

	public: virtual property String^ Name{//Свойство осуществляющее доступ к параметру name
				private: Void set(String^ Value){name = Value;}
				public: String^ get(){return name;}
			};

	public: virtual property Boolean Rasetype{//Свойство осуществляющее доступ к параметру rasetype
				private: Void set(Boolean Value){rasetype = Value;}
				public: Boolean get(){return rasetype;}
			};
};

