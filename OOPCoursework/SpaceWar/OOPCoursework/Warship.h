#pragma once
#include "spaceship.h"

using namespace std;
using namespace System;
using namespace Drawing;

ref class Warship:	public SpaceShip{
private:
	Int32 firespeed;//�������� ��������
protected:
	Int32 MAXSPEED;//������������ ��������
	Int32 MAXARMOR;//������������ �����
	Int32 MAXVISIBILITY;//������������ ������ ���������
	Int32 MAXFIRESPEED;//������������ �������� ��������
public:
	Warship();//����������� �� ���������
	Warship(Point^ _location,Graph^ _graph,const Int32 _speed,const Int32 _armor,const Int32 _visibility,const Int32 _firespeed);//����������� � �����������
	Warship(Warship%);//����������� �����������
	virtual property Int32 FireSpeed{//�������� �������������� ������ � ��������� firespeed
		Void set(Int32 Value);
		Int32 get();
	};

	virtual property Int32 Speed{//�������� �������������� ������ � ��������� speed
		Void set(Int32 Value) override;
		Int32 get() override{return SpaceShip::Speed;};
	}

	virtual property Int32 Armor{//�������� �������������� ������ � ��������� armor
		Void set(Int32 Value) override;
		Int32 get() override{return SpaceShip::Armor;}
	}

	virtual property Int32 Visibility{//�������� �������������� ������ � ��������� visibility
		Void set(Int32 Value) override;
		Int32 get() override{return SpaceShip::Visibility;}
	}
	virtual ~Warship();
	bool fire(SpaceShip^);//������� ���� �������
	virtual String^ ToString() override;//����������� ������ � ������
	virtual void attack(Rootclass^ ship) override;//�����
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//������������ ������
};

