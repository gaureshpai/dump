#include<stdio.h>

int main(){
    char dest;
    float fare;

    printf("Choose a destination:");
    printf("A-City Centre\n");
    printf("B-Suburb A\n");
    printf("C-Suburb B\n");
    printf("D-Out skirts\n");
    printf("Enter destination");
    scanf("%c",&dest);

    switch (dest)
    {
    case 'A': fare=10.0;
                break;
    case 'B': fare=20.0;
                break;
    case 'C': fare=30.0;
                break;
    case 'D': fare=25.0;
                break;
    default: printf("Invalid destination.\n");
                break;
    }
    
    printf("The fare to the selected destination is Rs %.2f .\n",fare);
}