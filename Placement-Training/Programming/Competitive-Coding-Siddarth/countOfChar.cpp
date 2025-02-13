/** Question: Count the number of occurances of each character in a string */

#include<iostream>
using namespace std;

int main(){
	string s1 = "GaureshGPai";
	for(int i=0;i<s1.size();i++){
		char ch = s1[i];
		int count=0;
		for(int j=0;j<s1.size();j++){
			if(ch == s1[j])
				count++;
		}
		cout<<s1[i]<<":"<<count<<endl;	
	}
	return 1;
}
