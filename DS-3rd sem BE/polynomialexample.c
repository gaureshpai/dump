#include <stdio.h>
struct Term
{
    int coefficient;
    int exponent;
};

void inputPolynomial(struct Term poly[], int degree)
{
    for (int i = 0; i <= degree; i++)
    {
        printf("Enter coefficient for x^%d: ", degree - i);
        scanf("%d", &poly[i].coefficient);
        poly[i].exponent = degree - i;
    }
}

void displayPolynomial(struct Term poly[], int degree)
{
    for (int i = 0; i <= degree; i++)
    {
        printf("%dx^%d", poly[i].coefficient, poly[i].exponent);
        if (i < degree)
        {
            printf(" + ");
        }
    }
    printf("\n");
}

void addPolynomials(struct Term poly1[], struct Term poly2[], int degree1, int degree2, struct Term result[])
{
    int i = 0, j = 0, k = 0;

    while (i <= degree1 && j <= degree2)
    {
        if (poly1[i].exponent > poly2[j].exponent)
        {
            result[k++] = poly1[i++];
        }
        else if (poly1[i].exponent < poly2[j].exponent)
        {
            result[k++] = poly2[j++];
        }
        else
        {
            result[k].exponent = poly1[i].exponent;
            result[k++].coefficient = poly1[i++].coefficient + poly2[j++].coefficient;
        }
    }

    while (i <= degree1)
    {
        result[k++] = poly1[i++];
    }

    while (j <= degree2)
    {
        result[k++] = poly2[j++];
    }
}

int main()
{
    int degree1, degree2;

    printf("Enter the degree of the first polynomial: ");
    scanf("%d", &degree1);

    struct Term poly1[degree1 + 1];
    printf("Enter coefficients for the first polynomial:\n");
    inputPolynomial(poly1, degree1);

    printf("\nEnter the degree of the second polynomial: ");
    scanf("%d", &degree2);

    struct Term poly2[degree2 + 1];
    printf("Enter coefficients for the second polynomial:\n");
    inputPolynomial(poly2, degree2);

    int maxDegree = (degree1 > degree2) ? degree1 : degree2;
    struct Term result[maxDegree + 1];

    addPolynomials(poly1, poly2, degree1, degree2, result);

    printf("\nFirst Polynomial: ");
    displayPolynomial(poly1, degree1);

    printf("\nSecond Polynomial: ");
    displayPolynomial(poly2, degree2);

    printf("\nSum of Polynomials: ");
    displayPolynomial(result, maxDegree);

    return 0;
}
