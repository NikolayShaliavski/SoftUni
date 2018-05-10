#include<string>
#include "Visitor.h"

Visitor::Visitor() :
    id(nullptr),
    name(nullptr),
    age(0),
    entries(0) {
}
Visitor::Visitor(std::string id, std::string name, int age) :
    id(id),
    name(name),
    age(age),
    entries(0) {
}

std::string Visitor::getId() const {
    return this->id;
}
std::string Visitor::getName() const {
    return this->name;
}
int Visitor::getAge() const {
    return this->age;
}
int Visitor::getEntries() const {
    return this->entries;
}
void Visitor::operator++(int) {
    this->entries++;
}
