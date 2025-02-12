/** Question: Add the size of the stack  to the top of the stack */

#include<iostream>
using namespace std;
#include<string>
#include<stack>

int main(){
	stack<int> st,temp;
	for(int i=0;i<=5;i++)
		st.push(i);
	while(!st.empty()){
		cout<<st.top()<<"\t";
		temp.push(st.top());
		st.pop();
	}
	cout<<endl<<endl;
	int n=6;
	st.push(n);
	for(int i=0;i<=5;i++)
		st.push(temp.top()),temp.pop();
	while(!st.empty()){
		cout<<st.top()<<"\t";
		st.pop();
	}
	cout<<endl;
}
