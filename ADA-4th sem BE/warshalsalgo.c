#include<stdlib.h>
#include<stdio.h>

int **a, n;

void warshall() {
    int i, j, k;
    for(k = 0; k < n; k++) {
        for(i = 0; i < n; i++) {
            for(j = 0; j < n; j++) {
                a[i][j] = a[i][j] || (a[i][k] && a[k][j]);
            }
        }
    }
}

void print_array() {
    int i, j;
    for(i = 0; i < n; i++) {
        for(j = 0; j < n; j++) {
            printf("%d\t", a[i][j]);
        }
        printf("\n");
    }
}

int main() {
    int i, j;
    printf("Number of vertices: ");
    scanf("%d", &n);
    a = calloc(n, sizeof(int *));
    for(i = 0; i < n; i++) {
        a[i] = calloc(n, sizeof(int));
    }
    printf("Adjacency Matrix:\n");
    for(i = 0; i < n; i++) {
        for(j = 0; j < n; j++) {
            scanf("%d", &a[i][j]);
        }
    }
    printf("Entered Adjacency Matrix:\n");
    print_array();
    warshall();
    printf("Transitive Closure Matrix:\n");
    print_array();
    return 0;
}
