#pragma once
#include "stdafx.h"
#include "rootclass.h"
#include "Interfaces.h"
#include "Field.h"

using namespace std;
using namespace System;
using namespace Drawing;
using namespace Interfaces;

ref class SpaceShip: public Rootclass, public ITactics, public IPic{
private:
	Int32 speed;//Скорость
	Int32 armor;//Броня
	Int32 visibility;//Радиус видимости
protected:
	Image^ icon;//Картинка
public:
	SpaceShip(void);//Конструктор по умолчанию
	SpaceShip(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility);//Конструктор с параметрами
	SpaceShip(SpaceShip% copyobj);//Конструктор копирования
	~SpaceShip(){};
	virtual property Int32 Speed{//Свойство осуществляющее доступ к параметру speed
		Void set(Int32 Value);
		Int32 get();
	};
	virtual property Int32 Armor{//Свойство осуществляющее доступ к параметру armor
		Void set(Int32 Value);
		Int32 get();
	};
	virtual property Int32 Visibility{//Свойство осуществляющее доступ к параметру visibility
		Void set(Int32 Value);
		Int32 get();
	};
	void moveto(Point _target);//Движение к точке
	void damage();//Получение урона(уменьшает armor)
	virtual String^ ToString () override;//Преобразование объекта в строку
	virtual Void retreat(Rootclass^ ship);//Отступление
	virtual Void default_move();//Движение в случайном направлении
	virtual Void attack(Rootclass^ ship) = 0;//Атака
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override = 0;//Отрисовывает объект

private:
/*	ref struct Graph{//Граф для работы BFS
		ref struct node{
			Point Value;
			Int32 visited;
			node^ up;
			node^ left;
			node^ down;
			node^ right;
			node(Int32 X, Int32 Y){
				Value.X = X, Value.Y = Y;
				visited = true;
			}
		};
		node^ head;
		node^ find(Int32 X, Int32 Y){
			node^ p = head;
			if(X > MAXCOORD || Y > MAXCOORD || X < 0 || Y < 0)
				return nullptr;
			while(X != p->Value.X)
				p = p->right;
			while(Y != p->Value.Y)
				p = p->down;
			return p;
		}
		Graph(Void){
			node^ p;
			for(Int32 y = 0; y <= MAXCOORD; y++){
				for(Int32 x = 0; x <= MAXCOORD; x++){
					if(!head){
						head = gcnew node(x,y);
						p = head;
					}else{
						if(x != 0){
							p->right = gcnew node(x,y);
							p->right->left = p;
							p = p->right;
							node^ u = find(x,y-1);
							p->up = u;
							if(u)
								u->down = p;
						}
					}
				}
				if(y != 100){
					p = find(0,y);
					p->down = gcnew node(0,y+1);
					p->down->up = p;
					p = p->down;
				}
			}
		}
		Void SetToZero(){
			node^ p = head;
			for(Int32 i = 0; i < MAXCOORD; i++){
				for(Int32 j = 0; j < MAXCOORD; j++){
					p->visited = false;
					if(p->right)
						p = p->right;
				}
				p = find(0,i+1);
			}
		}
	};
	Graph^ graph;//Граф*/
	
protected:
	Generic::Stack<Point>^ way;//Путь к точке
	Generic::Stack<Point>^ BFS(Point start, Point target);//Определяет кратчайший путь к точке
};