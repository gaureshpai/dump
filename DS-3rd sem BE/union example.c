#include <stdio.h>

union Data {
    int i;
    float f;
    char str[20];
};

int main() {
    union Data data;

    // Accessing and modifying union members
    data.i = 10;
    printf("i: %d\n", data.i);

    data.f = 3.14;
    printf("f: %f\n", data.f);

    strcpy(data.str, "Hello, Union!");
    printf("str: %s\n", data.str);

    return 0;
}
