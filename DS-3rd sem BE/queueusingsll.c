#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int data;
    struct Node *next;
} Node;

typedef struct
{
    Node *front;
    Node *rear;
} Queue;

void initialize(Queue *queue)
{
    queue->front = queue->rear = NULL;
}

int isEmpty(Queue *queue)
{
    return (queue->front == NULL);
}

void enqueue(Queue *queue, int value)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    if (newNode == NULL)
    {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    newNode->data = value;
    newNode->next = NULL;

    if (isEmpty(queue))
    {
        queue->front = queue->rear = newNode;
    }
    else
    {
        queue->rear->next = newNode;
        queue->rear = newNode;
    }

    printf("Enqueued %d into the queue.\n", value);
}

int dequeue(Queue *queue)
{
    if (isEmpty(queue))
    {
        printf("Queue underflow: Cannot dequeue from an empty queue.\n");
        exit(EXIT_FAILURE);
    }

    int value = queue->front->data;

    Node *temp = queue->front;
    queue->front = queue->front->next;
    free(temp);

    if (queue->front == NULL)
    {
        queue->rear = NULL;
    }

    printf("Dequeued %d from the queue.\n", value);
    return value;
}

void freeQueue(Queue *queue)
{
    while (!isEmpty(queue))
    {
        dequeue(queue);
    }
}

int main()
{
    Queue myQueue;
    initialize(&myQueue);

    enqueue(&myQueue, 10);
    enqueue(&myQueue, 20);
    enqueue(&myQueue, 30);

    dequeue(&myQueue);
    dequeue(&myQueue);

    printf("Is the queue empty? %s\n", isEmpty(&myQueue) ? "Yes" : "No");

    freeQueue(&myQueue);

    return 0;
}
