#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

bool compareArrays(const vector<int> &left, const vector<int> &right);

int main() {
    vector<int> left;
    vector<int> right;

    string line = "";
    getline(cin, line);
    istringstream left_is(line);
    int number = 0;
    while(left_is >> number) {
        left.push_back(number);
    }

    line = "";
    getline(cin, line);
    istringstream right_is(line);
    number = 0;
    while(right_is >> number) {
        right.push_back(number);
    }

    bool arrEqual = compareArrays(left, right);
    if (arrEqual) {
        cout << "Arrays are equal." << endl;
    } else {
        cout << "Arrays are NOT equal." << endl;
    }
    return 0;
}

bool compareArrays(const vector<int> &left, const vector<int> &right) {
    bool areEqual = true;
    if (left.size() != right.size()) {
        areEqual = false;
        return areEqual;
    }
    for(int i = 0; i < left.size(); i++) {
        if (left[i] != right[i]) {
            areEqual = false;
            break;
        }
    }
    return areEqual;
}
