#include<stdio.h>

int main(){
    int numrows,i,j;
    printf("Enter the number of rows:");
    scanf("%d",&numrows);
    
    for(int i=1;i<=numrows;i++){

        for(j=1;j<=numrows-i;j++){
            printf(" ");
        }

        for(j=i-1;j>=1;j--){
            printf("%2d",j);
        }
        printf("\n");
    }
}