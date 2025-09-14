/*
Question:
Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
For a given query word, the spell checker handles two categories of spelling mistakes:
- Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
  - Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
  - Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
  - Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
- Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
  - Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
  - Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
  - Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
In addition, the spell checker operates under the following precedence rules:
- When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
- When the query matches a word up to capitlization, you should return the first such match in the wordlist.
- When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
- If the query has no matches in the wordlist, you should return the empty string.
Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
*/

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        int m = wordlist.length, n = queries.length;
        String[] res = new String[n];
        Map<String, Integer> caseSense = new HashMap<>();
        Map<String, Integer> caseInsense = new HashMap<>();
        Map<String, Integer> vowelErrors = new HashMap<>();

        for (int i = m - 1; i >= 0; i--) {
            String word = wordlist[i];
            caseSense.put(word, i);
            String lowerCase = word.toLowerCase();
            char[] vowelRepArr = lowerCase.toCharArray();

            for (int j = 0; j < vowelRepArr.length; j++)
                if (vowelRepArr[j] == 'e' || vowelRepArr[j] == 'i' || vowelRepArr[j] == 'o' || vowelRepArr[j] == 'u') vowelRepArr[j] = 'a';

            caseInsense.put(lowerCase, i);
            vowelErrors.put(new String(vowelRepArr), i);
        }
        for (int i = 0; i < n; i++) {
            String word = queries[i];

            if (caseSense.containsKey(word)) {
                res[i] = word;
                continue;
            }

            String lowerCase = word.toLowerCase();

            if (caseInsense.containsKey(lowerCase)) {
                res[i] = wordlist[caseInsense.get(lowerCase)];
                continue;
            }

            char[] vowelRepArr = lowerCase.toCharArray();

            for (int j = 0; j < vowelRepArr.length; j++) 
                if (vowelRepArr[j] == 'e' || vowelRepArr[j] == 'i' || vowelRepArr[j] == 'o' || vowelRepArr[j] == 'u') vowelRepArr[j] = 'a';

            String vowelRepStr = new String(vowelRepArr);

            if (vowelErrors.containsKey(vowelRepStr)) {
                res[i] = wordlist[vowelErrors.get(vowelRepStr)];
                continue;
            }

            res[i] = "";
        }

        return res;
    }
}

/*
First Thought:
class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] result = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            boolean found = false;
            
            for (String word : wordlist)
                if (word.equals(query)) {
                    result[i] = word;
                    found = true;
                    break;
                }
            
            if (!found)
                for (String word : wordlist)
                    if (word.toLowerCase().equals(query.toLowerCase())) {
                        result[i] = word;
                        found = true;
                        break;
                    }
            
            if (!found)
                for (String word : wordlist) {
                    String w = word.toLowerCase();
                    String q = query.toLowerCase();
                    if (w.length() != q.length()) continue;
                    boolean match = true;
                    for (int k = 0; k < w.length(); k++) {
                        char wc = w.charAt(k);
                        char qc = q.charAt(k);

                        boolean wcVowel = (wc == 'a' || wc == 'e' || wc == 'i' || wc == 'o' || wc == 'u');
                        boolean qcVowel = (qc == 'a' || qc == 'e' || qc == 'i' || qc == 'o' || qc == 'u');
                        if (wcVowel && qcVowel) continue;
                        if (wc != qc) { match = false; break; }
                    }
                    if (match) {
                        result[i] = word;
                        found = true;
                        break;
                    }
                }
            
            if (!found) result[i] = "";
        }
        return result;
    }
}
*/