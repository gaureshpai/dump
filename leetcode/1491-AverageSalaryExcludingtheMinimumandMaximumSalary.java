/*
Question:
You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
Return the average salary of employees excluding the minimum and maximum salary. 
Answers within 10-5 of the actual answer will be accepted.
*/

class Solution {
    public double average(int[] salary) {
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        double sum = 0;

        for(int sal: salary){
            sum += sal;
            if(sal > min) min = sal;
            if(sal < max) max = sal;
        }
        
        sum = sum - min - max;

        return 1.0 * sum/(salary.length - 2);
    }
}