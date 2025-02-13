/** Question: Find the sum of the digits of a number */

#include<iostream>
using namespace std;

int main(){
    int n=123, sum=0;

    while(n>0){
        sum += n%10;
        n /= 10;
    }

    cout<<sum;
    return 0;
}