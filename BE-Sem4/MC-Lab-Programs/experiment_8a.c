#include <stdio.h>

// Function to calculate factorial recursively
unsigned long long factorialRecursive(int n) {
    if (n == 0 || n == 1) {
        return 1;
    } else {
        return n * factorialRecursive(n - 1);
    }
}

int main(void) {
    int number = 5;  // Example number to calculate factorial for
    unsigned long long fact = factorialRecursive(number);
    
    printf("Recursive: Factorial of %d is %llu\n", number, fact);

    while(1); // Infinite loop to keep the program running
}
