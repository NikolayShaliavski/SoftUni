#include <iostream>

using namespace std;

void makeTitlecase(string &text);

int main() {
    string text = "";
    getline(cin, text);

    makeTitlecase(text);

    cout << text << endl;
    return 0;
}

void makeTitlecase(string &text) {
    for(int i = 0; i < text.size(); i++) {
        char letter = text[i];
        bool makeUpper = true;
        if (i > 0) {
            char leftChar = text[i - 1];
            if (isalpha(leftChar)) {
                makeUpper = false;
            }
        }
        if (makeUpper) {
            text[i] = toupper(letter);
        }
    }
}
