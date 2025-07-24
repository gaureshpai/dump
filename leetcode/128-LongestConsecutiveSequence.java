/*
Question:
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length<1)return 0;
        if(nums.length<2)return 1;
        Arrays.sort(nums);
        int m = 0;
        int[] count = new int[nums.length];
        int co = 0;
        for(int i = 0;i < nums.length-1;i++){
            if((nums[i+1]==nums[i]+1)){
                co++;
            }
            else if((nums[i+1]==nums[i]))
                continue;
            else{
                count[m] = co;
                m++;
                co = 0;
            }
        }
        count[m] = co;
        Integer[] boxed = Arrays.stream(count).boxed().toArray(Integer[]::new);
        Arrays.sort(boxed,Collections.reverseOrder());
        return (boxed[0]+1);
    }
}