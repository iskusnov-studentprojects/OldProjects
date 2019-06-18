#pragma once
#include "Interfaces.h"
#include "Rootclass.h"

using namespace std;
using namespace System;
using namespace Drawing;
using namespace Interfaces;

ref class Planet: public Rootclass, public ICreation, public IPic{
private:
	Int32 resource;//����������� �������� �� �������
	Boolean captured;//������� �������
	Int32 visibility;//������ ���������
protected:
	Image^ icon;//��������
public:
	Planet(Void);//����������� �� ���������
	Planet(Point^ _location,Graph^ _graph, const Int32 _resourses,const Boolean _captured, const Int32 visibility);//����������� � �����������
	Planet(Planet% copyobj);//����������� �����������
	~Planet(Void){};
	virtual property Int32 Resource{//�������� ��������������� ������ � ��������� resource
		Void set(Int32 Value);
		Int32 get();
	};
	virtual property Boolean Captured{//�������� ��������������� ������ � ��������� captured
		Void set(Boolean Value);
		Boolean get();
	};
	virtual property Int32 Visibility{//�������� ��������������� ������ � ��������� visibility
		Void set(Int32 Value);
		Int32 get();
	};
	static const Int32 MAXCAPTURE = 3;//���������, ������������ ������� �������� ����� �������� ��� ������� �������
	virtual String^ ToString() override;//������������� ������ � ������
	virtual Rootclass^ create_warship();//������� Warship
	virtual Rootclass^ create_transportship();//������� Transportship
	virtual Rootclass^ create_ship_radar();//������� Ship_radar
	virtual Rootclass^ create_regenship();//������� Regenship
	virtual Rootclass^ create_stealthship();//������� Stealthship
	virtual Void draw(Graphics^ canvas,Rectangle^ canvassize, Rectangle^ cursor) override;//���������� ������
};

