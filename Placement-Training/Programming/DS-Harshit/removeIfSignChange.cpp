/**Question: Remove all elements from the stack if the sign of the current element is different from the sign of the top element */

#include<iostream>
using namespace std;
#include<vector>
#include<stack>

int main(){
	vector<int> a = {5,4,2,-3,6,-4,-5,7};
	stack<int> st,temp;
	for(int i=0;i<a.size();i++){
		if(st.empty())
			st.push(a[i]);
		else if(st.top() > 0 && a[i] > 0)
			st.push(a[i]);
		else if(st.top() < 0 && a[i] < 0)
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