/*	Question:

You are given two non-negative integers, `n1` and `n2`. Write a function `function1(int n1, int n2)` that performs the following operations:

1. If either `n1` or `n2` is negative, return `0`.
2. If `n1 < n2`, iterate from `n1` to `n2` (inclusive):
   - Add even numbers to a sum (`x`).
   - Subtract odd numbers from the sum (`x`).
   - Return the final value of `x`.
3. If `n1 >= n2`, return `-1`. */
#include<stdio.h>


/*
 * This function takes two non-negative integers, n1 and n2, as input.
 * 
 * Returns:
 * - If either n1 or n2 is negative, returns 0.
 * - If n1 < n2, iterates from n1 to n2 (inclusive), adding even numbers
 *   and subtracting odd numbers from a sum variable x, then returns x.
 * - If n1 >= n2, returns -1.
 */

int function1(int n1, int n2){
	if(n1<0 || n2<0)
		return 0;
	if(n1<n2){
		int x;
		for(int i=n1;i<=n2;i++){
			if(i%2 == 0)
				x+=i;
			else
				x-=i;	
		}
		return x;
	}else if (n1>=n2){
		return -1;	
	}
}

int main(){
	printf("%d",function1(2,10));
}
