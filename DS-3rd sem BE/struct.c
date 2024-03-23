#include<stdio.h>
typedef struct poly
{
    float coef;
    int exp;
};
poly A[5],B[5],S[10];
int main(){
    int m,n;
    printf("Enter m and n:");
    scanf("%d%d",&m,&n);
    for(i=0;i<m;i++)
        scanf("%f%d",&A[i].coef,&A[i].exp);
    for(i=0;i<n;i++)
        scanf("%f%d",&B[i].coef,&B[i].exp);
    for(i=0;i<m;i++)
        S[i].coef = A[i].coef+B[i].coef;
    for(i=0;i<n;i++)
        printf("%f%d",B[i].coef,B[i].exp);
}