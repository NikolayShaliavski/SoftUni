#include <iostream>
#include <cstdlib>
#include <set>
#include "IndexedSet.h"

IndexedSet::IndexedSet() :
    valuesSet(),
    valuesArray(nullptr) {
}
IndexedSet::IndexedSet(const IndexedSet& other) :
    valuesSet(other.valuesSet),
    valuesArray(nullptr) {
    this->buildIndex();
}
IndexedSet::~IndexedSet() {
    this->clearIndex();
}
void IndexedSet::add(const Value& v) {
    this->valuesSet.insert(v);
    this->clearIndex();
}
const Value& IndexedSet::operator[](size_t index) {
    if (index < 0 || index >= this->size()) {
        throw std::out_of_range("Index out of range");
    }
    if (this->valuesArray == nullptr) {
        this->buildIndex();
    }
    return this->valuesArray[index];
}
IndexedSet& IndexedSet::operator=(const IndexedSet& other) {
    if (this != &other) {
        this->clearIndex();
        this->valuesSet = other.valuesSet;

        this->buildIndex();
    }
    return *this;
}
void IndexedSet::buildIndex() {
    this->clearIndex();

    size_t currentSize = this->size();
    size_t counter = 0;
    this->valuesArray = new Value[currentSize];
    for(auto v : this->valuesSet) {
        this->valuesArray[counter++] = v;
    }
}
void IndexedSet::clearIndex() {
    if (this->valuesArray != nullptr) {
        delete[] this->valuesArray;
        this->valuesArray = nullptr;
    }
}
size_t IndexedSet::size() const {
    return this->valuesSet.size();
}
