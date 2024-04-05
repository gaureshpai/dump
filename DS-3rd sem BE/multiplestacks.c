#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 15
#define NUM_STACKS 3

typedef struct {
    int* array;
    int* top;
    int* base;
    int stackSize;
} MultiStack;

MultiStack* createMultiStack(int totalSize) {
    MultiStack* multiStack = (MultiStack*)malloc(sizeof(MultiStack));

    if (multiStack == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    multiStack->array = (int*)malloc(sizeof(int) * totalSize);
    if (multiStack->array == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }

    multiStack->top = multiStack->base = multiStack->array;
    multiStack->stackSize = totalSize / NUM_STACKS;

    return multiStack;
}

int isEmpty(MultiStack* multiStack, int stackNum) {
    return (multiStack->top - stackNum * multiStack->stackSize == multiStack->base);
}

int isFull(MultiStack* multiStack, int stackNum) {
    return (multiStack->top - stackNum * multiStack->stackSize == (stackNum + 1) * multiStack->stackSize);
}

void push(MultiStack* multiStack, int stackNum, int item) {
    if (isFull(multiStack, stackNum)) {
        printf("Stack %d is full. Cannot push.\n", stackNum + 1);
        return;
    }

    *(multiStack->top) = item;
    multiStack->top++;
    printf("Pushed %d onto Stack %d\n", item, stackNum + 1);
}

int pop(MultiStack* multiStack, int stackNum) {
    if (isEmpty(multiStack, stackNum)) {
        printf("Stack %d is empty. Cannot pop.\n", stackNum + 1);
        exit(EXIT_FAILURE);
    }

    multiStack->top--;
    int item = *(multiStack->top);
    printf("Popped %d from Stack %d\n", item, stackNum + 1);
    return item;
}

void display(MultiStack* multiStack, int stackNum) {
    if (isEmpty(multiStack, stackNum)) {
        printf("Stack %d is empty.\n", stackNum + 1);
        return;
    }

    printf("Stack %d: ", stackNum + 1);
    int* ptr = multiStack->base + stackNum * multiStack->stackSize;
    while (ptr < multiStack->top) {
        printf("%d ", *ptr);
        ptr++;
    }
    printf("\n");
}

void freeMultiStack(MultiStack* multiStack) {
    free(multiStack->array);
    free(multiStack);
}

int main() {
    
    MultiStack* multiStack = createMultiStack(MAX_SIZE);

    push(multiStack, 0, 10);
    push(multiStack, 1, 20);
    push(multiStack, 2, 30);

    display(multiStack, 0);
    display(multiStack, 1);
    display(multiStack, 2);

    pop(multiStack, 0);
    pop(multiStack, 1);
    pop(multiStack, 2);

    freeMultiStack(multiStack);

    return 0;
}
