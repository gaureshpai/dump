/*
Question:
Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
Please implement encode and decode
*/

class Solution {
    public String encode(List<String> strs) {
        StringBuilder result = new StringBuilder();

        for (String s : strs) result.append(s.length()).append('#').append(s);
        return result.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') j++;
            int length = Integer.parseInt(str.substring(i, j));
            String word = str.substring(j + 1, j + 1 + length);
            result.add(word);

            i = j + 1 + length;
        }
        return result;
    }
}
