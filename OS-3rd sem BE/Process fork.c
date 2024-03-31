#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    pid_t child_pid;
    int status;

    child_pid = fork(); 
    if (child_pid < 0)
    {
        perror("Fork failed");
        exit(1);
    }
    else if (child_pid == 0)
    {
        printf("Child process (PID: %d) is running.\n", getpid()); 
        execlp("/bin/ls", "ls", "-l", NULL);                       
        perror("Exec failed");                                     
        exit(1);
    }
    else
    {
        printf("Parent process (PID: %d) created a child (PID: %d).\n", getpid(), child_pid); 
        wait(&status);                                                                        
        if (WIFEXITED(status))
        {
            printf("Child process (PID: %d) exited with status %d.\n", child_pid,
                   WEXITSTATUS(status));
        }
        else
        {
            printf("Child process (PID: %d) did not exit normally.\n", child_pid);
        }
    }
    return 0;
}