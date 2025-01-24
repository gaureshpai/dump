#include <stdlib.h>
#include <stdio.h>

int *x, q = 0, n;

int can_place(int p){
    int i;
    for (i = 0; i < q; i++){
        if (x[i] == p || x[i] - i == p - q || x[i] + i == p + q){
            return 0;
        }
    }
    return 1;
}

void print_board(){
    int i, j;
    printf("Solution : \n");
    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            if (x[i] == j){
                printf("Q ");
            }
            else{
                printf("_ ");
            }
        }
        printf("\n");
    }
}

void n_queens(){
    int p;
    for (p = 0; p < n; p++){
        if (can_place(p)){
            x[q] = p;
            q++;
            if (q == n){
                print_board();
            }
            else{
                n_queens();
            }
            q--;
        }
    }
}

int main(){
    printf("Board size : ");
    scanf("%d", &n);
    x = calloc(n, sizeof(int));
    n_queens();
    return 0;
}
