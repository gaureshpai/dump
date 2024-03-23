#include <stdio.h>
#include <string.h>

int main() {
    char str1[100], str2[100], result[200];
    int choice;

    printf("Enter the first string: ");
    fgets(str1, sizeof(str1), stdin);
    str1[strcspn(str1, "\n")] = '\0';  // Remove newline character

    printf("Enter the second string: ");
    fgets(str2, sizeof(str2), stdin);
    str2[strcspn(str2, "\n")] = '\0';  // Remove newline character

    printf("\nString Operations Menu:\n");
    printf("1. Concatenate\n");
    printf("2. Compare\n");
    printf("3. Copy\n");
    printf("4. Length\n");
    printf("Enter your choice (1-4): ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            // Concatenate strings
            strcpy(result, str1);
            strcat(result, str2);
            printf("Concatenated String: %s\n", result);
            break;

        case 2:
            // Compare strings
            if (strcmp(str1, str2) == 0)
                printf("Strings are equal.\n");
            else
                printf("Strings are not equal.\n");
            break;

        case 3:
            // Copy string
            strcpy(result, str1);
            printf("Copied String: %s\n", result);
            break;

        case 4:
            // Length of strings
            printf("Length of String 1: %lu\n", strlen(str1));
            printf("Length of String 2: %lu\n", strlen(str2));
            break;

        default:
            printf("Invalid choice.\n");
    }

    return 0;
}
