#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 10

typedef struct {
    int data[MAX_SIZE];
    int top;
} Stack;

void initialize(Stack *s) {
    s->top = -1;
}

int isEmpty(Stack *s) {
    return (s->top == -1);
}

int isFull(Stack *s) {
    return (s->top == MAX_SIZE - 1);
}

void push(Stack *s, int value) {
    if (isFull(s)) {
        printf("Stack overflow: Cannot push %d, stack is full.\n", value);
        return;
    }

    s->data[++(s->top)] = value;
    printf("Pushed %d onto the stack.\n", value);
}

int pop(Stack *s) {
    if (isEmpty(s)) {
        printf("Stack underflow: Cannot pop from an empty stack.\n");
        exit(EXIT_FAILURE);
    }

    printf("Popped %d from the stack.\n", s->data[s->top]);
    return s->data[(s->top)--];
}

int peek(Stack *s) {
    if (isEmpty(s)) {
        printf("Cannot peek from an empty stack.\n");
        exit(EXIT_FAILURE);
    }
    
    return s->data[s->top];
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

    return 0;
}
