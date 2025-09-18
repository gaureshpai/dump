/*
Question:
You are given an array apple of size n and an array capacity of size m.
There are n packs where the ith pack contains apple[i] apples. 
There are m boxes as well, and the ith box has a capacity of capacity[i] apples.
Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.
Note that, apples from the same pack can be distributed into different boxes.
*/

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int apples = 0, count = 0;

        for(int app: apple) apples += app;

        Arrays.sort(capacity);
        
        for(int i = capacity.length - 1; i >= 0; i--)
            if(apples <= 0) break;
            else {
                apples -= capacity[i];
                count++;
            }

        return count;
    }
}