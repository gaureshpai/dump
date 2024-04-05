#include <stdio.h>

typedef struct poly{
    float coef;
    int exp;
} poly;

poly A[5], B[5], S[10];

int main(){
    int m, n, i;

    printf("Enter m and n: ");
    scanf("%d%d", &m, &n);

    printf("Enter coefficients and exponents for polynomial A:\n");
    for (i = 0; i < m; i++)
        scanf("%f%d", &A[i].coef, &A[i].exp);

    printf("Enter coefficients and exponents for polynomial B:\n");
    for (i = 0; i < n; i++)
        scanf("%f%d", &B[i].coef, &B[i].exp);

    printf("Polynomial B: ");
    for (i = 0; i < n; i++)
        printf("%f%d ", B[i].coef, B[i].exp);
        
    return 0;
}
