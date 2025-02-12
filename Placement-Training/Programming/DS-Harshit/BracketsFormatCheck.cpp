// Question: Check if the brackets in a string are in the correct format.

#include<iostream>
#include<vector>
#include<stack>
using namespace std;

int main(){
    string a;
    cout << "Enter a string of brackets: ";
    cin >> a;

    stack<char> st;
    
    for(int i = 0; i < a.size(); i++){
        if(a[i] == ')'){
            if(st.empty() || st.top() != '('){
                cout << 0;
                return -1;
            }
            st.pop();
        } else if(a[i] == '('){
            st.push(a[i]);
        }
    }
    
    if(st.empty()){
        cout << 1;
    } else {
        cout << 0;
    }
    
    return 0;
}