#include<stdio.h> /*wrong program*/

void main(){

    int n,a[25][25],i,j,b[25][25],count,k,l,p,q;

    printf("Enter the number of elements: ");
    scanf("%d",&n);

    printf("Enter the number of rows and columns: ");
    scanf("%d%d",&i,&j);

    printf("Enter the elements into the matrix: ");
    for( k=0;k<i;k++)
        for (  l = 0; l < j; l++)
            scanf("%d",&a[k][l]);
    printf("The entered elements of the matrix are: ");

    for( k=0;k<i;k++){
        for (  l = 0; l < j; l++)
            printf("%d\t",&a[k][l]);
        printf("\n");
    }

    for( k=0;k<i;k++)
        for (  l = 0; l < j; l++)
        b[k][l] = a[l][k];
    
    printf("Transpose of the matrix is: \n");
    for( k=0;k<i;k++){
        for (  l = 0; l < j; l++){
            printf("%d\t",&b[k][l]);
        }
        printf("\n");
    }

    for( k=0;k<i;k++){
        for (  l = 0; l < j; l++){
            if(a[k][l]!=0){
                count+=1;
            } 
        }

        int Triplet[3][count+1],Trans[3][count+1] ,x=1,y=0;

        for( k=0;k<i;k++){
            for (  l = 0; l < j; l++){
                if(b[k][l]!=0){
                    Triplet[k][l] = k;
                    Triplet[k][l+1] = l;
                    Triplet[k][l+1] = a[k][l];
                    k++;
                }x=x-1;
            }
        }

        Triplet[0][0] = k;
        Triplet[0][1] = l;
        Triplet[0][2] = count;
        int p=1,q=0;

        for( k=0;k<i;k++){
            for (  l = 0; l < j; l++){
                if(b[k][l]!=0){
                    Trans[p][q] = k;
                    Trans[p][q+1] = l;
                    Trans[p][q+1] = b[k][l];
                    p++;
                }
            p=p-1;
            }
        }

        Trans[0][0] = p;
        Trans[0][1] = q;
        Trans[0][2] = count;

        printf("The matrix triplet is given by \n");
        for(int k=0;k<i;k++){
            for (int  l = 0; l < 3; l++){
                printf("%d\t",Triplet[k][l]);
            }
            printf("\n");
        }
    }

    printf("The matrix Transpose triplet is given by \n");
    for(int k=0;k<i;k++){
        for (int  l = 0; l < 3; l++){
        printf("%d\t",Trans[k][l]);
        }
        printf("\n");
    }
    
}