#include<iostream>
#include<string>
#include<sstream>
#include<vector>
#include<limits>

#include"TransportTime.h"
std::vector<TransportTime> parseBusTimes(std::string line);

int main() {
    std::string line;
    getline(std::cin, line);
    std::vector<TransportTime> busTimes = parseBusTimes(line);

    std::string trainTimeStr;
    getline(std::cin, trainTimeStr);
    TransportTime trainTime(trainTimeStr);

    int bestDiff = std::numeric_limits<int>::max();
    for(int i = 0; i < busTimes.size(); i++) {
        int currDiff = busTimes[i].getDifference(trainTime);
        if (currDiff >= 0 &&
            currDiff < bestDiff) {
            bestDiff = currDiff;
        }
    }

    std::cout << bestDiff << std::endl;
    return 0;
}
std::vector<TransportTime> parseBusTimes(std::string line) {
    std::vector<TransportTime> busTimes;
    std::istringstream iss(line);
    std::string part;
    while(iss >> part) {
        busTimes.push_back(TransportTime(part));
    }
    return busTimes;
}
