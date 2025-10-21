#include <stdio.h>
#include <mpi.h>

int main(int argc, char *argv[]) {
    int rank, size;
    int msg_out, msg_in;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    
    msg_out = 100 + rank;

    if (size != 2) {
        if (rank == 0) {
            printf("Requires exactly 2 processes.\n");
        }
        MPI_Finalize();
        return 1;
    }

    if (rank == 0) {
        // Process 0 receives from Process 1 first, then sends.
        MPI_Recv(&msg_in, 1, MPI_INT, 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        MPI_Send(&msg_out, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
    } else { // rank == 1
        // Process 1 sends to Process 0 first, then receives.
        // This avoids the deadlock because Process 0's Recv can complete.
        MPI_Send(&msg_out, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
        MPI_Recv(&msg_in, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
    }

    printf("Process %d received %d\n", rank, msg_in);

    MPI_Finalize();
    return 0;
}