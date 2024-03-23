#include<stdio.h>
#define MALLOC(p,n,type)
p = (type *)malloc(n * sizeof(type));
if(p == NULL){
    printf("Insufficient memory\n");
    exit(0);
}
int main(){
    int *p1;
    float *p2;
    char *p3;
    double *p4;
    p1 = MALLOC(p1,10,int);
    p2 = MALLOC(p2,20,float);
    p3 = MALLOC(p3,30,char);
    p4 = MALLOC(p4,40,double);
    free(p1);
    free(p2);free(p3);free(p4);
}