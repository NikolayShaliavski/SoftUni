#include <iostream>
using namespace std;

int main() {
    long long fact = 0;
    cin >> fact;

    int divider = 2;
    int num = 0;
    while(fact > divider) {
        fact /= divider;
        divider++;
    }
    num = divider;
    cout << num << endl;
    return 0;
}
