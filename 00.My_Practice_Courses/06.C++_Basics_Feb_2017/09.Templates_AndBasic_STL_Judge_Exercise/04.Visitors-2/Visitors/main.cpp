#include<iostream>
#include<sstream>
#include<string>
#include<map>
#include<set>
#include<vector>

std::vector<std::string> split(const std::string& str);

int main() {
    std::set<std::string> ids;
    std::map<std::string, int> names;
    int ages[101] = {0};

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

            if (ids.find(id) == ids.end()) {
                ids.insert(id);
                if (names.find(name) == names.end()) {
                    names.insert(std::make_pair(name, 0));
                }
                names.find(name)->second++;
                ages[age]++;
            }
        } else if (command == "name") {
            std::string name = splitted[1];
            if (names.find(name) != names.end()) {
                out.push_back(names.find(name)->second);
            } else {
                out.push_back(0);
            }

        } else if (command == "age") {
            int minAge = std::stoi(splitted[1]);
            int maxAge = std::stoi(splitted[2]);
            int counter = 0;
            for(int i = minAge; i < maxAge; i++) {
                counter += ages[i];
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
