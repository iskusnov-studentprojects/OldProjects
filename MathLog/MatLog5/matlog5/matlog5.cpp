// matlog5.cpp: определяет точку входа для консольного приложения.
//

#include "stdafx.h"

int N;
int M;
int **answer;

int mass_sum(int mass[]);
void outmass(int mass[]);
int countsquaresize(int mass[], int i);
int tryset(int i0, int j0, int n);
void set(int *mass, int i0, int j0, int numb);
int setsquare(int *mass, int numb);
int setallsquares(int *mass);
int maxnumb(int* mass);

int _tmain(int argc, _TCHAR* argv[])
{
	ifstream reader("napkin.in");
	ofstream writer("napkin.out");
	reader >> N >> M;
	reader.close();
	int *mass = new int[M];
	for (int i = 0; i < M; i++)
		mass[i] = 1;
	char c;
	if (N*N < M || !countsquaresize(mass, 0)){
		writer << "No";
		writer.close();
		return 0;
	}
	answer = new int*[N+1];
	for (int i = 0; i <= N; i++){
		answer[i] = new int[N + 1];
		for (int j = 0; j <= N; j++){
			if (i < N && j < N)
				answer[i][j] = 0;
			else
				answer[i][j] = -1;
		}
	}
	if (setallsquares(mass)){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++)
				writer << answer[i][j];
			writer << endl;
		}
	}
	else
		writer << "No";
	writer.close();
	return 0;
}

int mass_sum(int mass[]){
	int sum = 0;
	for (int i = 0; i < M; i++)
		sum += mass[i]*mass[i];
	return sum;
}

void outmass(int mass[]){
	for (int i = 0; i < M; i++){
		cout << mass[i] << ' ';
	}
	cout << endl;
}

int countsquaresize(int mass[], int i){
	if (M == 1){
		mass[0] = N;
		return 1;
	}
	for (; mass[i] <= N; mass[i]++){
		if (N*N == mass_sum(mass))
			return 1;
		if (mass[i] >= N)
			return 0;
		if (i < M - 1){
			mass[i + 1] = 1;
			if(countsquaresize(mass, i + 1))
				return 1;
		}
	}
}

int tryset(int i0, int j0, int n){
	for (int i = i0; i < n +i0; i++){
		for (int j = j0; j < n + j0; j++){
			if (answer[i][j] != 0){
				return 0;
			}
		}
	}
	return 1;
}

void set(int *mass, int i0, int j0, int numb){
	for (int i = i0; i < mass[numb] + i0; i++){
		for (int j = j0; j < mass[numb] + j0; j++){
			answer[i][j] = numb + 1;
		}
	}
}

int setsquare(int *mass, int numb){
	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			if (tryset(i, j, mass[numb])){
				set(mass, i, j, numb);
				return 1;
			}
		}
	}
	return 0;
}

int setallsquares(int *mass){
	int n;
	for (int i = 0; i < M; i++){
		n = maxnumb(mass);
		if (!setsquare(mass, n))
			return 0;
		mass[n] = 0;
	}
	return 1;
}

int maxnumb(int* mass){
	int max = mass[0];
	int numb = 0;
	for (int i = 0; i < M; i++)
		if (mass[i] > max){
			max = mass[i];
			numb = i;
		}
	return numb;
}