#include<iostream>
#include<sstream>
#include<string>
#include "ComputeMachine.h"

int main() {
    /*ComputeMachine compMachine;
    compMachine.insertNumber(1);
    compMachine.insertNumber(2);
    compMachine.insertNumber(3);
    compMachine.insertNumber(4);
    compMachine.handleOperation("sum");

    compMachine.handleOperation("subtract");
    compMachine.handleOperation("concat");
    compMachine.print();*/


    ComputeMachine compMachine;
    std::string line;
    getline(std::cin, line);
    while(line != "end") {
        std::size_t spaceFound = line.find(" ");
        if (spaceFound == std::string::npos) {
            int number;
            std::istringstream inStr(line);
            if (inStr >> number) {
                compMachine.insertNumber(number);
            } else {
                compMachine.handleOperation(line);
            }
        } else {
            compMachine.handleOperation(line);
        }

        getline(std::cin, line);
    }
    compMachine.print();
    return 0;
}
