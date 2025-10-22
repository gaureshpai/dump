/*
Question:
Given an array of positive integers nums, return an array answer that consists of the digits of each integer in nums after separating them in the same order they appear in nums.
To separate the digits of an integer is to get all the digits it has in the same order.
For example, for the integer 10921, the separation of its digits is [1,0,9,2,1].
*/

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            String s = String.valueOf(nums[i]);  

            for (char c : s.toCharArray()) arr.add(c - '0');               
        }

        int[] res = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++) res[i] = arr.get(i);

        return res;
    }
}