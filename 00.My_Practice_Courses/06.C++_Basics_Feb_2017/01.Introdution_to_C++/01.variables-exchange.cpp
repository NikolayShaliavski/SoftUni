#include <iostream>
using namespace std;

int main() {
    int a = 3;
    int b = 2;

    if (a > b) {
        int temp = b;
        b = a;
        a = temp;
    }
    cout << "A: " << a << " B: " << b << endl;
    return 0;
}
