#include <iostream>

using namespace std;

bool validateBrackets(const string &expression);

int main() {
    string expression = "";
    cin >> expression;

    bool bracketsValid = validateBrackets(expression);
    if (bracketsValid) {
        cout << "Brackets in the expression are valid." << endl;
    } else {
        cout << "Brackets in the expression are NOT valid." << endl;
    }
    return 0;
}

bool validateBrackets(const string &expression) {
    int counter = 0;
    for(int i = 0; i < expression.size(); i++) {
        char current = expression[i];
        if (current == '(') {
            counter++;
        } else if (current == ')') {
            counter--;
        }
    }
    if (counter == 0) {
        return true;
    }
    return false;
}
