/**
 * @brief Program to calculate the sum of the numerical values in a given string.
 *
 * It takes a string of numbers, iterates over it, and adds the numerical value of each
 * character to a variable. The numerical value is obtained by subtracting 48 from the
 * ASCII value of the character (as the ASCII value of '0' is 48). The result is then
 * printed out.
 *
 * @return 0 on success
 */

#include<stdio.h>

int main(){
	char number[] = "876543";
	int x=0;
	for(int i=0; number[i] !='\0';i++){
		x =x+ number[i]-48;
	}
	printf("%d",x);
}