#include<stdio.h>

int main(){
    int num,a[25];
    printf("Enter the number of elements:");
    scanf("%d",&num);
    printf("Enter the elements into the array:");

    for(int i=0;i<num;i++){
        scanf("%d",&a[i]);
    }
    printf("The entered elements of the array are:\n");

    for(int i=0;i<num;i++){
        printf("%d\t",a[i]);
    }

    printf("\n");
}