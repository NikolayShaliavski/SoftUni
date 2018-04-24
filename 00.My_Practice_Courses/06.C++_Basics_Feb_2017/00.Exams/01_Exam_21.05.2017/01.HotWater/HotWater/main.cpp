#include <iostream>
#include <sstream>
#include <vector>
#include "Pipe.h"

using namespace std;

int main() {
    vector<int> firstMeasurements;
    vector<int> secondMeasurements;

    string line = "";
    getline(cin, line);
    istringstream first(line);
    int num = 0;
    while(first >> num) {
        firstMeasurements.push_back(num);
    }
    getline(cin, line);
    istringstream second(line);
    while(second >> num) {
        secondMeasurements.push_back(num);
    }
    for(int i = 0; i < firstMeasurements.size(); i++) {
        int firstNum = firstMeasurements[i];
        int secondNum = secondMeasurements[i];

        Pipe pipe(firstNum, secondNum);
        pipe.calculateYearsRemaining();
        cout << pipe.getYearsRemaining();
        if (i < firstMeasurements.size() - 1) {
            cout << " ";
        }
    }
    cout << endl;

    return 0;
}
