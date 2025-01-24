#include<stdio.h>
#include<stdlib.h>

int **cost, n, *dist, *visited;

void dijkstras(int src){
    int v, u, min, count;
    for( v=0; v<n ; v++){
        dist[v] = cost[src][v];
    }
    visited[src] = 1;
    for ( count = n-1; count >=1; count-- ){
        min = 999;
        for ( v = 0; v < count; v++){
            if (visited[v] != 1 && dist[v] < min){
                min = dist[v];
                u = v;
            }
        }
        
        visited[u] = 1;
        for ( v=0; v<n; v++ ){
            if( visited[v] != 1 && dist[v] > dist[u] +cost[u][v] ){
                dist[v] = dist[u] + cost[u][v];
            }
        }
    }
}

int main(){
    int i, j, src;
    printf("Size of the graph");
    scanf("%d",&n);
    cost = calloc( n, sizeof(int *) );
    for ( i = 0; i < n; i++){
        cost[i] = calloc( n, sizeof(int) );   
    }
    visited = calloc( n, sizeof(int) );
    dist = calloc( n, sizeof(int) );
    printf("Enter the elements:\n");
    for(i = 0; i < n; i++){
        for(j = 0 ; j < n ; j++){
            scanf("%d",&cost[i][j]);
        }
    }
    printf("Source vertex: ");
    scanf("%d",&src);
    dijkstras(src);
    printf("Vertex\tDistance From Source\n");
    for(i=0;i<n;i++){
        printf("%d\t%d\n",i,dist[i]);
    }
    return 0;
}