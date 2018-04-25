#ifndef INBOX_H
#define INBOX_H

#include<string>
#include<sstream>
#include<vector>
#include<set>

using namespace std;

class Inbox {
public:
    class Email {
public:
    Email(string searchWord, string message) :
        wordOccurences(0),
        message(message) {
        this->calculateWordOccurences(searchWord);
    }
    string getMessage() const {
        return this->message;
    }
    int getWordOccurences() const {
        return this->wordOccurences;
    }

    bool operator< (const Email &other) const {
        if (this->getWordOccurences() == other.getWordOccurences()) {
            return true;
        }
        return this->getWordOccurences() < other.getWordOccurences();
    }

    void calculateWordOccurences(string searchWord) {
        vector<string> tokens;
        istringstream iss(this->message);
        string buf;
        while(iss >> buf) {
            tokens.push_back(buf);
        }
        for(string s : tokens) {
            if (s == searchWord) {
                this->wordOccurences++;
            }
        }
    }
private:
    int wordOccurences;
    string message;
};

    Inbox(int pageSize, string searchWord);
    void addEmail(string message);
    string toString() const;
private:
    int pageSize;
    string searchWord;
    set<Email> emails;
};
#endif // INBOX_H
