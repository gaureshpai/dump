#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 5

typedef struct {
    int front, rear;
    int capacity;
    int* array;
} Deque;

Deque* createDeque(int capacity) {
    Deque* deque = (Deque*)malloc(sizeof(Deque));
    if (deque == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    deque->capacity = capacity;
    deque->front = -1;
    deque->rear = -1;
    deque->array = (int*)malloc(capacity * sizeof(int));
    if (deque->array == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    return deque;
}

int isEmpty(Deque* deque) {
    return (deque->front == -1);
}

int isFull(Deque* deque) {
    return ((deque->rear + 1) % deque->capacity == deque->front);
}

void enqueueFront(Deque* deque, int item) {
    if (isFull(deque)) {
        printf("Deque is full. Cannot enqueue at the front.\n");
        return;
    }

    if (isEmpty(deque)) {
        deque->front = 0;
        deque->rear = 0;
    } else {
        deque->front = (deque->front - 1 + deque->capacity) % deque->capacity;
    }

    deque->array[deque->front] = item;
    printf("Enqueued at the front: %d\n", item);
}

void enqueueRear(Deque* deque, int item) {
    if (isFull(deque)) {
        printf("Deque is full. Cannot enqueue at the rear.\n");
        return;
    }

    if (isEmpty(deque)) {
        deque->front = 0;
        deque->rear = 0;
    } else {
        deque->rear = (deque->rear + 1) % deque->capacity;
    }

    deque->array[deque->rear] = item;
    printf("Enqueued at the rear: %d\n", item);
}

int dequeueFront(Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty. Cannot dequeue from the front.\n");
        exit(EXIT_FAILURE);
    }

    int item = deque->array[deque->front];
    if (deque->front == deque->rear) {
        deque->front = -1;
        deque->rear = -1;
    } else {
        deque->front = (deque->front + 1) % deque->capacity;
    }

    printf("Dequeued from the front: %d\n", item);
    return item;
}

int dequeueRear(Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty. Cannot dequeue from the rear.\n");
        exit(EXIT_FAILURE);
    }

    int item = deque->array[deque->rear];
    if (deque->front == deque->rear) {
        deque->front = -1;
        deque->rear = -1;
    } else {
        deque->rear = (deque->rear - 1 + deque->capacity) % deque->capacity;
    }

    printf("Dequeued from the rear: %d\n", item);
    return item;
}

void display(Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty.\n");
        return;
    }

    printf("Deque: ");
    int i = deque->front;
    do {
        printf("%d ", deque->array[i]);
        i = (i + 1) % deque->capacity;
    } while (i != (deque->rear + 1) % deque->capacity);
    printf("\n");
}

void freeDeque(Deque* deque) {
    free(deque->array);
    free(deque);
}

int main() {
    Deque* deque = createDeque(MAX_SIZE);

    enqueueFront(deque, 10);
    enqueueFront(deque, 20);
    enqueueRear(deque, 30);
    enqueueRear(deque, 40);

    display(deque);

    dequeueFront(deque);
    dequeueRear(deque);

    display(deque);

    freeDeque(deque);

    return 0;
}
