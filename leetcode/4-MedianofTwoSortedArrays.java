/*
Question:
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, x = 0, y = 0, i = 0;
        int[] nums3 = new int[m + n];
        
        while (x < m && y < n) {
            if (nums1[x] <= nums2[y]) nums3[i++] = nums1[x++];
            else nums3[i++] = nums2[y++];
        }
        while (x < m) nums3[i++] = nums1[x++];
        while (y < n) nums3[i++] = nums2[y++];
        
        int len = m + n;
        if (len % 2 == 1) return nums3[len/2];
        
        else return (nums3[len/2 - 1] + nums3[len/2]) / 2.0;
    }
}