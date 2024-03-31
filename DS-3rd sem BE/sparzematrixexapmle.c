#include <stdio.h>
#include <stdlib.h>

struct Element
{
    int row;
    int col;
    int value;
};

struct SparseMatrix
{
    int rows;
    int cols;
    int numElements;
    struct Element *elements;
};

struct SparseMatrix createSparseMatrix()
{
    struct SparseMatrix matrix;

    printf("Enter the number of rows and columns: ");
    scanf("%d %d", &matrix.rows, &matrix.cols);

    printf("Enter the number of non-zero elements: ");
    scanf("%d", &matrix.numElements);

    matrix.elements = (struct Element *)malloc(matrix.numElements * sizeof(struct Element));

    printf("Enter non-zero elements (row, column, value):\n");
    for (int i = 0; i < matrix.numElements; i++)
    {
        scanf("%d %d %d", &matrix.elements[i].row, &matrix.elements[i].col, &matrix.elements[i].value);
    }

    return matrix;
}

void displaySparseMatrix(struct SparseMatrix matrix)
{
    printf("Sparse Matrix:\n");
    int k = 0;

    for (int i = 0; i < matrix.rows; i++)
    {
        for (int j = 0; j < matrix.cols; j++)
        {
            if (k < matrix.numElements && matrix.elements[k].row == i && matrix.elements[k].col == j)
            {
                printf("%d ", matrix.elements[k++].value);
            }
            else
            {
                printf("0 ");
            }
        }
        printf("\n");
    }
}

struct SparseMatrix addSparseMatrices(struct SparseMatrix matrix1, struct SparseMatrix matrix2)
{
    if (matrix1.rows != matrix2.rows || matrix1.cols != matrix2.cols)
    {
        printf("Matrices cannot be added. Dimensions mismatch.\n");
        exit(EXIT_FAILURE);
    }

    struct SparseMatrix result;
    result.rows = matrix1.rows;
    result.cols = matrix1.cols;
    result.numElements = 0;

    result.elements = (struct Element *)malloc((matrix1.numElements + matrix2.numElements) * sizeof(struct Element));

    int i = 0, j = 0, k = 0;

    while (i < matrix1.numElements && j < matrix2.numElements)
    {
        if (matrix1.elements[i].row < matrix2.elements[j].row ||
            (matrix1.elements[i].row == matrix2.elements[j].row && matrix1.elements[i].col < matrix2.elements[j].col))
        {
            result.elements[k++] = matrix1.elements[i++];
        }
        else if (matrix1.elements[i].row > matrix2.elements[j].row ||
                 (matrix1.elements[i].row == matrix2.elements[j].row && matrix1.elements[i].col > matrix2.elements[j].col))
        {
            result.elements[k++] = matrix2.elements[j++];
        }
        else
        {
            result.elements[k].row = matrix1.elements[i].row;
            result.elements[k].col = matrix1.elements[i].col;
            result.elements[k++].value = matrix1.elements[i++].value + matrix2.elements[j++].value;
        }
    }

    while (i < matrix1.numElements)
    {
        result.elements[k++] = matrix1.elements[i++];
    }

    while (j < matrix2.numElements)
    {
        result.elements[k++] = matrix2.elements[j++];
    }

    result.numElements = k;

    return result;
}

struct SparseMatrix transposeSparseMatrix(struct SparseMatrix matrix)
{
    struct SparseMatrix transposedMatrix;
    transposedMatrix.rows = matrix.cols;
    transposedMatrix.cols = matrix.rows;
    transposedMatrix.numElements = matrix.numElements;

    transposedMatrix.elements = (struct Element *)malloc(matrix.numElements * sizeof(struct Element));

    for (int i = 0; i < matrix.numElements; i++)
    {
        transposedMatrix.elements[i].row = matrix.elements[i].col;
        transposedMatrix.elements[i].col = matrix.elements[i].row;
        transposedMatrix.elements[i].value = matrix.elements[i].value;
    }

    return transposedMatrix;
}

struct SparseMatrix multiplySparseMatrices(struct SparseMatrix matrix1, struct SparseMatrix matrix2)
{
    if (matrix1.cols != matrix2.rows)
    {
        printf("Matrices cannot be multiplied. Invalid dimensions.\n");
        exit(EXIT_FAILURE);
    }

    struct SparseMatrix result;
    result.rows = matrix1.rows;
    result.cols = matrix2.cols;
    result.numElements = 0;

    result.elements = (struct Element *)malloc((matrix1.rows * matrix2.cols) * sizeof(struct Element));

    int k = 0;

    for (int i = 0; i < matrix1.rows; i++)
    {
        for (int j = 0; j < matrix2.cols; j++)
        {
            int sum = 0;

            for (int l = 0; l < matrix1.cols; l++)
            {
                int value1 = 0, value2 = 0;

                for (int m = 0; m < matrix1.numElements; m++)
                {
                    if (matrix1.elements[m].row == i && matrix1.elements[m].col == l)
                    {
                        value1 = matrix1.elements[m].value;
                        break;
                    }
                }

                for (int m = 0; m < matrix2.numElements; m++)
                {
                    if (matrix2.elements[m].row == l && matrix2.elements[m].col == j)
                    {
                        value2 = matrix2.elements[m].value;
                        break;
                    }
                }

                sum += value1 * value2;
            }

            if (sum != 0)
            {
                result.elements[k].row = i;
                result.elements[k].col = j;
                result.elements[k++].value = sum;
            }
        }
    }

    result.numElements = k;

    return result;
}

int main()
{
    struct SparseMatrix matrix1 = createSparseMatrix();
    struct SparseMatrix matrix2 = createSparseMatrix();

    printf("\nMatrix 1:\n");
    displaySparseMatrix(matrix1);

    printf("\nMatrix 2:\n");
    displaySparseMatrix(matrix2);

    struct SparseMatrix sumMatrix = addSparseMatrices(matrix1, matrix2);
    printf("\nSum of Matrices:\n");
    displaySparseMatrix(sumMatrix);

    struct SparseMatrix transposedMatrix1 = transposeSparseMatrix(matrix1);
    printf("\nTranspose of Matrix 1:\n");
    displaySparseMatrix(transposedMatrix1);

    struct SparseMatrix productMatrix = multiplySparseMatrices(matrix1, matrix2);
    printf("\nProduct of Matrices:\n");
    displaySparseMatrix(productMatrix);

    free(matrix1.elements);
    free(matrix2.elements);
    free(sumMatrix.elements);
    free(transposedMatrix1.elements);
    free(productMatrix.elements);

    return 0;
}
