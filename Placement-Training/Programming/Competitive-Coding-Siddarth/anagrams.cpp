/** Question: Check if two strings are anagrams */

#include<iostream>
using namespace std;

int main(){
	string s1 = "abaaa";//template
	string s2 = "aaaba";
	if(s1.size()!=s2.size()){
		cout<<"0";
		return 0;
	}
	int i=0,n,j=0,x=s1.size(),tch,count=0;
	for(i=0;i<x;i++){
		tch = s1[i];
		count =0;
		for(int j=0;j<x;j++)
			if(tch == s2[j])
				count++;
		for(int j=0;j<x;j++)
			if(tch == s1[j])
				count--;
		if(count != 0){
			cout<<"0";
			return 0;
		}
	}
	cout<<"1";
	return 1;
}
