#include<iostream>
#include<sstream>
#include<string>
#include<map>
#include<vector>

#include "Visitor.h"

std::vector<std::string> split(const std::string& str);

int main() {
    std::map<std::string, Visitor> visitors;
    std::string line;
    getline(std::cin, line);
    std::vector<int> out;
    while(line != "end") {
        std::vector<std::string> splitted = split(line);
        std::string command = splitted[0];
        if (command == "entry") {
            std::string id = splitted[1];
            std::string name = splitted[2];
            int age = std::stoi(splitted[3]);

            if (visitors.find(id) == visitors.end()) {
                visitors.insert(std::make_pair(id, Visitor(id, name, age)));
            }
            visitors[id]++;

        } else if (command == "name") {
            std::string name = splitted[1];
            int counter = 0;
            std::map<std::string, Visitor>::iterator it;
            for (it = visitors.begin(); it != visitors.end(); ++it) {
                if (it->second.getName() == name) {
                    counter++;
                }
            }
            out.push_back(counter);
        } else if (command == "age") {
            int minAge = std::stoi(splitted[1]);
            int maxAge = std::stoi(splitted[2]);
            int counter = 0;
            std::map<std::string, Visitor>::iterator it;
            for (it = visitors.begin(); it != visitors.end(); ++it) {
                if (it->second.getAge() >= minAge &&
                    it->second.getAge() < maxAge) {
                    counter++;
                }
            }
            out.push_back(counter);
        }
        getline(std::cin, line);
    }
    std::vector<int>::iterator it;
    for(it = out.begin(); it != out.end(); ++it) {
        std::cout << *it << std::endl;
    }
    return 0;
}

std::vector<std::string> split(const std::string& str) {
    std::vector<std::string> parts;
    std::istringstream iss(str);
    do {
        std::string part;
        iss >> part;
        parts.push_back(part);
    } while(iss);
    return parts;
}
