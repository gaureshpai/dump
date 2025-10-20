/*
Question:
You are given an integer num. 
Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
Return the rearranged number with minimal value.
Note that the sign of the number does not change after rearranging the digits.
*/

import java.util.*;

class Solution {

    public long negativeMin(long num) {
        long n = Math.abs(num);
        String str = Long.toString(n);
        Long[] arr = new Long[str.length()];

        for (int i = 0; i < str.length(); i++)
            arr[i] = Long.parseLong(Character.toString(str.charAt(i)));

        Arrays.sort(arr, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (long digit : arr) sb.append(digit);

        long ans = Long.parseLong(sb.toString());
        return -ans;
    }

    public long positiveNum(long num) {
        long n = Math.abs(num);
        String str = Long.toString(n);
        Long[] arr = new Long[str.length()];

        for (int i = 0; i < str.length(); i++)
            arr[i] = Long.parseLong(Character.toString(str.charAt(i)));

        Arrays.sort(arr);

        if (arr[0] == 0) getMin(arr);

        StringBuilder sb = new StringBuilder();
        for (long digit : arr) sb.append(digit);

        long ans = Long.parseLong(sb.toString());
        return ans;
    }

    public void getMin(Long[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] != 0) {
                Long temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                break;
            }
    }

    public long smallestNumber(long num) {
        if (num < 0) return negativeMin(num);
        return positiveNum(num);
    }
}