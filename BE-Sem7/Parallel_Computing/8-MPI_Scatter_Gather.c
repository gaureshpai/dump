#include <stdio.h>
#include <mpi.h>

int main(int argc, char** argv) {
    int rank, size, send_data[4] = {10, 20, 30, 40}, recv_data;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    MPI_Scatter(send_data, 1, MPI_INT, &recv_data, 1, MPI_INT, 0, MPI_COMM_WORLD);
    printf("Process %d received: %d\n", rank, recv_data);

    recv_data += 1;

    MPI_Gather(&recv_data, 1, MPI_INT, send_data, 1, MPI_INT, 0, MPI_COMM_WORLD);

    if (rank == 0) {
        printf("Gathered data: ");
        for (int i = 0; i < size; i++)
            printf("%d ", send_data[i]);
        printf("\n");
    }

    MPI_Finalize();
    return 0;
}