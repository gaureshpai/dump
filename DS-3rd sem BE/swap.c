#include<stdio.h>

int main(){
    int a,b,temp;

    printf("Enter the two numbers:");
    scanf("%d %d",&a,&b);

    printf("Before swapping\n a=%d \t b=%d\n",a,b);
    
    temp = a;
    a = b;
    b = temp;

    printf("After swapping\n a=%d \t b=%d\n",a,b);

    return 0;
}