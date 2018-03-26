#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

int * parseNumbers(const string &line, int &resultLength) {
    istringstream inStr(line);
    vector<int> arr;
    int number = 0;
    while(inStr >> number) {
        arr.push_back(number);
    }
    int arrSize = arr.size();
    int *arrPointer = new int[arrSize];
    for(int i = 0; i < arrSize; i++) {
        arrPointer[i] = arr[i];
    }
    resultLength = arrSize;
    return arrPointer;
}

int main() {
    int linesCount = 0;
    cin >> linesCount;
    cin.ignore();
    int sum = 0;
    for(int i = 0; i < linesCount; i++) {
        string line = "";
        getline(cin, line);
        int length = 0;

        int *pointer = parseNumbers(line, length);
        for(int i = 0; i < length; i++) {
            sum += pointer[i];
        }
        delete[] pointer;
    }

    cout << "Sum: " << sum << endl;
    return 0;
}
