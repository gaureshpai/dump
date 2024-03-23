#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

struct Deque {
    int front, rear;
    int array[MAX_SIZE];
};

// Function to initialize a deque
void initializeDeque(struct Deque* deque) {
    deque->front = -1;
    deque->rear = -1;
}

// Function to check if the deque is empty
int isEmpty(struct Deque* deque) {
    return deque->front == -1;
}

// Function to check if the deque is full
int isFull(struct Deque* deque) {
    return (deque->front == 0 && deque->rear == MAX_SIZE - 1) || (deque->front == deque->rear + 1);
}

// Function to add an element to the front of the deque
void insertFront(struct Deque* deque, int item) {
    if (isFull(deque)) {
        printf("Deque is full. Cannot insert %d at the front.\n", item);
        return;
    }

    if (isEmpty(deque)) {
        deque->front = 0;
        deque->rear = 0;
    } else if (deque->front == 0) {
        deque->front = MAX_SIZE - 1;
    } else {
        deque->front--;
    }

    deque->array[deque->front] = item;
    printf("%d inserted at the front of the deque\n", item);
}

// Function to add an element to the rear of the deque
void insertRear(struct Deque* deque, int item) {
    if (isFull(deque)) {
        printf("Deque is full. Cannot insert %d at the rear.\n", item);
        return;
    }

    if (isEmpty(deque)) {
        deque->front = 0;
        deque->rear = 0;
    } else if (deque->rear == MAX_SIZE - 1) {
        deque->rear = 0;
    } else {
        deque->rear++;
    }

    deque->array[deque->rear] = item;
    printf("%d inserted at the rear of the deque\n", item);
}

// Function to remove an element from the front of the deque
int deleteFront(struct Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty. Cannot delete from the front.\n");
        return -1; // Return an invalid value to indicate failure
    }

    int item = deque->array[deque->front];

    if (deque->front == deque->rear) {
        initializeDeque(deque);
    } else if (deque->front == MAX_SIZE - 1) {
        deque->front = 0;
    } else {
        deque->front++;
    }

    printf("%d deleted from the front of the deque\n", item);
    return item;
}

// Function to remove an element from the rear of the deque
int deleteRear(struct Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty. Cannot delete from the rear.\n");
        return -1; // Return an invalid value to indicate failure
    }

    int item = deque->array[deque->rear];

    if (deque->front == deque->rear) {
        initializeDeque(deque);
    } else if (deque->rear == 0) {
        deque->rear = MAX_SIZE - 1;
    } else {
        deque->rear--;
    }

    printf("%d deleted from the rear of the deque\n", item);
    return item;
}

// Function to get the front element of the deque without removing it
int getFront(struct Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty.\n");
        return -1; // Return an invalid value to indicate failure
    }

    return deque->array[deque->front];
}

// Function to get the rear element of the deque without removing it
int getRear(struct Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty.\n");
        return -1; // Return an invalid value to indicate failure
    }

    return deque->array[deque->rear];
}

// Function to print the elements of the deque
void printDeque(struct Deque* deque) {
    if (isEmpty(deque)) {
        printf("Deque is empty.\n");
        return;
    }

    printf("Deque elements: ");
    int i = deque->front;
    do {
        printf("%d ", deque->array[i]);

        if (i == MAX_SIZE - 1) {
            i = 0;
        } else {
            i++;
        }

    } while (i != (deque->rear + 1) % MAX_SIZE);

    printf("\n");
}

int main() {
    struct Deque deque;
    initializeDeque(&deque);

    insertRear(&deque, 10);
    insertRear(&deque, 20);
    insertFront(&deque, 5);

    printDeque(&deque);

    printf("Front element: %d\n", getFront(&deque));
    printf("Rear element: %d\n", getRear(&deque));

    deleteFront(&deque);
    printDeque(&deque);

    deleteRear(&deque);
    printDeque(&deque);

    return 0;
}
