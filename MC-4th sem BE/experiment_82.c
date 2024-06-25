#include <stdio.h>

// Function to calculate factorial non-recursively
unsigned long long factorialNonRecursive(int n) {
    unsigned long long result = 1;
    for (int i = 1; i <= n; ++i) {
        result *= i;
    }
    return result;
}

int main(void) {
    int number = 5;  // Example number to calculate factorial for
    unsigned long long fact = factorialNonRecursive(number);
    
    printf("Non-recursive: Factorial of %d is %llu\n", number, fact);

    while(1); // Infinite loop to keep the program running
}
