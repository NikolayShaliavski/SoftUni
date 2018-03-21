#include <iostream>
using namespace std;

int fibonacci(int n);

int main() {
    int n = 0;
    cin >> n;

    int res = fibonacci(n);
    cout << n << "-th fibonacci number: " << res << endl;
    return 0;
}

int fibonacci(int n) {
    if (n <= 1) {
        return 0;
    }
    if (n <= 3) {
        return 1;
    }
    return fibonacci(n - 2) + fibonacci(n - 1);
}
