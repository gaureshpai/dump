/*
Question:
Given an integer n, find the digit that occurs least frequently in its decimal representation. 
If multiple digits have the same frequency, choose the smallest digit.
Return the chosen digit as an integer.
The frequency of a digit x is the number of times it appears in the decimal representation of n
*/

class Solution {
    public int getLeastFrequentDigit(int n) {
        int[] freq = new int[10];
        int min = Integer.MAX_VALUE, minDigit = -1;
        
        while(n != 0){
            int last = n%10;
            freq[last]++;
            n = n/10;
        }

        for (int i = 0; i < 10; i++)
            if (freq[i] > 0 && freq[i] < min) {
                min = freq[i];
                minDigit = i;
            }

        return minDigit;
    }
}