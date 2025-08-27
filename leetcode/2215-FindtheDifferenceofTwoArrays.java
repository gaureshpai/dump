/*
Question:
Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
- answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
- answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.
*/

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> res = new ArrayList();
        
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());

        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();

        for (int i : nums1) set1.add(i);
        for (int j : nums2) set2.add(j);

        for (int x : set1)
            if (!set2.contains(x)) res.get(0).add(x);
        for (int y : set2)
            if (!set1.contains(y)) res.get(1).add(y);

        return res;

    }
}

/*
First Thought:
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> fin = new ArrayList<>();
        Set<Integer> res1 = new HashSet<>();
        Set<Integer> res2 = new HashSet<>();
        boolean yes = false;
        for(int i = 0; i < nums1.length; i++){
            yes = false;
            for(int j = 0; j < nums2.length; j++){
                if(nums1[i] == nums2[j]) yes = true;
            }
            if(!yes) res1.add(nums1[i]);
        }
        for(int i = 0; i < nums2.length; i++){
            yes = false;
            for(int j = 0; j < nums1.length; j++){
                if(nums2[i] == nums1[j]) yes = true;
            }
            if(!yes) res2.add(nums2[i]);
        }
        List<Integer> ses1 = new ArrayList<>(res1);
        List<Integer> ses2 = new ArrayList<>(res2);
        fin.add(ses1);
        fin.add(ses2);
        return fin;
    }
}
*/