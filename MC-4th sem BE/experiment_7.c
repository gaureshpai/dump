#include <stdio.h>

// Function to swap two integers
void swap(int *xp, int *yp) {
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}

// Function to perform bubble sort in ascending order
void bubbleSortAscending(int arr[], int n) {
    int i, j;
    for (i = 0; i < n-1; i++) {
        for (j = 0; j < n-i-1; j++) {
            if (arr[j] > arr[j+1]) {
                swap(&arr[j], &arr[j+1]);
            }
        }
    }
}

// Function to perform bubble sort in descending order
void bubbleSortDescending(int arr[], int n) {
    int i, j;
    for (i = 0; i < n-1; i++) {
        for (j = 0; j < n-i-1; j++) {
            if (arr[j] < arr[j+1]) {
                swap(&arr[j], &arr[j+1]);
            }
        }
    }
}

// Function to print the array
void printArray(int arr[], int size) {
    int i;
    for (i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    // Example array
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    int n = sizeof(arr)/sizeof(arr[0]);
    
    // Sort array in ascending order
    printf("Original array: \n");
    printArray(arr, n);
    
    printf("\nSorting in ascending order...\n");
    bubbleSortAscending(arr, n);
    printf("Sorted array in ascending order: \n");
    printArray(arr, n);

    // Sort array in descending order
    printf("\nSorting in descending order...\n");
    bubbleSortDescending(arr, n);
    printf("Sorted array in descending order: \n");
    printArray(arr, n);

    while(1); // Infinite loop to keep the program running
}
