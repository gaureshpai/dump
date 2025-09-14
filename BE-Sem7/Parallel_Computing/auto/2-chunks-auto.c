#include<stdio.h>
#include<omp.h>

int main() { 
    int num_iterations = 16; // Fixed value for automation

    printf("Number of iterations: %d\n", num_iterations);
    printf("\nUsing schedule(static,2):\n\n");

    #pragma omp parallel
    { 
        int tid = omp_get_thread_num();

        #pragma omp for schedule(static, 2) 
        for (int i = 0; i < num_iterations; i++)
            printf("Thread %d : Iteration %d\n", tid, i);
    }

    return 0;
}
