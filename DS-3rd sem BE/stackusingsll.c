#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int data;
    struct Node *next;
} Node;

typedef struct{
    Node *top;
} Stack;

void initialize(Stack *stack){
    stack->top = NULL;
}

int isEmpty(Stack *stack){
    return (stack->top == NULL);
}

void push(Stack *stack, int value){
    Node *newNode = (Node *)malloc(sizeof(Node));

    if (newNode == NULL){
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    newNode->data = value;
    newNode->next = stack->top;
    stack->top = newNode;

    printf("Pushed %d onto the stack.\n", value);
}

int pop(Stack *stack){
    if (isEmpty(stack)){
        printf("Stack underflow: Cannot pop from an empty stack.\n");
        exit(EXIT_FAILURE);
    }

    int value = stack->top->data;
    Node *temp = stack->top;
    stack->top = stack->top->next;
    free(temp);

    printf("Popped %d from the stack.\n", value);
    return value;
}

int peek(Stack *stack){
    if (isEmpty(stack)){
        printf("Cannot peek from an empty stack.\n");
        exit(EXIT_FAILURE);
    }

    return stack->top->data;
}

void freeStack(Stack *stack){
    while (!isEmpty(stack)){
        pop(stack);
    }
}

int main(){
    Stack myStack;
    initialize(&myStack);

    push(&myStack, 10);
    push(&myStack, 20);
    push(&myStack, 30);

    printf("Top of the stack: %d\n", peek(&myStack));

    pop(&myStack);
    pop(&myStack);

    printf("Is the stack empty? %s\n", isEmpty(&myStack) ? "Yes" : "No");

    freeStack(&myStack);

    return 0;
}
