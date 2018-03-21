#include <iostream>
#include <string>
#include <sstream>
using namespace std;

string getAverage(string left, string right);
signed int parseHex(string hexStr);
string backToHex(signed int num);

int main() {
    string first = "";
    string second = "";
    cin >> first;
    cin >> second;

    string res = "#";
    for(int i = 1; i < first.size(); i += 2) {
        string left = first.substr(i, 2);
        string right = second.substr(i, 2);
        res += getAverage(left, right);
    }
    cout << res << endl;
    return 0;
}

string getAverage(string left, string right) {
    signed int leftNum = parseHex(left);
    signed int rightNum = parseHex(right);
    signed int average = (leftNum + rightNum) / 2;

    string resHex = backToHex(average);
    return resHex;
}

signed int parseHex(string hexStr) {
    signed int num;
    std::stringstream ss;
    ss << std::hex << hexStr;
    ss >> num;
    return num;
}

string backToHex(signed int num) {
    string hexStr;
    std::stringstream ss;
    ss << std::hex << num;
    ss >> hexStr;
    if (num == 0) {
        hexStr = "00";
    }
    return hexStr;
}
