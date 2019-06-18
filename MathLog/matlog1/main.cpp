#include <iostream>
#include <stdlib.h>
#include <conio.h>

using namespace std;

int o;

void outmatrix(char **matrix, int n, int m){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(rand()%2)
                matrix[i][j] = '*';
            else
                matrix[i][j] = '.';
            cout<<matrix[i][j];
        }
        cout<<endl;
    }
}

//Сложность log2
int pow(int a, int n){
    if(n == 0)
        return 1;
    if(n%2 == 1)
        return a*pow(a,n-1);
    return pow(a,n/2) * pow(a,n/2);
}

//Числа фибоначи. Сложность 1 2 4 7 12 20 33 54 88
int tiles(int n){
    if(n == 0 || n == 1)
        return 1;
    o++;
    return tiles(n - 1) + tiles(n - 2);
}

void hanoi(int n, char a, char b, char c){
    if(n == 0) return;
    o++;
    hanoi(n-1,a,c,b);
    cout << "from " << a << " to " << c << endl;
    hanoi(n-1,b,a,c);
}

//Сложность 1 2 4 7 12 21 36 62 106
int machine1(int n){
    int res = 0;
    if(n == 0)
        return 1;
    o++;
    if(n>=10)
        res += machine1(n-10);
    if(n>=5)
        res += machine1(n-5);
    if(n>=2)
        res += machine1(n-2);
    if(n>=1)
        res += machine1(n-1);
    return res;
}

//Сложность не более чем n^4
int machine2(int n){
    int res = 0;
    for(int i = 0; i <= n; i++)
        for(int j = 0; j <= n-i*10; j++)
            for(int k = 0; k <= n-i*10-j*5; k++)
                for(int t = 0; t <= n-i-j-k; t++)
                    if(10*i+j*5+k*2+t == n){
                        res++;
                        o++;
                    }
    return res;
}

int types[5] = {1,2,5,10};

int machine2rec(int sum,int n){
    if(sum == 0) return 1;
    if(sum < 0 || n == -1) return 0;
    return machine2rec(sum,n-1) + machine2rec(sum - types[n],n);
}


//Сложность log2
bool binsearch(int t, int b, int e, int mass[]){
    if(e == b || t < mass[b] || t > mass[e])
        return false;
    if(t == mass[(e+b)/2] || t == mass[(e+b)/2 + 1])
        return true;
    if(t > mass[(e+b)/2])
        binsearch(t,(e+b)/2+1,e,mass);
    else
        binsearch(t,b,(e+b)/2,mass);
}


void segmentsearched(char **matrix,int i, int j, int n, int m){
    matrix[i][j] = '.';
    o++;
    if(i-1>=0 && matrix[i-1][j] == '*')
        segmentsearched(matrix,i-1,j,n,m);
    if(j-1>=0 && matrix[i][j-1] == '*')
        segmentsearched(matrix,i,j-1,n,m);
    if(i+1<n && matrix[i+1][j] == '*')
        segmentsearched(matrix,i+1,j,n,m);
    if(j+1<m && matrix[i][j+1] == '*')
        segmentsearched(matrix,i,j+1,n,m);
}

int searchsegments(char **matrix,int n, int m){
    int segments = 0;
    for(int i = 0; i < n; i++)
        for(int j = 0; j < m; j++)
            if(matrix[i][j] == '*'){
                segments++;
                o++;
                segmentsearched(matrix,i,j,n,m);
            }
    return segments;
}

int main()
{
    int n;
    while(true){
        o=0;
        cin >> n;
        //Возведение в степень
        /*
        int a;
        cin >> a;
        cout<< pow(a,n) <<endl;
        */

        //Плитки
        /*
        cout<< tiles(n) <<endl;
        */

        //Ханойские башни

        hanoi(n,'a','b','c');


        //Поиск количесва сегментов
        /*
        int m;
        cin>>m;
        char **matrix = new char*[n];
        for(int i = 0; i < m; i++)
            matrix[i] = new char[10];
        outmatrix(matrix,n,m);
        cout<<searchsegments(matrix,n,m)<<endl;
        */

        //Первый автомат
        /*
        cout<<machine1(n)<<endl;
        */

        //Второй автомат
        /*
        cout<< machine2(n)<<endl;
        */

        //Бинарный поиск
        /*
        int mass[] = {1,2,3,4,5,6,7,8,9};
        if(binsearch(n,0,8,mass))
            cout<<"true"<<endl;
        else
            cout<<"false"<<endl;
            */

        //cout << o << endl;
    }
    return 0;
}
