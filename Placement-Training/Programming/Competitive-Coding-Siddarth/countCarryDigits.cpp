/** Question: Write the function to count the number of carry digits */

#include<iostream>
using namespace std;

int main(){
	int a = 998999,b=1,count=0,carry=0;
	while(a>0 || b>0){
		if((a%10+b%10+carry)>9){
			count++;
			carry=1;
		}else{
			carry=0;
		}
		a=a/10;
		b=b/10;
	}
	cout<<count;
}
