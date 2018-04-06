#include<iostream>
#include "BigInt.h"
using namespace std;

BigInt getFirstDivisible(BigInt num);
bool isDivisibleBy45(string num);

int main() {
    string startIn;
    string endIn;

    cin >> startIn;
    cin >> endIn;

    BigInt num(startIn);
    num = getFirstDivisible(num);
    BigInt endNum(endIn);
    while(num < endNum) {
        cout << num.getDigits() << endl;
        BigInt newNum("45");
        num += newNum;
    }

    return 0;
}
BigInt getFirstDivisible(BigInt num) {
    while(!isDivisibleBy45(num.getDigits())) {
        BigInt increment("1");
        num += increment;
    }
    return num;
}
bool isDivisibleBy45(string num) {
    bool isDivisible = false;
    if (num[num.size() - 1] == '0' || num[num.size() - 1] == '5') {
        int digitsSum = 0;
        for(int i = 0; i < num.size(); i++) {
            digitsSum += num[i] - '0';
        }
        if (digitsSum % 9 == 0) {
            isDivisible = true;
        }
    }
    return isDivisible;
}
