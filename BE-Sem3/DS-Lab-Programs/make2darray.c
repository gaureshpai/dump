#include <stdio.h>
#include <stdlib.h>

#define MALLOC(p, n, type)                \
    p = (type *)malloc(n * sizeof(type)); \
    if (p == NULL){                       \
        printf("Insufficient memory\n");  \
        exit(0);                          \
    }

int **make2darray(int row, int col){
    int **x;
    int i;
    MALLOC(x, row, int *);

    for (i = 0; i < row; i++)
        MALLOC(x[i], col, int);
    return x;
}

int main(){
    int **p, row, col, i, j;

    printf("Enter the number of rows and columns: ");
    scanf("%d%d", &row, &col);

    p = make2darray(row, col);

    printf("Enter the elements of the 2D array:\n");
    for (i = 0; i < row; i++)
        for (j = 0; j < col; j++)
            scanf("%d", &p[i][j]);

    printf("The 2D array you entered is:\n");
    for (i = 0; i < row; i++){
        for (j = 0; j < col; j++)
            printf("%d ", p[i][j]);
        printf("\n");
    }

    for (i = 0; i < row; i++)
        free(p[i]);
    free(p);

    return 0;
}
