/** Question: Find the last element of the triangle of size n  where the first element is 1 and the next element is the sum of the previous two elements from the column of the previous column except the first column elements where the first element is 1 and the other elements are the   from the previous row */

#include<iostream>
using namespace std;

int main(){
	int n=5;
	int a[n][n];
	a[0][0]=1;
	for(int i=1;i<n;i++){
		for(int j=0;j<i+1;j++){
			if(j==0)
				a[i][j]=a[i-1][i-1];
			else
				a[i][j] = a[i][j-1]+a[i-1][j-1];	
		}
	}
	cout<<a[n-1][n-1];
}