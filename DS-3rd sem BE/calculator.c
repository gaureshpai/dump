#include<stdio.h>

int main(){
    int a,b,res;
    char op;

    printf("Enter the equation: ");
    scanf("%d %c %d",&a,&op,&b);

    switch(op){
        case '+':res=a+b;
                 break;
        case '-':res=a-b;
                 break;
        case '*':res=a*b;
                 break;
        case '/':res=a/b;
                 break;
        case '%':res=a%b;
                 break;
        default:printf("Error!");
                break;
    }
    printf("Solution: %d\n",res);
}