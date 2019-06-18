#pragma once
#include "Rootclass.h"

using namespace std;
using namespace System;
using namespace Collections;
using namespace Generic;

ref class Rase{
private:
	Int32 capturedprogress;//���������� �� ������� ��������� �������
	Rootclass^ nextplanet;//��������� �� ������������� �������
	Int32 numberwarship;//���������� ������ ��������
	Int32 numbership_radar;//���������� ��������-�������
	Int32 numbertransportship;//���������� ���������������� ��������
	Int32 numberregenship;//���������� �������� � �������������� ������
	Int32 numberstealthship;//���������� �������� ���������
	String^ name;//�������� ����
	Int32 rasetype;//��� ����(���������� ������� ��������� ��� ������� � �������������� ������)
public: 
	static const Int32 MAXWARSHIP = 10;//������������ ���������� ������ ��������
	static const Int32 MAXSHIP_RADAR = 5;//������������ ���������� ��������-�������
	static const Int32 MAXTRANSPORTSHIP = 5;//������������ ���������� ���������������� ��������
	static const Int32 MAXREGENSHIP = 6;//������������ ���������� �������� � �������������� ������
	static const Int32 MAXSTEALTHSHIP = 6;//������������ ���������� �������� ���������
	List<Rootclass^> Memory;//���������, �������� ������� � �������
public: 
	Rase(void);//����������� �� ���������
	~Rase(Void){};
	Void Add(Rootclass^ var);//�������� ������ � ���������
	Void Erase(Rootclass^ var);//��������� ������ �� ����������
	virtual String^ ToString() override;//���������� ��� ����
	virtual property Int32 CapturedProgress{//�������� �������������� ������ � ��������� capturedprogress
				Void set(Int32 Value){capturedprogress = Value;}
				Int32 get(){return capturedprogress;}
			};
	virtual property Rootclass^ NextPlanet{//�������� �������������� ������ � ��������� nextplanet
				Void set(Rootclass^ Value);
				Rootclass^ get();
			};
	public: virtual property Int32 NumberWarship{//�������� �������������� ������ � ��������� numberwarship
				private: Void set(Int32 Value){numberwarship = Value;}
				public: Int32 get(){return numberwarship;}
			};

	public: virtual property Int32 NumberShip_radar{//�������� �������������� ������ � ��������� numbership_radar
				private: Void set(Int32 Value){numbership_radar = Value;}
				public: Int32 get(){return numbership_radar;}
			};

	public: virtual property Int32 NumberTransportship{//�������� �������������� ������ � ��������� numbertransportship
				private: Void set(Int32 Value){numbertransportship = Value;}
				public: Int32 get(){return numbertransportship;}
			};

	public: virtual property Int32 NumberRegenship{//�������� �������������� ������ � ��������� numberregenship
				private: Void set(Int32 Value){numberregenship = Value;}
				public: Int32 get(){return numberregenship;}
			};

	public: virtual property Int32 NumberStealthship{//�������� �������������� ������ � ��������� numberstealthship
				private: Void set(Int32 Value){numberstealthship = Value;}
				public: Int32 get(){return numberstealthship;}
			};

	public: virtual property String^ Name{//�������� �������������� ������ � ��������� name
				private: Void set(String^ Value){name = Value;}
				public: String^ get(){return name;}
			};

	public: virtual property Boolean Rasetype{//�������� �������������� ������ � ��������� rasetype
				private: Void set(Boolean Value){rasetype = Value;}
				public: Boolean get(){return rasetype;}
			};
};

