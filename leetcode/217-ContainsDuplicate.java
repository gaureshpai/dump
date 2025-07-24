/*
Question: 
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for(int i=0;i<nums.length;i++)
            hash.add(nums[i]);
        if(hash.size()!=nums.length)return true;
        return false;
    }
}

/*First thought: Higher time complexity

class Solution {
    public boolean containsDuplicate(int[] nums) {
        for(int i=0;i<nums.length; i++){
            for(int j=1;j<nums.length;j++){
                if(nums[i]==nums[j])
                    return true;
                }
            }
        }
        return false;
    }
}*/