/*
Question:
Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once
*/

class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        boolean[] he = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            char c = Character.toLowerCase(arr[i]);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') he[i] = true;
            else he[i] = false;
        }

        int left = 0, right = arr.length - 1;
        while (left < right)
            if (he[left] && he[right]) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } 
            else if (he[left]) right--;
            else if (he[right]) left++;
            else {
                left++;
                right--;
            }

        return new String(arr);
    }
}