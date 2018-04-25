#include "Inbox.h"
#include<iostream>
#include<string>
#include<sstream>
#include<set>

using namespace std;

Inbox::Inbox(int pageSize, string searchWord) :
    pageSize(pageSize),
    searchWord(searchWord) {
    for(int i = 0; i < pageSize; i++) {
        Email emptyEmail("", "");
        this->emails.insert(emptyEmail);
    }
}
void Inbox::addEmail(string message) {
    Email newEmail(this->searchWord, message);
    int newOccurences = newEmail.getWordOccurences();
    auto firstIt = this->emails.begin();
    Email first = *firstIt;
    if (first.getWordOccurences() < newOccurences) {
        this->emails.erase(firstIt);
        this->emails.insert(newEmail);
    }
}
string Inbox::toString() const {
    ostringstream out;
    for (set<Email>::reverse_iterator i = this->emails.rbegin(); i != this->emails.rend(); ++i ) {
        Email current = *i;
        out << current.getMessage() << endl;
    }
    return out.str();
}
