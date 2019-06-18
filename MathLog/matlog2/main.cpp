#include <iostream>
#include <stdlib.h>
#include <conio.h>

int s(int x);
int sg(int x);
int plus(int x, int y);
int minus(int x, int y);
int minus1(int x);
int mul(int x, int y);
int del(int x, int y);
int mod(int x, int y);

int main()
{
    int x, n = 0;
    std::cin>>x;
    while(sg(x)){
        if(mod(x,16) == 1)
            n++;
        x = del(x,16);
    }
    std::cout << n << std::endl;
    return 0;
}

int s(int x){
    return x+1;
}

int sg(int x){
    if(x == 0)
        return 0;
    else
        return 1;
}

int plus(int x, int y){
    if(y == 0)
        return x;
    return s(plus(x,y-1));
}

int minus1(int x){
    if(x == 0)
        return 0;
    else
        return x - 1;
}

int minus(int x, int y){
    if(y == 0)
        return x;
    return minus1(minus(x,y - 1));
}

int mul(int x, int y){
    if(y == 0)
        return 0;
    return plus(mul(x,y-1),x);
}


int del(int x, int y){
    int z = 0;
    while(mul(!sg(minus(z,y)),!sg(minus(mul(s(z),y),x))))
        z++;
    return z;
}

int mod(int x, int y){
    return minus(x,mul(del(x,y),y));
}
