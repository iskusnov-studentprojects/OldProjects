#pragma once
#include "Field.h"

using namespace std;
using namespace System;
using namespace Drawing;

ref class Rootclass{
private:
	Point^ location;//Определяет местоположение объекта
protected:
	Graph^ graph;
public:
	static const Int32 MAXCOORD = 100;//Максимальная координата
	Rootclass(void){//Конструктор по умолчанию
		location = gcnew Point();
		Location = Point(0,0);
		graph = gcnew Graph();
	};

	Rootclass(Point^ loc,Graph^ _graph){//Конструктор с параметром
		location = gcnew Point();
		Location = loc;
		graph = _graph;
	};

	Rootclass(Rootclass% old){//Конструктор копирования
		location = gcnew Point();
		Location = old.Location;
		graph = old.graph;
	};
	~Rootclass(){};
	property Point^ Location{//Свойство осуществляющее доступ к параметру location
		Void set(Point^ Value){
			if(Value->X>MAXCOORD) location->X = MAXCOORD;
			else{if(Value->X<0) location->X = 0;
			else location->X = Value->X;}
			if(Value->Y>MAXCOORD) location->Y = MAXCOORD;
			else{if(Value->Y<0) location->Y = 0;
			else location->Y = Value->Y;}
		}
		Point^ get(){
			return location;
		}
	}
};

