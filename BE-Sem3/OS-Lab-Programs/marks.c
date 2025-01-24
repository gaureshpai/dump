#include<stdio.h>

int main(){
    int m1,m2,m3,m4,m5,m6,m7,m8,marks,total;
    printf("Enter the marks of all 8 subjects:");
    scanf("%d%d%d%d%d%d%d%d",&m1,&m2,&m3,&m4,&m5,&m6,&m7,&m8);

    int total = m1+m2+m3+m4+m5+m6+m7+m8;
    float avg = total/8;
    char grade;

    if(avg>=80){
        grade = 'A';
    }

    else if(avg>=70){
        grade = 'B';
    }

    else if(avg>=60){
        grade = 'C';
    }

    else if(avg>=50){
        grade = 'D';
    }

    else{
        grade = 'F';
    }
    
    printf("Average marks: %2f \n",avg);
    printf("Grade = %c \n",grade);
}