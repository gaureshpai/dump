/*
Question:
You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:
- Every element less than pivot appears before every element greater than pivot.
- Every element equal to pivot appears in between the elements less than and greater than pivot.
- The relative order of the elements less than pivot and the elements greater than pivot is maintained.
    - More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. If i < j and both elements are smaller (or larger) than pivot, then pi < pj.
Return nums after the rearrangement.
*/

class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> before = new ArrayList<>();
        List<Integer> piv = new ArrayList<>();
        List<Integer> after = new ArrayList<>();
        
        int[] res = new int[nums.length];

        for(int num: nums)
            if(num < pivot) before.add(num);
            else if(num == pivot) piv.add(num);
            else after.add(num);

        for(int i = 0; i < before.size(); i++) res[i] = before.get(i);

        for(int i = 0; i <  piv.size(); i++) res[i + before.size()] = piv.get(i);

        for(int i = 0; i < after.size(); i++) res[i + before.size() + piv.size()] = after.get(i);
        
        return res;
    }
}