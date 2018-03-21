#include <iostream>
using namespace std;

int reverse(int number);

int main () {
    int n = 0;
    cin >> n;

    for (int i = 0; i < n; i++) {
        int current = 0;
        cin >> current;
        int reversed = reverse(current);

        cout << "Origin: " << current << " Reversed: " << reversed << endl;
    }

    return 0;
}

int reverse (int number) {
    int reversed = 0;
    while (number > 0) {
        int lastDigit = number % 10;
        reversed = (reversed * 10) + lastDigit;

        number /= 10;
    }
    return reversed;
}
