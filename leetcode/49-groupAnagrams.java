/*
Question:
Given an array of strings strs, group the anagrams together. You can return the answer in any order.
*/

import java.util.Arrays;

class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> sc = new HashMap<>();
        Map<Character, Integer> tc = new HashMap<>();

        if(s.length()!=t.length())  return false;
        for (int i=0;i<s.length();i++) sc.merge(s.charAt(i),1,Integer::sum);
        for (int i=0;i<t.length();i++) tc.merge(t.charAt(i),1,Integer::sum);
        if (sc.equals(tc)) return true;
        return false;
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];

        if (strs.length <= 1) {
            list.add(Arrays.asList(strs));
            return list;
        }
        for(int i = 0;i<strs.length;i++){
            List<String> inner = new ArrayList<>();
            for(int j=i+1;j<strs.length;j++){
                if(isAnagram(strs[i],strs[j])){
                    if (!visited[j]) {
                        visited[j] = true;
                        inner.add(strs[j]);
                    }    
                }
            }
            if(!visited[i]) {
                visited[i] = true;
                inner.add(strs[i]);
                list.add(inner);
            }  
        }
        return list;
    }
}