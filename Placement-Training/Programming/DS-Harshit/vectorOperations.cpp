/** Question: Add and remove elements from the end of the vector */

#include<iostream>
using namespace std;
#include<vector>

int main(){
	vector<int> a ={1,2,3,4,5};
	for(int i=0;i<a.size();i++)
		cout<<a[i]<<"\t";
	cout<<endl;
	a.push_back(6);
	for(int i=0;i<a.size();i++)
		cout<<a[i]<<"\t";
	cout<<endl;
	a.pop_back();
	for(int i=0;i<a.size();i++)
		cout<<a[i]<<"\t";
	cout<<endl;
}
