#include <stdio.h>
#include <stdlib.h>

int* createAndInitialize1DArray(int size) {
    int* arr = (int*)malloc(size * sizeof(int));
    if (arr == NULL) {
        printf("Memory allocation failed for 1D array\n");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < size; i++) {
        arr[i] = i + 1;
    }

    return arr;
}

int** createAndInitialize2DArray(int rows, int cols) {
    int** arr = (int**)malloc(rows * sizeof(int*));
    if (arr == NULL) {
        printf("Memory allocation failed for 2D array\n");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < rows; i++) {
        arr[i] = (int*)malloc(cols * sizeof(int));
        if (arr[i] == NULL) {
            printf("Memory allocation failed for 2D array\n");
            exit(EXIT_FAILURE);
        }

        // Initialize array elements
        for (int j = 0; j < cols; j++) {
            arr[i][j] = i * cols + j + 1;
        }
    }

    return arr;
}

// Function to print 1D array
void print1DArray(int* arr, int size) {
    printf("1D Array: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Function to print 2D array
void print2DArray(int** arr, int rows, int cols) {
    printf("2D Array:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("%d ", arr[i][j]);
        }
        printf("\n");
    }
}

// Function to free memory allocated for 1D array
void free1DArray(int* arr) {
    free(arr);
}

// Function to free memory allocated for 2D array
void free2DArray(int** arr, int rows) {
    for (int i = 0; i < rows; i++) {
        free(arr[i]);
    }
    free(arr);
}

int main() {
    // Example for 1D array
    int size1D = 5;
    int* arr1D = createAndInitialize1DArray(size1D);
    print1DArray(arr1D, size1D);
    free1DArray(arr1D);

    // Example for 2D array
    int rows2D = 3;
    int cols2D = 4;
    int** arr2D = createAndInitialize2DArray(rows2D, cols2D);
    print2DArray(arr2D, rows2D, cols2D);
    free2DArray(arr2D, rows2D);

    return 0;
}
