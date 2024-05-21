#include<stdlib.h>
#include<stdio.h>

int **a, n, *visited, **sel;

void prim(int src){
        int i, j, count, min, u, v, sum, k;
        count = 0;sum = 0;k = 0;
        visited[src] = 1;
        while(count < n-1){
            min = 999;
            for ( i = 0; i < n; i++){
                for ( j = 0; j < n; j++){
                    if ( visited[i] == 1 && visited[j] != 1 && a[i][j] < min ){
                        min = a[i][j];
                        u = i;
                        v = j;
                    }
                }
            }
            sel[k][0] = u;
            sel[k][1] = v;
            sum += a[u][v];
            visited[v] = 1;
            k++;
            count++;
        }
        if( sum < 999 ){
            printf("Spanning Tree Exists.\n");
            printf("Sum of MST: %d\n",sum);
            printf("Selected Edges: \n");
            for( i = 0; i < n-1; i++){
                printf("%d -- %d\n",sel[i][0],sel[i][1]);
            }
        }else{
            printf("Spanning Tree does not exist.\n");
        }
}

int main(){
    int i, j;
    printf("Number of vertices:");
    scanf("%d",&n);
    a = calloc(n, sizeof(int *));
    for(i = 0; i < n; i++){
        a[i] = calloc(n, sizeof(int));
    }
    visited = calloc(n,sizeof(int));
    sel = calloc(n, sizeof(int *));
    for (i = 0; i < n; i++){
        sel[i] = calloc(2, sizeof(int));
    }
    printf("Cost Adjacency Matrix:\n");
    for ( i = 0; i < n; i++){
        for ( j = 0; j < n; j++){
            scanf("%d",&a[i][j]);
        }
    }   
    prim(0);
    return 0;         
}