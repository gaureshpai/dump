/*
Question:
You are given a 0-indexed integer array nums. 
In one operation, you may do the following:
- Choose two integers in nums that are equal.
- Remove both integers from nums, forming a pair.
The operation is done on nums as many times as possible.
Return a 0-indexed integer array answer of size 2 where answer[0] is the number of pairs that are formed and answer[1] is the number of leftover integers in nums after doing the operation as many times as possible.
*/

class Solution {
    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count  = 0, remain = 0;

        for (int num: nums)
            if (frequencyMap.containsKey(num)) frequencyMap.put(num, frequencyMap.get(num) + 1);
            else frequencyMap.put(num, 1);

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()){
            count += entry.getValue() / 2;
            remain += entry.getValue() % 2;
        }
        
        return new int[]{count, remain};
    }
}