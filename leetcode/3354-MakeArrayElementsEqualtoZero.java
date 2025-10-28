/*
Question:
You are given an integer array nums.
Start by selecting a starting position curr such that nums[curr] == 0, and choose a movement direction of either left or right.
After that, you repeat the following process:
-If curr is out of the range [0, n - 1], this process ends.
-If nums[curr] == 0, move in the current direction by incrementing curr if you are moving right, or decrementing curr if you are moving left.
-Else if nums[curr] > 0:
    - Decrement nums[curr] by 1.
    - Reverse your movement direction (left becomes right and vice versa).
    - Take a step in your new direction.
    - A selection of the initial position curr and movement direction is considered valid if every element in nums becomes 0 by the end of the process.
Return the number of possible valid selections.
*/

class Solution {
    public int countValidSelections(int[] nums) {
        int res = 0, n = nums.length;
        
        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                int bi = i, fi = i, fsum = 0, bsum = 0;

                while(fi < n){
                    fsum += nums[fi];
                    fi++;
                }

                while(bi >= 0){
                    bsum += nums[bi];
                    bi--;
                }

                int diff = Math.abs(fsum - bsum);

                if(diff == 0) res += 2;
                else if(diff == 1) res += 1;
            }
        }

        return res;
    }
}