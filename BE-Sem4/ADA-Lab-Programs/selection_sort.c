#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int *a,n;

void swap(int *x, int *y){
    int temp = *x;
    *x = *y;
    *y = temp;
}

void selectionsort(int a[], int n){
    int i, j, min;
    for(i=0; i<n-1; i++){
        min = i;
        for(j=i+1; j<n; j++)
            if(a[j] < a[min])
                min = j;
        swap(&a[min], &a[i]);
    }
}

void print_array(){
    int i;
    for(i=0; i<n; i++){
        printf("%d ", a[i]);
    }
    printf("\n");
}

int main(){
    int i;
    float start, end, complexity;
    printf("Enter the value of n: ");
    scanf("%d",&n);
    a = calloc(n, sizeof(int));
    for(i=0; i<n; i++)
        a[i] = rand() % 20;
    printf("The array is:\n");
    print_array();
    start = clock();
    selectionsort(a,n);
    printf("The sorted array is:\n");
    print_array();
    complexity = (end - start)/CLOCKS_PER_SEC;
    printf("Complexity: %f\n",complexity);
    return 0;
}