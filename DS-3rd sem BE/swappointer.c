#include<stdio.h>

void swap(int *x,int *y){
    *x = *x+*y;
    *y = *x-*y;
    *x = *x-*y;
}

int main(){
    int x,y;

    printf("Enter the two numbers:");
    scanf("%d %d",&x,&y);

    printf("Before swapping\n x = %d \t y = %d\n",x,y);

    swap(&x,&y);

    printf("After swapping\n x = %d \t y = %d\n",x,y);

    return 0;
}