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
	Int32 speed;//��������
	Int32 armor;//�����
	Int32 visibility;//������ ���������
protected:
	Image^ icon;//��������
public:
	SpaceShip(void);//����������� �� ���������
	SpaceShip(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility);//����������� � �����������
	SpaceShip(SpaceShip% copyobj);//����������� �����������
	~SpaceShip(){};
	virtual property Int32 Speed{//�������� �������������� ������ � ��������� speed
		Void set(Int32 Value);
		Int32 get();
	};
	virtual property Int32 Armor{//�������� �������������� ������ � ��������� armor
		Void set(Int32 Value);
		Int32 get();
	};
	virtual property Int32 Visibility{//�������� �������������� ������ � ��������� visibility
		Void set(Int32 Value);
		Int32 get();
	};
	void moveto(Point _target);//�������� � �����
	void damage();//��������� �����(��������� armor)
	virtual String^ ToString () override;//�������������� ������� � ������
	virtual Void retreat(Rootclass^ ship);//�����������
	virtual Void default_move();//�������� � ��������� �����������
	virtual Void attack(Rootclass^ ship) = 0;//�����
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override = 0;//������������ ������

private:
/*	ref struct Graph{//���� ��� ������ BFS
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
	Graph^ graph;//����*/
	
protected:
	Generic::Stack<Point>^ way;//���� � �����
	Generic::Stack<Point>^ BFS(Point start, Point target);//���������� ���������� ���� � �����
};