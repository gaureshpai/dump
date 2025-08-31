/*
Question:
Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
*/

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length, h = 0;

        for (int i = 0; i < n; i++) {
            int currH = citations[i], numPapers = n - i;
            if (currH >= numPapers) h = Math.max(h, numPapers);
        }
        
        return h;
    }
}