#include <stdio.h>
#include <stdlib.h>

#define INITIAL_CAPACITY 5

typedef struct {
    int *data;
    int top;
    int capacity;
} Stack;

// Function to initialize a stack
void initialize(Stack *s) {
    s->data = (int *)malloc(sizeof(int) * INITIAL_CAPACITY);
    if (s->data == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }
    s->top = -1;
    s->capacity = INITIAL_CAPACITY;
}

// Function to check if the stack is empty
int isEmpty(Stack *s) {
    return (s->top == -1);
}

// Function to check if the stack is full
int isFull(Stack *s) {
    return (s->top == s->capacity - 1);
}

// Function to push an element onto the stack
void push(Stack *s, int value) {
    if (isFull(s)) {
        // Resize the array if it's full
        s->capacity *= 2;
        s->data = (int *)realloc(s->data, sizeof(int) * s->capacity);
        if (s->data == NULL) {
            printf("Memory reallocation failed.\n");
            exit(EXIT_FAILURE);
        }
    }

    s->data[++(s->top)] = value;
    printf("Pushed %d onto the stack.\n", value);
}

// Function to pop an element from the stack
int pop(Stack *s) {
    if (isEmpty(s)) {
        printf("Stack underflow: Cannot pop from an empty stack.\n");
        exit(EXIT_FAILURE);
    }

    printf("Popped %d from the stack.\n", s->data[s->top]);
    return s->data[(s->top)--];
}

// Function to peek at the top element of the stack
int peek(Stack *s) {
    if (isEmpty(s)) {
        printf("Cannot peek from an empty stack.\n");
        exit(EXIT_FAILURE);
    }
    return s->data[s->top];
}

// Function to free the memory used by the stack
void freeStack(Stack *s) {
    free(s->data);
}

int main() {
    Stack myStack;
    initialize(&myStack);

    push(&myStack, 10);
    push(&myStack, 20);
    push(&myStack, 30);

    printf("Top of the stack: %d\n", peek(&myStack));

    pop(&myStack);
    pop(&myStack);

    printf("Is the stack empty? %s\n", isEmpty(&myStack) ? "Yes" : "No");

    // Free the memory used by the stack
    freeStack(&myStack);

    return 0;
}
