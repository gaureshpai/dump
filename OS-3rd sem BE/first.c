#include<stdio.h>

int main(){
    char name[25],college[25],adddress[25];

    printf("Enter your name:");
    scanf("%s",name);

    printf("Enter your college name:");
    scanf("%s",college);

    printf("Enter your address:");
    scanf("%s",adddress);
    
    printf("Name = %s",name);
    printf("College = %s",college);
    printf("Address = %s",adddress);
}