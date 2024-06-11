#include<stdio.h>
#include<stdlib.h>

int **a, n, *visited, *res, *s, top = -1, k;

void dfs(int u){
    int v;
    s[ ++top ] = u;
    visited[u] = 1;
    for(v = 0; v < n; v++){
        if(visited[v] != 1 && a[u][v] == 1){
            dfs(v);
        }
    }
    res[k--] = s[top--];
}

void topological_sort(){
    int v;
    for(v = 0; v < n; v++){
        if(visited[v] != 1){
            dfs(v);
        }
    }
    printf("The topological ordering is:\n");
    for(v = 0; v < n; v++){
        printf("%d\n", res[v]);
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
    visited = calloc(n, sizeof(int));
    res = calloc(n, sizeof(int));
    s = calloc(n, sizeof(int));
    printf("The elements:\n");
    for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            scanf("%d",&a[i][j]);
        }
    }
    k = n - 1;
    topological_sort();
    return 0;
}