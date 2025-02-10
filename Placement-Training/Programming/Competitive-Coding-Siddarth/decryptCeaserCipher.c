#include<stdio.h>


/**
 * Decrypts a Caesar cipher encoded string by shifting each character back by one position.
 * 
 * This function takes an input string `name` and modifies it in place by decrementing
 * each character's ASCII value by 1, effectively reversing a Caesar cipher with a shift
 * of 1. The decrypted characters are printed to the standard output.
 *
 * @param name The string to be decrypted, expected to be null-terminated.
 */

void decrypt(char name[]){
	int key = -1;
	for(int i=0;name[i]!='\0';i++){
		name[i] = name[i]-1;
		printf("%c",name[i]);
	}
}

int main(){
	char name[] = "SBIVM"; // -1
	decrypt(name);
	return 0;
}