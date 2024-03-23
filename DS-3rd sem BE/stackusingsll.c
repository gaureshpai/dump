#include <stdio.h>
#include <stdlib.h>

// Define the structure for a node in the linked list
typedef struct Node {
    int data;
    struct Node *next;
} Node;

// Define the structure for the stack
typedef struct {
    Node *top;  // Points to the top of the stack
} Stack;

// Function to initialize an empty stack
void initialize(Stack *stack) {
    stack->top = NULL;
}

// Function to check if the stack is empty
int isEmpty(Stack *stack) {
    return (stack->top == NULL);
}

// Function to push an element onto the stack
void push(Stack *stack, int value) {
    // Create a new node
    Node *newNode = (Node *)malloc(sizeof(Node));
    if (newNode == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    // Set the data and link the new node to the current top
    newNode->data = value;
    newNode->next = stack->top;

    // Update the top to the new node
    stack->top = newNode;

    printf("Pushed %d onto the stack.\n", value);
}

// Function to pop an element from the stack
int pop(Stack *stack) {
    if (isEmpty(stack)) {
        printf("Stack underflow: Cannot pop from an empty stack.\n");
        exit(EXIT_FAILURE);
    }

    // Get the value from the top node
    int value = stack->top->data;

    // Move the top to the next node and free the popped node
    Node *temp = stack->top;
    stack->top = stack->top->next;
    free(temp);

    printf("Popped %d from the stack.\n", value);
    return value;
}

// Function to peek at the top element of the stack
int peek(Stack *stack) {
    if (isEmpty(stack)) {
        printf("Cannot peek from an empty stack.\n");
        exit(EXIT_FAILURE);
    }

    return stack->top->data;
}

// Function to free the memory used by the stack
void freeStack(Stack *stack) {
    // Pop each element until the stack is empty
    while (!isEmpty(stack)) {
        pop(stack);
    }
}

// Example usage
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
