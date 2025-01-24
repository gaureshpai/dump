#include<stdio.h>
#include<ctype.h>

void convertcase(char *str){
    int i;
    while(str[i] != '\0'){
        if(islower(str[i])){
            str[i] = toupper(str[i]);
        }
        else if(isupper(str[i])){
            str[i] = tolower(str[i]);
        }
        i++;
    }
}

int main(){
    char str[100];
    printf("Enter a string:");
    scnf("%99s",str);
    convertcase(str);
    printf("Converted string is:\n",str);
    return 0;
}