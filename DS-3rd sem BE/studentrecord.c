#include <stdio.h>

// Define the structure for a student record
struct StudentRecord {
    char name[50];
    int regNumber;
    long long contactNumber;
    int age;
    float avgMarks;
};

// Function to read a student record
void readStudentRecord(struct StudentRecord *student) {
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

// Function to write a student record
void writeStudentRecord(struct StudentRecord student) {
    printf("\nStudent Record:\n");
    printf("Name: %s\n", student.name);
    printf("Registration Number: %d\n", student.regNumber);
    printf("Contact Number: %lld\n", student.contactNumber);
    printf("Age: %d\n", student.age);
    printf("Average Marks: %.2f\n", student.avgMarks);
}

int main() {
    int numStudents;

    printf("Enter the number of students: ");
    scanf("%d", &numStudents);

    // Declare an array of student records
    struct StudentRecord students[numStudents];

    // Read and write records for each student
    for (int i = 0; i < numStudents; i++) {
        printf("\nEnter details for Student %d:\n", i + 1);
        readStudentRecord(&students[i]);
    }

    printf("\nDetails of all students:\n");

    for (int i = 0; i < numStudents; i++) {
        writeStudentRecord(students[i]);
    }

    return 0;
}
