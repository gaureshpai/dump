// This side reads first, then reads
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
int main()
{
    int fd1;
    char *myfifo = "/tmp/myfifo";
    mkfifo(myfifo, 0666);
    char str1[80], str2[80];
    while (1)
    {
        fd1 = open(myfifo, O_RDONLY);
        read(fd1, str1, 80);
        printf("User1: %s\n", str1);
        close(fd1);
        fd1 = open(myfifo, O_WRONLY);
        fgets(str2, 80, stdin);
        write(fd1, str2, strlen(str2) + 1);
        close(fd1);
    }
    return 0;
}
