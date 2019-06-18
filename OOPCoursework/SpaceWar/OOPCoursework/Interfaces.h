#pragma once
#include "Rase.h"

using namespace System;
using namespace Drawing;

namespace Interfaces{
	public interface class IPic{//��������� ��������� �������
	public:
		virtual void draw(Graphics^ canvas,Rectangle^ canvassize,Rectangle^ cursor) = 0;//����� ��������� �������
	};

	public interface class ICreation{//��������� �������� ��������
	public:
		virtual Rootclass^ create_warship() = 0;//�����, ��������� Warship
		virtual Rootclass^ create_stealthship() = 0;//�����, ��������� Stealthship
		virtual Rootclass^ create_transportship() = 0;//�����, ��������� Transportship
		virtual Rootclass^ create_ship_radar() = 0;//�����, ��������� Ship_radar
		virtual Rootclass^ create_regenship() = 0;//�����, ��������� Regenship
	};

	public interface class ITactics{//��������� ����������� ��������� ��������
	public:
		virtual Void attack(Rootclass^ ship) = 0;//����� �����
		virtual Void retreat(Rootclass^ ship) = 0;//����� �����������
		virtual Void default_move();//����� �������� � ��������� �����������
	};

	public interface class IRegeneration{//��������� �����������
	public:
		virtual void regeneration() = 0;//����� �����������
	};

	public interface class IStealth{//��������� �����������
	public:
		virtual Boolean stealthon(Rase^ rase) = 0;//����� ������������ ������� �� ������� ��� ������ ����
	};

	public interface class ICapture{//��������� ������� ������
	public:
		virtual Void capture(Rase^ var) = 0;//����� ������������ �������
	};
}