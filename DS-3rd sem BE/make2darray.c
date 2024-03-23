#include<stdio.h>
#include<stdlib.h>
#define MALLOC(p,n,type)
p = (type *)malloc(n * sizeof(type));
if(p == NULL){
    printf("Insufficient memory\n");
    exit(0);
}
int **make2darray(int row,int col){
    int **x;
    int i;
    MALLOC(x,rows,int *);
    for(i=0;i<row;i++)
    MALLOC(x[i],col,int);
    return x;
}
void main(){
    printf("Enter the number of rows and columns:");
    scanf("%d%d",&row,&col);
    for(i=0;i<row;i++)
        for(j=0;i<col;j++)
            scanf("%d",&p[i][j]);
    
    for(i=0;i<row;i++){
        for(j=0;i<col;j++)
            printf("%d",&p[i][j]);
        printf("\n");
    }
       
}