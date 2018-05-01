#include<iostream>
#include<sstream>
#include<string>
#include<stdlib.h>
#include<bitset>

static const int MAX_DNAS = 1048576;

int main() {
    char symbol;
    std::cin >> symbol;
    std::bitset<MAX_DNAS> dnaNumbers;

    while(symbol != '.') {
        char arr[6];
        arr[0] = symbol;
        for(int i = 0; i < 4; i++) {
            std::cin >> arr[i + 1];
        }

        //std::string line(arr);
        int dnaNum = (int)strtol(arr, 0, 16);
        if (dnaNumbers[dnaNum]) {
            dnaNumbers.reset(dnaNum);
        } else {
            dnaNumbers.set(dnaNum, 1);
        }

        std::cin >> symbol;
    }
    for(int i = 1; i < MAX_DNAS; i++) {
        //if (dnaNumbers[i] % 2 != 0) {
        if (dnaNumbers[i]) {
            std::cout << std::hex << i;
            break;
        }
    }
    return 0;
}

/*
int main() {
    std::string line;
    getline(std::cin, line);


    //int * dnaNumbers = new int[MAX_DNAS];
    bool * dnaNumbers = new bool[MAX_DNAS];
    int index = 0;
    char startSymbol = line[index];
    while(startSymbol != '.') {
        std::string currDna = line.substr(index, 5);
        int dnaNum = (int)strtol(currDna.c_str(), 0, 16);
        //dnaNumbers[dnaNum]++;
        dnaNumbers[dnaNum] = !dnaNumbers[dnaNum];
        index += 5;
        startSymbol = line[index];
    }


    for(int i = 1; i < MAX_DNAS; i++) {
        //if (dnaNumbers[i] % 2 != 0) {
        if (dnaNumbers[i]) {
            std::cout << std::hex << i;
            break;
        }
    }
    delete[] dnaNumbers;
    return 0;
}
*/
