//Given a list of strings, remove all consecutive duplicate strings and return the resulting list.

#include<iostream>
using namespace std;
#include<vector>
#include<stack>

int main(){
	vector<string> a = {"ab","bc","cd","cd","bc","cd"};
	stack<string> st,temp;
	for(int i=0;i<a.size();i++){
		if(st.empty())
			st.push(a[i]);
		else if(st.top()!=a[i])
			st.push(a[i]);
		else{
			st.pop();
		}
	}
	while(!st.empty())
		temp.push(st.top()),st.pop();
	while(!temp.empty())
		st.push(temp.top()),temp.pop(),cout<<st.top();		
}