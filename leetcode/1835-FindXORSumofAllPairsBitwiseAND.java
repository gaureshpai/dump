/*
Question:
The XOR sum of a list is the bitwise XOR of all its elements. 
If the list only contains one element, then its XOR sum will be equal to this element.
For example, the XOR sum of [1,2,3,4] is equal to 1 XOR 2 XOR 3 XOR 4 = 4, and the XOR sum of [3] is equal to 3.
You are given two 0-indexed arrays arr1 and arr2 that consist only of non-negative integers.
Consider the list containing the result of arr1[i] AND arr2[j] (bitwise AND) for every (i, j) pair where 0 <= i < arr1.length and 0 <= j < arr2.length.
Return the XOR sum of the aforementioned list.
*/

class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
        int sum1 = 0, sum2 = 0;

        for(int i: arr1) sum1 ^= i;
        for(int j: arr2) sum2 ^= j;
        
        return sum1 & sum2;
    }
}