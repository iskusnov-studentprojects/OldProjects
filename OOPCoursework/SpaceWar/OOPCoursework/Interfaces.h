#pragma once
#include "Rase.h"

using namespace System;
using namespace Drawing;

namespace Interfaces{
	public interface class IPic{//Интерфейс отрисовки объекта
	public:
		virtual void draw(Graphics^ canvas,Rectangle^ canvassize,Rectangle^ cursor) = 0;//Метод отрисовки объекта
	};

	public interface class ICreation{//Интерфейс создания кораблей
	public:
		virtual Rootclass^ create_warship() = 0;//Метод, создающий Warship
		virtual Rootclass^ create_stealthship() = 0;//Метод, создающий Stealthship
		virtual Rootclass^ create_transportship() = 0;//Метод, создающий Transportship
		virtual Rootclass^ create_ship_radar() = 0;//Метод, создающий Ship_radar
		virtual Rootclass^ create_regenship() = 0;//Метод, создающий Regenship
	};

	public interface class ITactics{//Интерфейс описывающий поведение кораблей
	public:
		virtual Void attack(Rootclass^ ship) = 0;//Метод атаки
		virtual Void retreat(Rootclass^ ship) = 0;//Метод отступления
		virtual Void default_move();//Метод движения в случайном направлении
	};

	public interface class IRegeneration{//Интерфейс регенерации
	public:
		virtual void regeneration() = 0;//Метод регенерации
	};

	public interface class IStealth{//Интерфейс невидимости
	public:
		virtual Boolean stealthon(Rase^ rase) = 0;//Метод определяющий невидим ли корабль для данной расы
	};

	public interface class ICapture{//Интерфейс захвата планет
	public:
		virtual Void capture(Rase^ var) = 0;//Метод захватывющий планету
	};
}