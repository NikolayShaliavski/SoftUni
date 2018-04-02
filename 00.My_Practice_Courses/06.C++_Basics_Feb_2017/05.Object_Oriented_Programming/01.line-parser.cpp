#include <iostream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

class LineParser {
private:
    string line;

public:
    LineParser(string line) :
        line(line) {
    }

    int * getNumberArray(int & length) const {
        istringstream inStream(this->line);
        vector<int> nums;
        int number = 0;
        while (inStream >> number) {
            nums.push_back(number);
        }
        length = nums.size();
        int * result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = nums[i];
        }
        return result;
    }

    string * getStringArray (int & length) const {
        istringstream inStream(this->line);
        vector<string> nums;
        string word = "";
        while (inStream >> word) {
            nums.push_back(word);
        }
        length = nums.size();
        string * result = new string[length];
        for (int i = 0; i < length; i++) {
            result[i] = nums[i];
        }
        return result;
    }

    char * getCharArray (int & length) const {
        vector<char> letters;
        for (int i = 0; i < this->line.size(); i++) {
            char letter = this->line[i];
            if (letter != ' ') {
                letters.push_back(letter);
            }
        }
        char * result = new char[length];
        length = letters.size();
        for(int i = 0; i < length; i++) {
            result[i] = letters[i];
        }
        return result;
    }
};

int main() {
    string numTest = "1 2 3 4 5";
    string wordTest = "1a 2a 3a 4a 5a";
    LineParser parser(wordTest);

    int length = 0;
    /*
    int * res = parser.getNumberArray(length);
    for (int i = 0; i < length; i++) {
        cout << res[i] << " ";
    }
    cout << endl;

    string * res = parser.getStringArray(length);
    for (int i = 0; i < length; i++) {
        cout << res[i] << " ";
    }
    cout << endl;
    */

    char * res = parser.getCharArray(length);
    for (int i = 0; i < length; i++) {
        cout << res[i] << "";
    }
    cout << endl;

    delete[] res;
    return 0;
}
