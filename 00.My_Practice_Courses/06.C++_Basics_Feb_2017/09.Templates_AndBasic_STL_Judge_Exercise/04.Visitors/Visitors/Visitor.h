#ifndef VISITOR
#define VISITOR

#include<string>

class Visitor {
public:
    Visitor();
    Visitor(std::string id, std::string name, int age);
    std::string getId() const;
    std::string getName() const;
    int getAge() const;
    int getEntries() const;
    void operator++(int);
private:
    std::string id;
    std::string name;
    int age;
    int entries;
};
#endif // VISITOR
