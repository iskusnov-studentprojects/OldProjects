// OOPCoursework.cpp: ������� ���� �������.

#include "stdafx.h"
#include "Form1.h"

using namespace OOPCoursework;

[STAThreadAttribute]
int main(array<System::String ^> ^args)
{
	// ��������� ���������� �������� Windows XP �� �������� �����-���� ��������� ����������
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false); 

	// �������� �������� ���� � ��� ������
	Application::Run(gcnew SpaceWar());
	return 0;
}
