/*
Question: 
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
*/

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= 1) return nums;

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort((a, b) -> b.getValue() - a.getValue());

        List<Integer> freq = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            freq.add(entryList.get(i).getKey());
        }

        int[] result = new int[freq.size()];
        for (int i = 0; i < freq.size(); i++) {
            result[i] = freq.get(i);
        }

        return result;
    }
}