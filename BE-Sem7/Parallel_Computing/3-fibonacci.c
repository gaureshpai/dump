#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int fib(int n) {
    int x, y;
    if (n <= 1) return n;

    #pragma omp task shared(x)
    x = fib(n - 1);

    #pragma omp task shared(y)
    y = fib(n - 2);

    #pragma omp taskwait
    return x + y;
}

int main() {
    int n;
    printf("Enter the number of Fibonacci numbers to calculate: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("Please enter a positive integer.\n");
        return 0;
    }

    printf("First %d Fibonacci numbers using OpenMP tasks:\n", n);

    double start = omp_get_wtime();

    #pragma omp parallel
    {
        #pragma omp single
        {
            for (int i = 0; i < n; i++) {
                #pragma omp task firstprivate(i)
                {
                    int result = fib(i);
                    #pragma omp critical
                    printf("Fib(%d) = %d\n", i, result);
                }
            }
        }
    }

    double end = omp_get_wtime();
    printf("Execution time: %.6f seconds\n", end - start);

    return 0;
}