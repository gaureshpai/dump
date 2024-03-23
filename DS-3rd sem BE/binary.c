#include<stdio.h>
int main()
{
    int n,low=0,high,x=0,mid,a[25],key;
    printf("Enter the number of elements:");
    scanf("%d",&n);
    printf("Enter the elements into the array:");
    for(int i=0;i<num;i++){
        scanf("%d",&a[i]);
    }
    high = n-1;
    printf("Enter the search element:");
    scanf("%d",&key);
    for(int i=0;i<n;i++){
        mid = (high+low)/2;
        if(a[mid] == key)
        x=1;
        if(a[mid]>key)
        high = mid-1;
        else if(a[mid]<key)
        low = mid+1;
        else
        printf("Element doesnt exist");
    }
    if(x==1)
    printf("The element exist at position: %d\n",mid);
    else
    printf("Search unsuccessful");
}