#include <iostream>
#include <bitset>
#include <cmath>
 
using namespace std;
 
int main()
{
    const unsigned nb = 6; //nombre de bits
    const int max = pow(2, nb); //maximum
    for (int i = 0; i < max; i++)
        cout << bitset<nb>(i) << endl; //on affiche en binaire !
    return 0;
}
