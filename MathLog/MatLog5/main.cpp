#include <iostream>

using namespace std;
int sizecloth;
int countsquaressize(int mass[], int sizemass);

int main()
{
    sizecloth = 4;
    int n = 4;
    int mass[6] = {3,1,1,1};
    countsquaressize(mass,n);
    return 0;
}

int countsquaressize(int mass[], int sizemass){
    int sum = 0;
    for(int i = 0; i < sizemass; i++){
        sum += mass[i]*mass[i];
        cout << mass[i] << ' ';
    }
    cout << endl;
    if(sum==sizecloth*sizecloth)
        return 1;
    for(int i = 0; i < sizemass - 1; i++)
    {
        if(mass[i] > mass[i+1])
        {
            mass[i]--;
            mass[i+1]++;
            break;
        }
    }
    return countsquaressize(mass, sizemass);
}
