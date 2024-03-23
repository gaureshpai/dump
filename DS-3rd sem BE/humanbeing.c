#include <stdio.h>

// Define the structure for a human being
struct HumanBeing {
    char name[50];
    int age;
    float salary;
};

int main() {
    // Declare a variable of type struct HumanBeing
    struct HumanBeing person;

    // Input information for the human being
    printf("Enter name: ");
    scanf("%s", person.name);

    printf("Enter age: ");
    scanf("%d", &person.age);

    printf("Enter salary: ");
    scanf("%f", &person.salary);

    // Display the information
    printf("\nDetails of the human being:\n");
    printf("Name: %s\n", person.name);
    printf("Age: %d\n", person.age);
    printf("Salary: %.2f\n", person.salary);

    return 0;
}
