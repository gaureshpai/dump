/*
QUestion:
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. 
If there is no future day for which this is possible, keep answer[i] == 0 instead.
*/

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int[] result = new int[temperatures.length];
        // Arrays.fill(result,0);
        
        for (int i = 0; i < temperatures.length; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int prevIndex = st.pop();
                result[prevIndex] = i - prevIndex;
            }
            st.push(i);
        }
        return result;
    }
}

/*
First Thought:
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] x = new int[temperatures.length];
        for(int i=0;i<temperatures.length;i++){
            int w=0;
            for(int j=i+1;j<temperatures.length;j++){
                    w++;
                    if(temperatures[i] < temperatures[j]){
                    x[i] = w;
                    break;
                }
            }
            
        }
        return x;
    }  
}
*/