/*
Question:
You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
*/

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] res = new int[spells.length];

        for(int i = 0; i < spells.length; i++){
            int left = 0, right = potions.length - 1;
            while(left <= right){
                int mid = (left + right)/2;
                long re = (long) potions[mid] * (long) spells[i];
                
                if(re >= success) right = mid - 1;
                else left = mid + 1;
            }
            res[i] = potions.length - left;
        }

        return res;
    }
}

/*
First thought:
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] res = new int[spells.length];

        for(int i = 0; i < spells.length; i++){
            for(int j = 0; j < potions.length; j++){
                long re = (long) potions[j] * (long) spells[i];

                if(re >= success){
                    res[i] = potions.length - j;
                    break;
                }
            }
        }

        return res;
    }
}
*/