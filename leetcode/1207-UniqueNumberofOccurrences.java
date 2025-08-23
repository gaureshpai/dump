/*
Question:
Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
*/

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> num = new HashMap<>();

        for(int i = 0; i < arr.length; i++) num.put(arr[i], num.getOrDefault(arr[i], 0) + 1);
        HashSet<Integer> freq = new HashSet<Integer>();
        
        for(int count : num.values())
            if(!freq.add(count)) return false;
        return true;
    }
}