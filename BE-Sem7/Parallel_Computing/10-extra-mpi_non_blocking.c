#include <stdio.h>
#include <mpi.h>

int main(int argc, char *argv[]) {
    int rank, size;
    int message = 42;
    int received_message = 0;
    double start_time, end_time;
    MPI_Request request;
    MPI_Status status;
    int mpi_err;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    if (size < 2) {
        if (rank == 0) {
            fprintf(stderr, "Requires at least 2 processes\n");
        }
        MPI_Finalize();
        return -1;
    }

    start_time = MPI_Wtime();

    if (rank == 0) {
        printf("Process 0 starting non-blocking send of message %d to process 1\n", message);
        mpi_err = MPI_Isend(&message, 1, MPI_INT, 1, 100, MPI_COMM_WORLD, &request);
        
        if (mpi_err != MPI_SUCCESS) {
            fprintf(stderr, "MPI_Isend failed\n");
        }

        printf("Process 0 is performing computation while message is being sent\n");
        // Simulate work by sleeping or doing calculations
        
        MPI_Wait(&request, &status);
        printf("Process 0 finished sending message\n");

    } else if (rank == 1) {
        mpi_err = MPI_Irecv(&received_message, 1, MPI_INT, 0, 100, MPI_COMM_WORLD, &request);

        if (mpi_err != MPI_SUCCESS) {
            fprintf(stderr, "MPI_Irecv failed\n");
        }
        
        printf("Process 1 is doing other work while waiting for message\n");
        // Simulate work
        
        MPI_Wait(&request, &status);
        printf("Process 1 received message %d from process 0\n", received_message);
    }

    end_time = MPI_Wtime();

    if (rank == 0) {
        printf("Total time including overlapping work: %f seconds\n", end_time - start_time);
    }

    MPI_Finalize();
    return 0;
}