#include <iostream>
#include <sstream>

using namespace std;

string findLongestSequence(const int arr[], const int arrLength);

int main() {
    int elementsCount = 0;
    cin >> elementsCount;

    int arr[elementsCount];
    for(int i = 0; i < elementsCount; i++) {
        cin >> arr[i];
    }
    string res = findLongestSequence(arr, elementsCount);
    cout << res << endl;

    return 0;
}

string findLongestSequence(const int arr[], const int arrLength) {
    ostringstream out;

    int startIndex = 0;
    int maxIndex = 0;
    int sequenceLength = 1;
    int maxLength = -1;
    for(int i = 0; i < arrLength - 1; i++) {
        int leftNum = arr[i];
        int rightNum = arr[i + 1];
        if (leftNum == rightNum) {
            sequenceLength++;
            if (sequenceLength > maxLength) {
                maxLength = sequenceLength;
                maxIndex = startIndex;
            }
        } else {
            if (sequenceLength > maxLength) {
                maxIndex = startIndex;
                maxLength = sequenceLength;
            }
            startIndex = i + 1;
            sequenceLength = 1;
        }
    }

    int endIndex = maxIndex + maxLength;
    for(int i = maxIndex; i < endIndex; i++) {
        out << arr[i];
        if (i < endIndex - 1) {
            out << " ";
        }
    }
    return out.str();
}
