// OS L12 C++.cpp: �������u�t�u�|���u�� �������{�� �r�����t�p �t�|�� �{���~�����|���~���s�� �����y�|���w�u�~�y��.
//

#include "stdafx.h"
#include <windows.h>
#include <clocale>
#include <iostream>

using namespace std;

bool dirExists(const wchar_t path[])
{
	DWORD ftyp = GetFileAttributes(path);
	if(ftyp == INVALID_FILE_ATTRIBUTES)
		return false;
	if(ftyp & FILE_ATTRIBUTE_DIRECTORY)
		return true;
	return false;
}

bool uncompress(const wchar_t path[])
{
	HANDLE handle = CreateFile(path, GENERIC_READ | GENERIC_WRITE, NULL, NULL, OPEN_EXISTING, FILE_FLAG_BACKUP_SEMANTICS, NULL);
	return DeviceIoControl(handle, FSCTL_SET_COMPRESSION, COMPRESSION_FORMAT_NONE, sizeof(USHORT), NULL, 0, 0, NULL);
}

int _tmain(int argc, _TCHAR* argv[])
{
	cout << GENERIC_WRITE << "   " << GENERIC_READ << endl;
	setlocale(LC_ALL,"");
	char cpath[1000];
	cout << "������� ���� � �����: " << endl;
	cin >> cpath;
	size_t tempsize = strlen(cpath) + 1, convertedChars = 0;
	wchar_t wcpath[1000];
	mbstowcs_s(&convertedChars, wcpath, tempsize, cpath, _TRUNCATE);
	if(!dirExists(wcpath))
	{
		cout << "��������� ���� �������� ������������." << endl;
		system("pause");
		return 0;
	}
	if(!(GetFileAttributes(wcpath) & FILE_ATTRIBUTE_COMPRESSED))
	{
		cout << "����� �� �����." << endl;
		system("pause");
		return 0;
	}
	if(uncompress(wcpath))
		cout << "�������� ������ �������." << endl;
	else
		cout << "�������� ������ ��������." << endl;
	system("pause");
	return 0;
}

