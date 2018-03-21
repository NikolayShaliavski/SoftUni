#include <iostream>
using namespace std;

int main () {
    int min = INT_MAX;
    int max = INT_MIN;

    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        int current;
        cin >> current;
        if (current < min) {
            min = current;
        }
        if (current > max) {
            max = current;
        }
    }
    cout << "MIN: " << min << " MAX: " << max << endl;

    return 0;
}
