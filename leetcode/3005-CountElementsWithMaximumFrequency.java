/*
Question:
You are given an array nums consisting of positive integers.
Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
The frequency of an element is the number of occurrences of that element in the array.
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        int maxFreq = 0;

        for (int freq : freqMap.values())
            if (freq > maxFreq) maxFreq = freq;

        int total = 0;
        for (int freq : freqMap.values())
            if (freq == maxFreq) total += freq;

        return total;
    }
}
