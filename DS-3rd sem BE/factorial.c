#include<stdio.h>
int fact(int n)
{
    if(n==0||n==1){
        return 1;
    }else{
        return n*fact(n-1);
    }
}
int main()
{
    int num;
    printf("Enter a number:");
    scanf("%d",num);
    if(num<0){
        printf("Factorial is not defined for negative numbers.\n");
    }else{
        int result = fact(num);
        printf("Factorial of %d is %d \n",num,result);
    }
}