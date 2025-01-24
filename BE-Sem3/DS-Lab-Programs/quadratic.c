#include<stdio.h>
#include<math.h>

int main(){
    double a,b,c;
    double disc,r1,r2;

    printf("Enter the coefficients of a quadratic equation(a,b,c):");
    scanf("%f %f %f",&a,&b,&c);

    disc = b*b-4*a*c;

    if(disc > 0){
        r1 = (-b+sqrt(disc))/(2*a);
        r2 = (-b-sqrt(disc))/(2*a);

        printf("Roots are real and distinct:\n");

        printf("Root1 = %lf\n",r1);
        printf("Root2 = %lf\n",r2);
    }

    else if(disc = 0){
        r1 = -b/(2*a);

        prinf("Roots are real and identical:\n");
        printf("Root1 = %lf\n",r1);
    }

    else{
        double real = -b/(2*a);
        double img = sqrt(-disc)/(2*a);

        printf("Roots are complex and conjugates of each other:\n");
        
        printf("Root1 = %2lf+%lfi\n",real,img);
        printf("Root2 = %2lf+%lfi\n",real,img);
    }
}