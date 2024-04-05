#include<stdio.h>
 
int add(int x,int y)
{
    int Sum = x+y;
    return Sum;
}

int main(){
    int x,y;
    printf("Enter numbers: ");
    scanf("%d %d",&x,&y);
    int sum = add(x,y);
    printf("Sum = %d",sum);
}