#include <stdio.h>
#include <string.h>

int main()
{
    char str[] = "HELLO";
    int len = strlen(str);

    // Print upper half of the pattern
    for (int i = 1; i <= len; i++)
    {
        for (int j = 0; j < i; j++)
        {
            printf("%c", str[j]);
        }
        printf("\n");
    }

    // Print lower half of the pattern
    for (int i = len - 1; i >= 1; i--)
    {
        for (int j = 0; j < i; j++)
        {
            printf("%c", str[j]);
        }
        printf("\n");
    }

    return 0;
}
