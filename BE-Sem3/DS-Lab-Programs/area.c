#include<stdio.h>

int main(){
    int l,b;
    printf("Enter the length and breadth of the rectangle:");
    scanf("%d %d",&l , &b);
    int area = l*b;
    int peri =2*(l+b);
    printf("Area = %d\n",area);
    printf("Perimeter = %d\n",peri);
}