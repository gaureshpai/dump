#include <mpi.h>
#include <stdio.h>

int main(int argc, char** argv) {
    int rank, size;
    int value;
    int sum, prod, max, min;
    int sum_all, prod_all, max_all, min_all;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    value = rank + 1;
    printf("Process %d has value %d\n", rank, value);

    MPI_Reduce(&value, &sum,  1, MPI_INT, MPI_SUM,  0, MPI_COMM_WORLD);
    MPI_Reduce(&value, &prod, 1, MPI_INT, MPI_PROD, 0, MPI_COMM_WORLD);
    MPI_Reduce(&value, &max,  1, MPI_INT, MPI_MAX,  0, MPI_COMM_WORLD);
    MPI_Reduce(&value, &min,  1, MPI_INT, MPI_MIN,  0, MPI_COMM_WORLD);

    if (rank == 0) {
        printf("\n[Using MPI_Reduce at Root Process]\n");
        printf("Sum   = %d\n", sum);
        printf("Prod  = %d\n", prod);
        printf("Max   = %d\n", max);
        printf("Min   = %d\n", min);
    }

    MPI_Allreduce(&value, &sum_all,  1, MPI_INT, MPI_SUM,  MPI_COMM_WORLD);
    MPI_Allreduce(&value, &prod_all, 1, MPI_INT, MPI_PROD, MPI_COMM_WORLD);
    MPI_Allreduce(&value, &max_all,  1, MPI_INT, MPI_MAX,  MPI_COMM_WORLD);
    MPI_Allreduce(&value, &min_all,  1, MPI_INT, MPI_MIN,  MPI_COMM_WORLD);

    printf("\n[Process %d] MPI_Allreduce Results:\n", rank);
    printf("  Sum  = %d\n", sum_all);
    printf("  Prod = %d\n", prod_all);
    printf("  Max  = %d\n", max_all);
    printf("  Min  = %d\n", min_all);

    MPI_Finalize();
    return 0;
}