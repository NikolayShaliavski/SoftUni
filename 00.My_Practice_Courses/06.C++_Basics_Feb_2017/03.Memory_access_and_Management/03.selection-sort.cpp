#include <iostream>
#include <limits>

using namespace std;

void sort(int arr[], const int length);
void swap(int arr[], int left, int right);

int main() {
    int arr[5] = {3, 3, 1, 3, -2};

    sort(arr, 5);

    for(int num : arr) {
        cout << num << " ";
    }
    cout << endl;
    return 0;
}

void sort(int arr[], const int length) {
    for(int i = 0; i < length; i++) {
        int minNum = INT_MAX;
        int minIndex = 0;
        for(int j = i; j < length; j++) {
            if (arr[j] < minNum) {
                minNum = arr[j];
                minIndex = j;
            }
        }
        swap(arr, i, minIndex);
    }
}

void swap(int arr[], int left, int right) {
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
}
