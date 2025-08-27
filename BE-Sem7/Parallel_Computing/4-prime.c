#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

int is_prime(int num) {
    if (num <= 1) return 0;
    if (num == 2) return 1;
    if (num % 2 == 0) return 0;

    int limit = (int)sqrt(num);
    for (int i = 3; i <= limit; i += 2)
        if (num % i == 0) return 0;
    return 1;
}

int main() {
    int n;

    printf("Enter the upper limit (n): ");
    scanf("%d", &n);

    if (n < 2) {
        printf("There are no prime numbers <= %d\n", n);
        return 0;
    }

    double start_serial = omp_get_wtime();
    int* primes_serial = (int*)malloc((n + 1) * sizeof(int));
    int count_serial = 0;

    for (int i = 2; i <= n; i++)
        if (is_prime(i))
            primes_serial[count_serial++] = i;

    double end_serial = omp_get_wtime();
    double time_serial = end_serial - start_serial;

    double start_parallel = omp_get_wtime();
    int* primes_parallel = (int*)malloc((n + 1) * sizeof(int));
    int count_parallel = 0;

    #pragma omp parallel
    {
        int* local_primes = (int*)malloc((n / omp_get_num_threads() + 1) * sizeof(int));
        int local_count = 0;

        #pragma omp for nowait
        for (int i = 2; i <= n; i++)
            if (is_prime(i))
                local_primes[local_count++] = i;

        #pragma omp critical
        {
            for (int i = 0; i < local_count; i++)
                primes_parallel[count_parallel++] = local_primes[i];
        }

        free(local_primes);
    }

    double end_parallel = omp_get_wtime();
    double time_parallel = end_parallel - start_parallel;

    printf("\nNumber of primes found: %d\n", count_serial);
    printf("Serial execution time  : %.6f seconds\n", time_serial);
    printf("Parallel execution time: %.6f seconds\n", time_parallel);
    printf("Speedup                : %.2fx\n", time_serial / time_parallel);

    free(primes_serial);
    free(primes_parallel);
    return 0;
}