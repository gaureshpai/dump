/*
Question:
You are given a 0-indexed array of strings words and a character x.
Return an array of indices representing the words that contain the character x.
Note that the returned array may be in any order.
*/

class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();

        for(int j = 0; j < words.length; j++)
            if(words[j].indexOf(x) > -1) res.add(j);
            
        return res;
    }
}

/*
First Thought:
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for(int j = 0; j < words.length; j++){
            boolean exist = false;
            for(int i = 0; i < words[j].length(); i++){
                if(x == words[j].charAt(i)) exist = true;
            }
            if(exist == true) res.add(j);
        }
        return res;
    }
}
*/

/*Second Thought:
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for(int j = 0; j < words.length; j++){
            for(int i = 0;i < words[j].length(); i++){
                if(x == words[j].charAt(i)) {
                    res.add(j);
                    break;
                }
            }
        }
        return res;
    }
}
*/