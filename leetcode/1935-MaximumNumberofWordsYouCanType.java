/*
Question:
There is a malfunctioning keyboard where some letter keys do not work. 
All other keys on the keyboard work properly.
Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.
*/

class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int count = 0;

        for (String word : words) {
            boolean canBeTyped = true;
            
            for (char brokenChar : brokenLetters.toCharArray())
                if (word.contains(String.valueOf(brokenChar))) {
                    canBeTyped = false;
                    break;
                }

            if (canBeTyped) count++;
        }
        
        return count;
    }
}