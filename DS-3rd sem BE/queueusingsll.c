#include <stdio.h>
#include <stdlib.h>

// Define the structure for a node in the linked list
typedef struct Node {
    int data;
    struct Node *next;
} Node;

// Define the structure for the queue
typedef struct {
    Node *front;  // Points to the front of the queue
    Node *rear;   // Points to the rear of the queue
} Queue;

// Function to initialize an empty queue
void initialize(Queue *queue) {
    queue->front = queue->rear = NULL;
}

// Function to check if the queue is empty
int isEmpty(Queue *queue) {
    return (queue->front == NULL);
}

// Function to enqueue (insert) an element into the queue
void enqueue(Queue *queue, int value) {
    // Create a new node
    Node *newNode = (Node *)malloc(sizeof(Node));
    if (newNode == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    // Set the data and link the new node
    newNode->data = value;
    newNode->next = NULL;

    if (isEmpty(queue)) {
        // If the queue is empty, set both front and rear to the new node
        queue->front = queue->rear = newNode;
    } else {
        // Otherwise, link the new node to the rear and update the rear
        queue->rear->next = newNode;
        queue->rear = newNode;
    }

    printf("Enqueued %d into the queue.\n", value);
}

// Function to dequeue (remove) an element from the queue
int dequeue(Queue *queue) {
    if (isEmpty(queue)) {
        printf("Queue underflow: Cannot dequeue from an empty queue.\n");
        exit(EXIT_FAILURE);
    }

    // Get the value from the front node
    int value = queue->front->data;

    // Move the front to the next node and free the dequeued node
    Node *temp = queue->front;
    queue->front = queue->front->next;
    free(temp);

    // If the queue becomes empty, update the rear as well
    if (queue->front == NULL) {
        queue->rear = NULL;
    }

    printf("Dequeued %d from the queue.\n", value);
    return value;
}

// Function to free the memory used by the queue
void freeQueue(Queue *queue) {
    // Dequeue each element until the queue is empty
    while (!isEmpty(queue)) {
        dequeue(queue);
    }
}

// Example usage
int main() {
    Queue myQueue;
    initialize(&myQueue);

    enqueue(&myQueue, 10);
    enqueue(&myQueue, 20);
    enqueue(&myQueue, 30);

    dequeue(&myQueue);
    dequeue(&myQueue);

    printf("Is the queue empty? %s\n", isEmpty(&myQueue) ? "Yes" : "No");

    // Free the memory used by the queue
    freeQueue(&myQueue);

    return 0;
}
