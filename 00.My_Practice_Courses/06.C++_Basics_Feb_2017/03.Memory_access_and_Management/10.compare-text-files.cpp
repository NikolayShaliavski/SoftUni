#include <iostream>
#include <fstream>

using namespace std;

int main() {
    ifstream leftStr("sources\\left.txt");
    ifstream rightStr("sources\\right.txt");

    string leftLine = "";
    string rightLine = "";

    bool areSame = true;
    while(true) {
        getline(leftStr, leftLine);
        getline(rightStr, rightLine);

        if (!leftStr && !rightStr) {
            break;
        }
        if (leftLine != rightLine) {
            areSame = false;
            break;
        }
    }

    if (areSame) {
        cout << "Files are same." << endl;
    } else {
        cout << "Files are different." << endl;
    }
    return 0;
}
