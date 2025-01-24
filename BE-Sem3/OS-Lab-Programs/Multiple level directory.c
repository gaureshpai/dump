#include <stdio.h>
#include <string.h>
#include <stdlib.h>
struct
{
    char dname[10], fname[10][10];
    int fcnt;
} dir[10];
void main()
{
    int i, ch, dcnt, k;
    char f[30], d[30];
    dcnt = 0;
    while (1)
    {
        printf("\n\n1. Create Directory\t2. Create File\t3. Delete File");
        printf("\n4. Search File\t\t5. Display\t6. Exit\tEnter your choice -- ");
        scanf("%d", &ch);
        switch (ch)
        {
        case 1:
            printf("\nEnter name of directory -- ");
            scanf("%s", dir[dcnt].dname);
            dir[dcnt].fcnt = 0;
            dcnt++;
            printf("Directory created");
            break;
        case 2:
            printf("\nEnter name of the directory -- ");
            scanf("%s", d);
            for (i = 0; i < dcnt; i++)
                if (strcmp(d, dir[i].dname) == 0)
                {
                    printf("Enter name of the file -- ");
                    scanf("%s", dir[i].fname[dir[i].fcnt]);
                    printf("File created");
                    break;
                }
            if (i == dcnt)
                printf("Directory %s not found", d);
            break;
        case 3:
            printf("\nEnter name of the directory -- ");
            scanf("%s", d);
            for (i = 0; i < dcnt; i++)
            {
                if (strcmp(d, dir[i].dname) == 0)
                {
                    printf("Enter name of the file -- ");
                    scanf("%s", f);
                    for (k = 0; k < dir[i].fcnt; k++)
                    {
                        if (strcmp(f, dir[i].fname[k]) == 0)
                        {
                            printf("File %s is deleted ", f);
                            dir[i].fcnt--;
                            strcpy(dir[i].fname[k], dir[i].fname[dir[i].fcnt]);
                            goto jmp;
                        }
                    }
                    printf("File %s not found", f);
                    goto jmp;
                }
            }
            printf("Directory %s not found", d);
        jmp:
            break;
        case 4:
            printf("\nEnter name of the directory -- ");
            scanf("%s", d);
            for (i = 0; i < dcnt; i++)
            {
                if (strcmp(d, dir[i].dname) == 0)
                {
                    printf("Enter the name of the file -- ");
                    scanf("%s", f);
                    for (k = 0; k < dir[i].fcnt; k++)
                    {
                        if (strcmp(f, dir[i].fname[k]) == 0)
                        {
                            printf("File %s is found ", f);
                            goto jmp1;
                        }
                    }
                    printf("File %s not found", f);
                    goto jmp1;
                }
            }
            printf("Directory %s not found", d);
        jmp1:
            break;
        case 5:
            if (dcnt == 0)
                printf("\nNo Directory's ");
            else
            {
                printf("\nDirectory\tFiles");
                for (i = 0; i < dcnt; i++)
                {
                    printf("\n%s\t\t", dir[i].dname);
                    for (k = 0; k < dir[i].fcnt; k++)
                        printf("\t%s", dir[i].fname[k]);
                }
            }
            break;
        default:
            exit(0);
        }
    }
}