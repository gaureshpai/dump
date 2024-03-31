#include <stdio.h>

struct StudentRecord
{
    char name[50];
    int regNumber;
    long long contactNumber;
    int age;
    float avgMarks;
};

void readStudentRecord(struct StudentRecord *student)
{
    printf("Enter student name: ");
    scanf("%s", student->name);

    printf("Enter registration number: ");
    scanf("%d", &student->regNumber);

    printf("Enter contact number: ");
    scanf("%lld", &student->contactNumber);

    printf("Enter age: ");
    scanf("%d", &student->age);

    printf("Enter average marks: ");
    scanf("%f", &student->avgMarks);
}

void writeStudentRecord(struct StudentRecord student)
{
    printf("\nStudent Record:\n");
    printf("Name: %s\n", student.name);
    printf("Registration Number: %d\n", student.regNumber);
    printf("Contact Number: %lld\n", student.contactNumber);
    printf("Age: %d\n", student.age);
    printf("Average Marks: %.2f\n", student.avgMarks);
}

int main()
{
    int numStudents;

    printf("Enter the number of students: ");
    scanf("%d", &numStudents);

    struct StudentRecord students[numStudents];

    for (int i = 0; i < numStudents; i++)
    {
        printf("\nEnter details for Student %d:\n", i + 1);
        readStudentRecord(&students[i]);
    }

    printf("\nDetails of all students:\n");

    for (int i = 0; i < numStudents; i++)
    {
        writeStudentRecord(students[i]);
    }

    return 0;
}
