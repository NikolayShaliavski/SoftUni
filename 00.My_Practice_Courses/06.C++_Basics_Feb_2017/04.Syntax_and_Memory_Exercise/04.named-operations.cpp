#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <unordered_map>
#include <limits>

using namespace std;

vector<int> getArr(string line);
int sum(vector<int> &arr, int startIndex, int endIndex);
int avg(vector<int> &arr, int startIndex, int endIndex);
int getMin(vector<int> &arr, int startIndex, int endIndex);
int getMax(vector<int> &arr, int startIndex, int endIndex);

int main() {
    string line = "";
    getline(cin, line);
    vector<int> arr = getArr(line);
    int namesCount = 0;
    cin >> namesCount;

    map<string, int> operations;
    for(int i = 0; i < namesCount; i++) {
        string operation = "";
        cin >> operation;
        int index = 0;
        cin >> index;
        operations.insert(make_pair(operation, index));
    }

    int counter = 0;
    multimap<int, vector<int>> result;
    vector<string> commands;
    string command = "";
    while(true) {
        cin >> command;
        if (command == "end") {
            break;
        }
        int startIndex = 0;
        cin >> startIndex;
        int endIndex = 0;
        cin >> endIndex;
        int index = operations[command];
        vector<int> commandRes;
        commandRes.push_back(startIndex);
        commandRes.push_back(endIndex);
        int calculationRes = 0;
        switch(index) {
            case 0:
                calculationRes = sum(arr, startIndex, endIndex);
                break;
            case 1:
                calculationRes = avg(arr, startIndex, endIndex);
                break;
            case 2:
                calculationRes = getMin(arr, startIndex, endIndex);
                break;
            case 3:
                calculationRes = getMax(arr, startIndex, endIndex);
                break;
        }
        commandRes.push_back(calculationRes);
        result.insert(make_pair(counter, commandRes));
        commands.push_back(command);
        counter++;
    }

    counter = 0;
    cout << "[" << commands.size() << "]{";
    for(string opName : commands) {
        vector<int> opRes = result.find(counter)->second;
        printf("%s(%d,%d)=%d", opName.c_str(), opRes[0], opRes[1], opRes[2]);
        if (counter < result.size() - 1) {
            cout << ",";
        }
        counter++;
    }
    cout << "}" << endl;
    return 0;
}

int sum(vector<int> &arr, int startIndex, int endIndex) {
    int sum = 0;
    for(int i = startIndex; i < endIndex; i++) {
        sum += arr[i];
    }
    return sum;
}
int avg(vector<int> &arr, int startIndex, int endIndex) {
    int sum = 0;
    for(int i = startIndex; i < endIndex; i++) {
        sum += arr[i];
    }
    return sum / (endIndex - startIndex);
}
int getMin(vector<int> &arr, int startIndex, int endIndex) {
    int minElement = INT_MAX;
    for(int i = startIndex; i < endIndex; i++) {
        if (arr[i] < minElement) {
            minElement = arr[i];
        }
    }
    return minElement;
}
int getMax(vector<int> &arr, int startIndex, int endIndex) {
    int maxElement = INT_MIN;
    for(int i = startIndex; i < endIndex; i++) {
        if (arr[i] > maxElement) {
            maxElement = arr[i];
        }
    }
    return maxElement;
}
vector<int> getArr(string line) {
    const string delimiters = " ";
    vector<int> arr;
    size_t prev = 0;
    size_t pos = 0;
    while (pos != string::npos)
    {
        pos = line.find_first_of(delimiters, prev);
        if (pos > prev) {
            string current = line.substr(prev, pos-prev);
            if (!current.empty()) {
                arr.push_back(std::stoi(current));
            }
        } else if (pos == string::npos) {
            string current = line.substr(pos);
            if (!current.empty()) {
                arr.push_back(std::stoi(current));
            }
        }
        prev = pos + 1;
    }
    return arr;
}
