#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct {
    char* name;
    int date;
    char* description;
} Day;

Day* create(){
    Day* calendar = (Day*)malloc(sizeof(Day) * 7);

    if( calendar == NULL) {   
        printf("Error allocating memory for calendar\n");
        exit(1);
    }

    return calendar;
}

void read (Day *calendar){

    for( int i=0;i<7;i++) {
        printf("Enter the name of day %d: ",i+1);
        char *name = (char *)malloc(sizeof(char)*100);
        if(name == NULL){
            printf("Error allocating memory for day name\n");
            exit(1);
        }
        scanf("%s",name);
        calendar[i].name = name;
        
        printf("Enter the day of day %d:",i+1);
        int date;
        scanf("%d",&date);
        calendar[i].date = date;

        printf("Enter the description of activity of day %d:",i+1);
        char *description = (char*)malloc(sizeof(char)*100);
        if(description == NULL){
           printf("Error allocating memeory for activity description");
           exit(1);
        }
        scanf("%s",description);
        calendar[i].description = description;
    }
}

void display (Day *calendar){
    printf("\n Week Activity Details Report\n");
    printf("__________________________________");
    
    for( int i = 0 ; i < 7 ; i++){
        printf("%s %d : %s\n",calendar[i].name,calendar[i].date,calendar[i].description);
    }
}

int main() {
    Day *calendar = create();

    read(calendar);
    display(calendar);

    for(int i=0;i<7;i++){
        free(calendar[i].name);
        free(calendar[i].description);
    }
    free(calendar);
    return 0;
}
