#include<stdio.h>

int main(){
    int n,*ptr,sum=0;

    printf("Enter the number of digits:");
    scanf("%d",&n);

    ptr = (int * )malloc(n*sizeof(int));

    for(int i=0;i<n;i++){
        sum = ptr[i]+sum;
    }

    printf("Sum = %d\n",sum);
    
    return 1;
}