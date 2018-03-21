#include <iostream>
#include <algorithm>
using namespace std;

int find5(int n);
int find2(int n);

int main() {
    int n = 0;
    cin >> n;

    int occurencesOf5 = find5(n);
    int occurencesOf2 = find2(n);
    //cout << occurencesOf5 << endl;
    //cout << occurencesOf2 << endl;

    int trailingZeros = min(occurencesOf5, occurencesOf2);
    cout << "Trailing zeroes for " << n << "!: " << trailingZeros << endl;
    return 0;
}

int find5(int n) {
    int divider = 5;
    int occurences = 0;
    while(divider <= n) {
        occurences += n / divider;
        divider *= 5;
    }
    return occurences;
}

int find2(int n) {
    int divider = 2;
    int occurences = 0;
    while(divider <= n) {
        occurences += n / divider;
        divider *= 2;
    }
    return occurences;
}
