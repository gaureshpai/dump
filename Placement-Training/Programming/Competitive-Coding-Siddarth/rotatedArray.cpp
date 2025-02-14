/** Questionn: Write the function to print the rotated array of a given array by n times */

#include<iostream>
using namespace std;

void printRotatedArray(int a[], int n,int size1){
	int b[size1],x=0;
	for(int i=(n%size1);i<size1;i++){
		b[x]=a[i];
		x=(x+1)%size1;
	}
	for(int i=0;i<(n%size1);i++){
		b[x]=a[i];
		x=(x+1)%size1;
	}
	for(int i=0;i<size1;i++){
		cout<<b[i]<<"\t";
	}
	
}

int main(){
	int a[]= {50,10,20,30,40};
	int n=11;
	int size1=sizeof(a);
	printRotatedArray(a,n,size1);
}	
