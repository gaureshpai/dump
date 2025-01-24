#include<stdio.h>
#include<stdlib.h>

int *p, *w, n, m, *sel;

int max(int a, int b){
    return a > b ? a : b;
}

void knapsack(){
    int i, j, v[n+1][m+1];
    for(i=0;i<=n;i++){
        for(j=0;j<=m;j++){
            if(i==0||j==0){
                v[i][j] = 0;
            }
            else if(j < w[i]){
                v[i][j] = v[i-1][j];
            }
            else{
                v[i][j] = max(v[i-1][j], p[i]+v[i-1][j-w[i]]);
            }
        }
    }
    printf("The table is:\n");
    for(i=0;i<=n;i++){
        for(j=0;j<=m;j++){
            printf("%d\t",v[i][j]);
        }
        printf("\n");
    }
    printf("The maximum profit:\n",v[n][m]);
    j = m;
    for(i=n; i>=0;i--){
        if(v[i][j] != v[i-1][j]){
            sel[i] = 1;
            j = j - w[i];
        }
    }
    printf("The selected items are:\n");
    for(i=0;i<n;i++){
        if(sel[i] == 1){
            printf("%d]t",i);
        }
    }
    printf("\n");
}

int main(){
    int i;
    printf("Number of items:");
    scanf("%d",&n);
    printf("Knapsack capacity:");
    scanf("%d", &m);
    p = calloc(n, sizeof(int));
    w = calloc(n, sizeof(int));
    sel = calloc(n, sizeof(int));
    printf("Profit array:\n");
    for(i=1;i<=n;i++){
        scanf("%d",&p[i]);
    }
    printf("Weight array:\n");
    for(i=1;i<=n;i++){
        scanf("%d",&w[i]);
    }
    knapsack();
    return 0;
}