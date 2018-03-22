#include <iostream>
#include <string>
#include <cstddef>
#include <vector>
#include <sstream>
#include <strstream>

using namespace std;

vector<string> parseText(string text);

int main() {
    string text = "@ test, ;,test, ; ,third @, , ,";
    getline(cin, text);
    string word = "";
    cin >> word;
    double percents = 0;
    cin >> percents;

    vector<string> words = parseText(text);
    int allMatches = 0;
    for (vector<string>::iterator it = words.begin(); it != words.end(); it++) {
        //cout << " | " << *it;
        string current = *it;
        if (word.size() == current.size()) {
            int length = word.size();
            double lettersMatch = 0;
            char firstLeft = word[0];
            char firstRight = current[0];
            if (firstLeft == firstRight) {
                for(int i = 0; i < word.size(); i++) {
                    char left = word[i];
                    char right = current[i];
                    if (left == right) {
                        lettersMatch++;
                    }
                }
                double matchRes = lettersMatch / length * 100;
                if (matchRes >= percents) {
                    allMatches++;
                }
            }
        }
    }
    cout << allMatches << endl;
    return 0;
}

vector<string> parseText(string text) {
    const string delimiters = " .,;!?";
    vector<string> words;
    size_t prev = 0;
    size_t pos = 0;
    while (pos != string::npos)
    {
        pos = text.find_first_of(delimiters, prev);
        if (pos > prev) {
            string current = text.substr(prev, pos-prev);
            if (!current.empty()) {
                words.push_back(current);
            }
        } else if (pos == string::npos) {
            string current = text.substr(pos);
            if (!current.empty()) {
                words.push_back(current);
            }
        }
        prev = pos + 1;
    }
    return words;
}
