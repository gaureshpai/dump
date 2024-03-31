#include <stdio.h>
int main()
{
    int bt[10] = {0}, at[10] = {0}, tat[10] = {0}, wt[10] = {0}, ct[10] = {0};
    int n, sum = 0;
    float totalTAT = 0, totalWT = 0;
    printf("Enter number of processes ");
    scanf("%d", &n);
    printf("Enter arrival time and burst time for each process\n\n");
    for (int i = 0; i < n; i++)
    {
        printf("Arrival time of process[%d] ", i + 1);
        scanf("%d", &at[i]);
        printf("Burst time of process[%d] ", i + 1);
        scanf("%d", &bt[i]);
        printf("\n");
    }
    for (int j = 0; j < n; j++)
    {
        sum += bt[j];
        ct[j] += sum;
    }
    for (int k = 0; k < n; k++)
    {
        tat[k] = ct[k] - at[k];
        totalTAT += tat[k];
    }
    for (int k = 0; k < n; k++)
    {
        wt[k] = tat[k] - bt[k];
        totalWT += wt[k];
    }
    printf("Solution: \n\n");
    printf("P#\t AT\t BT\t CT\t TAT\t WT\t\n\n");
    for (int i = 0; i < n; i++)
    {
        printf("P%d\t %d\t %d\t %d\t %d\t %d\n", i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
    }
    printf("\n\nAverage Turnaround Time = %f\n", totalTAT / n);
    printf("Average WT = %f\n\n", totalWT / n);
    return 0;
}
