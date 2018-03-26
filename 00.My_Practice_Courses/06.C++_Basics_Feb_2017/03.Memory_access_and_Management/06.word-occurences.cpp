#include <iostream>
#include <string>

using namespace std;

int countOccurences(const string &text, const string &word);

int main() {
    string text = "";
    getline(cin, text);
    string word = "";
    getline(cin, word);

    int wordOccurences = countOccurences(text, word);

    printf("Word %s found %d times.", word.c_str(), wordOccurences);
    return 0;
}

int countOccurences(const string &text, const string &word) {
    int counter = 0;
    const int wordLength = word.size();

    std::size_t found = text.find(word);
    while(found != string::npos) {
        counter++;
        found = text.find(word, found + 1);
    }
    return counter;
}
