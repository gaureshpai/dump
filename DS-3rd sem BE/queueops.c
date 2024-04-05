#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

struct Queue{
    int front, rear;
    int array[MAX_SIZE];
};

void initializeQueue(struct Queue *queue){
    queue->front = -1;
    queue->rear = -1;
}

int isEmpty(struct Queue *queue){
    return queue->front == -1;
}

int isFull(struct Queue *queue){
    return (queue->rear == MAX_SIZE - 1);
}

void enqueue(struct Queue *queue, int item){
    if (isFull(queue)){
        printf("Queue is full. Cannot enqueue %d\n", item);
        return;
    }

    if (isEmpty(queue)){
        queue->front = 0;
    }

    queue->rear++;
    queue->array[queue->rear] = item;
    printf("%d enqueued to the queue\n", item);
}

int dequeue(struct Queue *queue){

    if (isEmpty(queue)){
        printf("Queue is empty. Cannot dequeue.\n");
        return -1;
    }

    int item = queue->array[queue->front];
    queue->front++;

    if (queue->front > queue->rear){
        initializeQueue(queue);
    }

    printf("%d dequeued from the queue\n", item);
    return item;
}

int front(struct Queue *queue){

    if (isEmpty(queue)){
        printf("Queue is empty.\n");
        return -1;
    }

    return queue->array[queue->front];
}

void printQueue(struct Queue *queue){

    if (isEmpty(queue)){
        printf("Queue is empty.\n");
        return;
    }

    printf("Queue elements: ");
    for (int i = queue->front; i <= queue->rear; i++){
        printf("%d ", queue->array[i]);
    }

    printf("\n");
}

int main()
{
    struct Queue queue;
    initializeQueue(&queue);

    enqueue(&queue, 10);
    enqueue(&queue, 20);
    enqueue(&queue, 30);

    printQueue(&queue);

    printf("Front element: %d\n", front(&queue));

    dequeue(&queue);
    printQueue(&queue);

    return 0;
}
