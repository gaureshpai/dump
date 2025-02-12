// Move all zeros to the right in a given array

#include<iostream>
using namespace std;

int main() {
    int arr[] = {5, 6, 0, 7, 0, 9, 0, 4};
    int size = sizeof(arr) / sizeof(arr[0]);

    for (int i = 0; i < size; i++) {
        cout << arr[i] << "\t";
    }
    cout << endl;

    int count = 0, temp = 0;

    for (int i = 0; i < size; i++) {
        for (int j = i + 1; j < size - i - 1; j++) {
            if (arr[j] == 0) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

    }

    for (int i = 0; i < size; i++) {
        cout << arr[i] << "\t";
    }
    cout << endl;

    return 0;
}