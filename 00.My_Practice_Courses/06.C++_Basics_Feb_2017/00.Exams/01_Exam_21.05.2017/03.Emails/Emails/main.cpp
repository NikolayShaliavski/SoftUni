#include<iostream>
#include<string>
#include<vector>
#include "Inbox.h"

using namespace std;

int main() {
    cin.sync_with_stdio(false);
    string searchWord;
    getline(cin, searchWord);
    int pageSize = 0;
    cin >> pageSize;

    string message = "";
    getline(cin, message);
    Inbox inbox(pageSize, searchWord);
    while(message != ".") {
        inbox.addEmail(message);
        getline(cin, message);
    }
    cout << inbox.toString() << endl;
    return 0;
}
