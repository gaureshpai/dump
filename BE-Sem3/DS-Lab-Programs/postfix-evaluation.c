#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>

int i, top = -1;
int op1, op2, res, s[20];
char postfix[90], symb;

void push(int item) {
    top = top + 1;
    s[top] = item;
}

int pop() {
    int item;
    item = s[top];
    top = top - 1;
    return item;
}

int main() {
    printf("\nEnter a valid postfix expression:\n");
    scanf("%s", postfix);

    for (i = 0; postfix[i] != '\0'; i++) {
        symb = postfix[i];

        if (isdigit(symb)) {
            push(symb - '0');
        } 
        else {
            op2 = pop();
            op1 = pop();
            switch (symb) {
                case '+':
                    push(op1 + op2);
                    break;
                case '-':
                    push(op1 - op2);
                    break;
                case '*':
                    push(op1 * op2);
                    break;
                case '/':
                    push(op1 / op2);
                    break;
                case '%':
                    push(op1 % op2);
                    break;
                case '$':
                case '^':
                    push(pow(op1, op2));
                    break;
                default:
                    push(0);
            }
        }
    }

    res = pop();
    printf("\n Result = %d\n", res);
    return 0;
}
