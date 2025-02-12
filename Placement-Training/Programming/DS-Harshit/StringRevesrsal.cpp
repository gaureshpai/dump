/** Question: Reverse a string using stack */

#include<iostream>
using namespace std;
#include<vector>
#include<stack>

int main(){
	string s;
	cin>>s;
	stack<char> st;
	for(int i=0;i<s.size();i++)
		st.push(s[i]);
	s="";
	while(!st.empty()){
		s+=st.top();
		st.pop();
	}
	cout<<endl<<s<<endl;
}	
