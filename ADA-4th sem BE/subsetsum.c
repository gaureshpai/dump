#include <stdlib.h>
#include <stdio.h>

int *w, n, m, *sel;

void print_soln(){
    int i;
    printf("Soln : ");
    for (i = 0; i < n; i++){
        if (sel[i] == 1){
            printf("%d ", w[i]);
        }
    }
    printf("\n");
}

void subset_sum(int ssf, int index){
    if (ssf + w[index] <= m){
        sel[index] = 1;
        if (ssf + w[index] == m){
            print_soln();
        }
        else{
            subset_sum(ssf + w[index], index + 1);
        }
        sel[index] = 0;
        subset_sum(ssf, index + 1);
    }
}

int main(){
    int i;
    printf("Number of elements : ");
    scanf("%d", &n);
    w = calloc(n, sizeof(int));
    sel = calloc(n, sizeof(int));
    printf("The elements : \n");
    for (i = 0; i < n; i++){
        scanf("%d", &w[i]);
    }
    printf("Desired Sum : ");
    scanf("%d", &m);
    subset_sum(0, 0);
    return 0;
}
