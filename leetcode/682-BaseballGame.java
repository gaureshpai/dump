/*
Question:
You are keeping the scores for a baseball game with strange rules. 
At the beginning of the game, you start with an empty record.
You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:
- An integer x: Record a new score of x.
- '+': Record a new score that is the sum of the previous two scores.
- 'D': Record a new score that is the double of the previous score.
- 'C': Invalidate the previous score, removing it from the record.
Return the sum of all the scores on the record after applying all the operations.
The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that all operations are valid.
*/

public class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> temp = new Stack<>();

        for (String ele : operations)
            switch (ele) {
                case "C":
                    temp.pop();
                    break;
                case "D":
                    temp.push(temp.peek() * 2);
                    break;
                case "+":
                    int last = temp.pop();
                    int sLast = temp.peek();
                    temp.push(last);
                    temp.push(last + sLast);
                    break;
                default:
                    temp.push(Integer.parseInt(ele));
            }

        int sum = 0;
        for (int score : temp) sum += score;
        return sum;
    }
}