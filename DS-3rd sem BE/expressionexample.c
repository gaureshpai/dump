#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define MAX_SIZE 100

typedef struct {
    int data[MAX_SIZE];
    int top;
} Stack;

void initialize(Stack *s) {
    s->top = -1;
}

void push(Stack *s, int value) {
    s->data[++(s->top)] = value;
}

int pop(Stack *s) {
    return s->data[(s->top)--];
}

int isOperand(char c) {
    return isdigit(c);
}

int evaluatePostfix(char *expression) {
    Stack stack;
    initialize(&stack);

    for (int i = 0; expression[i] != '\0'; i++) {
        if (isOperand(expression[i])) {
            push(&stack, expression[i] - '0');
        } 
        
        else {
            int operand2 = pop(&stack);
            int operand1 = pop(&stack);

        switch (expression[i]) {
            case '+':
                push(&stack, operand1 + operand2);
                break;
            case '-':
                push(&stack, operand1 - operand2);
                break;
            case '*':
                push(&stack, operand1 * operand2);
                break;
            case '/':
                push(&stack, operand1 / operand2);
                break;
            }
        }
    }

    return pop(&stack);
}

int main() {
    char postfixExpression[] = "34+5*";
    int result = evaluatePostfix(postfixExpression);
    printf("Result of postfix expression: %d\n", result);
    return 0;
}
