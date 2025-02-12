// Given a number of rats 'r', the amount of food each rat needs 'unit',
// and an array 'houses' where each element represents the amount of food
// available in each house, determine how many houses are needed to feed all the rats.

#include<iostream>
using namespace std;

int countnhouses(int r, int unit, int houses[], int n){
    if(r <= 0 || unit <= 0 || n <= 0){
        cout << "Not a valid input";
        return 0;
    }
    int count = 0;
    int total = r * unit;
    for(int i = 0; i < n; i++){
        if(total > 0){
            total -= houses[i];
            count++;
        }
    }
    return count;
}

int main(){
	int a[] = {5,3,4,6,7,1};
	int x =  (sizeof(a)/sizeof(a[0]));
	int temp=0;
	for(int i=0;i<x;i++){
		for(int j=i+1;j<x-i-1;j++){
			if(a[j]<a[j+1]){
				temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
			}
		}
			
	}
	int n = countnhouses(7,2,a,x);
	cout<<n;
}
