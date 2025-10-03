/*
Question:
Given an integer array nums, return the third distinct maximum number in this array. 
If the third maximum does not exist, return the maximum number.
*/

class Solution {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        
        for (int num : nums) set.add(num);
    
        if (set.size() < 3) return set.first();
        else {
            set.pollFirst();
            set.pollFirst();

            return set.first();
        }
    }
}
