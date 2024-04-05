#include <stdio.h>

struct Point {
    int x;
    int y;
};

void printPoint(struct Point p) {
    printf("(%d, %d)\n", p.x, p.y);
}

int main() {
    struct Point p1 = {10, 20};
    struct Point p2;
    
    p2.x = 30; p2.y = 40;

    printf("Coordinates of p1: ");
    printPoint(p1);

    printf("Coordinates of p2: ");
    printPoint(p2);

    return 0;
}
