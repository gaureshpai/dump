#include <mpi.h>
#include <stdio.h>

int main(int argc, char** argv) {
    int rank, size;
    int msg_send = 100, msg_recv;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    if (size < 2) {
        if (rank == 0)
            printf("Run with at least 2 processes.\n");
        MPI_Finalize();
        return 0;
    }

    if (rank == 0) {
        printf("Process 0 sending to Process 1...\n");
        MPI_Send(&msg_send, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
        MPI_Recv(&msg_recv, 1, MPI_INT, 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        printf("Process 0 received from Process 1: %d\n", msg_recv);
    } else if (rank == 1) {
        printf("Process 1 sending to Process 0...\n");
        MPI_Send(&msg_send, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
        MPI_Recv(&msg_recv, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        printf("Process 1 received from Process 0: %d\n", msg_recv);
    }

    MPI_Finalize();
    return 0;
}
