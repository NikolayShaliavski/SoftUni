#include <iostream>
using namespace std;

int main() {
    string text = "";
    getline(cin, text);
    string searchStr = "";
    string replacement = "";
    cin >> searchStr;
    cin >> replacement;

    int replaceLength = searchStr.size();
    int pos = text.find(searchStr);
    while(pos != string::npos) {
        text.replace(pos, replaceLength, replacement);
        pos = text.find(searchStr, pos + 1);
    }
    cout << text << endl;
    return 0;
}
