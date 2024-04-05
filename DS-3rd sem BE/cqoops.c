#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 5

typedef struct {
    int front, rear;
    int capacity;
    int* array;
} CircularQueue;

CircularQueue* createCircularQueue(int capacity) {
    CircularQueue* queue = (CircularQueue*)malloc(sizeof(CircularQueue));

    if (queue == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    queue->capacity = capacity;
    queue->front = -1;
    queue->rear = -1;
    queue->array = (int*)malloc(capacity * sizeof(int));

    if (queue->array == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    return queue;
}

int isEmpty(CircularQueue* queue) {
    return (queue->front == -1);
}

int isFull(CircularQueue* queue) {
    return ((queue->rear + 1) % queue->capacity == queue->front);
}

void enqueue(CircularQueue* queue, int item) {

    if (isFull(queue)) {
        printf("Queue is full. Cannot enqueue.\n");
        return;
    }

    if (isEmpty(queue)) {
        queue->front = 0;
    }

    queue->rear = (queue->rear + 1) % queue->capacity;
    queue->array[queue->rear] = item;
    printf("Enqueued: %d\n", item);
}

int dequeue(CircularQueue* queue) {

    if (isEmpty(queue)) {
        printf("Queue is empty. Cannot dequeue.\n");
        exit(EXIT_FAILURE);
    }

    int item = queue->array[queue->front];

    if (queue->front == queue->rear) {
        queue->front = -1;
        queue->rear = -1;
    } 
    else {
        queue->front = (queue->front + 1) % queue->capacity;
    }

    printf("Dequeued: %d\n", item);
    return item;
}

void display(CircularQueue* queue) {

    if (isEmpty(queue)) {
        printf("Queue is empty.\n");
        return;
    }

    printf("Queue: ");
    int i = queue->front;

    do {
        printf("%d ", queue->array[i]);
        i = (i + 1) % queue->capacity;
    } while (i != (queue->rear + 1) % queue->capacity);

    printf("\n");
}

void freeCircularQueue(CircularQueue* queue) {
    free(queue->array);
    free(queue);
}

int main() {
    CircularQueue* queue = createCircularQueue(MAX_SIZE);

    enqueue(queue, 10);
    enqueue(queue, 20);
    enqueue(queue, 30);
    enqueue(queue, 40);
    enqueue(queue, 50);

    display(queue);

    dequeue(queue);
    dequeue(queue);

    display(queue);

    enqueue(queue, 60);
    enqueue(queue, 70);

    display(queue);

    freeCircularQueue(queue);
    
    return 0;
}
