#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
int *a, n;

void merge(int l, int r, int m)
{
    int i, j, k, *b;
    i = l;
    j = m + 1;
    k = l;
    b = calloc(n, sizeof(int));
    while (i <= m && j <= r)
    {
        if (a[i] < a[j])
        {
            b[k++] = a[i++];
        }
        else
        {
            b[k++] = a[j++];
        }
    }
    while (i <= m)
    {
        b[k++] = a[i++];
    }
    while (j <= m)
    {
        b[k++] = a[j++];
    }
    for (i = l; i < k; i++)
    {
        a[i] = b[i];
    }
}

void merge_sort(int l, int r)
{
    int m;
    if (l < r)
    {
        m = (l + r) / 2;
#pragma opm task
        {
            merge_sort(l, m);
        }
#pragma opm task
        {
            merge_sort(m + 1, r);
        }
        merge(l, r, m);
    }
}

void print_array()
{
    int i;
    for (i = 0; i < n; i++)
    {
        printf("%d\t", a[i]);
    }
    printf("\n");
}

int main()
{
    int i;
    double start, end;

    printf("Number of items: ");
    scanf("%d", &n);

    a = calloc(n, sizeof(int));

    for (i = 0; i < n; i++)
    {
        a[i] = rand() % 20;
    }

    printf("The Unsorted array\n");
    print_array();

    omp_set_num_threads(2);
    start = omp_get_wtime();
    merge_sort(0, n - 1);
    end = omp_get_wtime();

    printf("The Sorted array\n");
    print_array();

    printf("time elapsed = %lf\n", end - start);

    return 0;
}
