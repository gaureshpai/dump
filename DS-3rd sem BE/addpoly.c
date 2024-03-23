#include <stdio.h>
#include <stdlib.h>
 
 
// Structure to represent a term in a polynomial
struct Term {
    int coefficient;
    int exponent;
    struct Term* next;
};

// Function to create a new term
struct Term* createTerm(int coeff, int exp) {
    struct Term* newTerm = (struct Term*)malloc(sizeof(struct Term));
    newTerm->coefficient = coeff;
    newTerm->exponent = exp;
    newTerm->next = NULL;
    return newTerm;
}

// Function to insert a term at the end of a polynomial
void insertTerm(struct Term** poly, int coeff, int exp) {
    struct Term* newTerm = createTerm(coeff, exp);
    if (*poly == NULL) {
        *poly = newTerm;
    } 
    else 
    {
        struct Term* temp = *poly;
        while (temp->next != NULL)
        {
            temp = temp->next;
        }
        temp->next = newTerm;
    }
}

// Function to display a polynomial
void displayPolynomial(struct Term* poly) {
    while (poly != NULL)
    {
        printf("%dx^%d", poly->coefficient, poly->exponent);
        poly = poly->next;
        if (poly != NULL) 
        {
            printf(" + ");
        }
    }
    printf("\n");
}

// Function to add two polynomials
struct Term* addPolynomials(struct Term* poly1, struct Term* poly2) {
    struct Term* result = NULL;

    while (poly1 != NULL || poly2 != NULL) {
        int coeff1 = (poly1 != NULL) ? poly1->coefficient : 0;
        int exp1 = (poly1 != NULL) ? poly1->exponent : 0;

        int coeff2 = (poly2 != NULL) ? poly2->coefficient : 0;
        int exp2 = (poly2 != NULL) ? poly2->exponent : 0;

        int sumCoeff = coeff1 + coeff2;
        insertTerm(&result, sumCoeff, exp1);

        if (poly1 != NULL) {
            poly1 = poly1->next;
        }
        if (poly2 != NULL) {
            poly2 = poly2->next;
        }
    }

    return result;
}

// Function to free memory allocated for a polynomial
void freePolynomial(struct Term* poly) {
    struct Term* temp;
    while (poly != NULL) {
        temp = poly;
        poly = poly->next;
        free(temp);
    }
}

int main() {
    struct Term* poly1 = NULL;
    struct Term* poly2 = NULL;

    // Insert terms into the first polynomial
    insertTerm(&poly1, 3, 2);
    insertTerm(&poly1, -2, 1);
    insertTerm(&poly1, 5, 0);

    // Insert terms into the second polynomial
    insertTerm(&poly2, 4, 1);
    insertTerm(&poly2, 1, 0);

    // Display the polynomials
    printf("Polynomial 1: ");
    displayPolynomial(poly1);

    printf("Polynomial 2: ");
    displayPolynomial(poly2);

    // Add the polynomials
    struct Term* result = addPolynomials(poly1, poly2);

    // Display the result
    printf("Resultant Polynomial: ");
    displayPolynomial(result);

    // Free allocated memory
    freePolynomial(poly1);
    freePolynomial(poly2);
    freePolynomial(result);

    return 0;
}
