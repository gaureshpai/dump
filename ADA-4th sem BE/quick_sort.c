#include <stdlib.h>
#include <stdio.h>
#include <time.h>

int *a, n;

int partition(int l, int r){
    int i, j, p, temp;
    i = l;
    j = r + 1;
    p = a[l];
    while (i <= j){
        do{
            i++;
        } while (p >= a[i]);
        do{
            j--;
        } while (p < a[j]);
        if (i < j){
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    temp = a[l];
    a[l] = a[j];
    a[j] = temp;
    return j;
}

void q_sort(int l, int r){
    int s;
    if (l < r){
        s = partition(l, r);
        q_sort(l, s - 1);
        q_sort(s + 1, r);
    }
}

void print_array(){
    int i;
    for (i = 0; i < n; i++){
        printf("%d\t", a[i]);
    }
    printf("\n");
}

int main(){
    int i;
    float start, end, complexity;
    printf("Enter the value of n : ");
    scanf("%d", &n);
    a = calloc(n, sizeof(int));
    for (i = 0; i < n; i++){
        a[i] = rand() % 20;
    }
    printf("The array is : \n");
    print_array();
    start = clock();
    q_sort(0, n - 1);
    end = clock();
    printf("The sorted array is : \n");
    print_array();
    complexity = (end - start) / CLOCKS_PER_SEC;
    printf("Start time = %f \n", start);
    printf("End time = %f \n", end);
    printf("Complexity = %f \n", complexity);
    return 0;
}
