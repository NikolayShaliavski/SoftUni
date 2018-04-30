#include<iostream>
#include<string>
#include "ComputeMachine.h"

static const int ARR_SIZE = 3;

ComputeMachine::ComputeMachine() :
    arr(new int[ARR_SIZE]),
    length(0),
    capacity(ARR_SIZE) {
}
ComputeMachine::ComputeMachine(const ComputeMachine & other) :
    arr(other.arr),
    capacity(other.capacity),
    length(other.length) {
}
ComputeMachine::~ComputeMachine() {
    delete[] this->arr;
}
void ComputeMachine::insertNumber(int number) {
    if (this->length == this->capacity) {
        this->capacity += ARR_SIZE;
        this->copyValues(*this);
    }
    this->arr[this->length] = number;
    this->length++;
}
void ComputeMachine::handleOperation(const std::string line) {
    if (line == "sum") {
        int last = this->arr[this->length - 1];
        int prevLast = this->arr[this->length - 2];
        int sum = last + prevLast;

        this->arr[this->length - 1] = 0;
        this->arr[this->length - 2] = 0;
        this->length--;
        this->arr[this->length - 1] = sum;
    } else if (line== "subtract") {
        int last = this->arr[this->length - 1];
        int prevLast = this->arr[this->length - 2];
        int subtracted = last - prevLast;

        this->arr[this->length - 1] = 0;
        this->arr[this->length - 2] = 0;
        this->length--;
        this->arr[this->length - 1] = subtracted;
    } else if (line == "concat") {
        int last = this->arr[this->length - 1];
        int prevLast = this->arr[this->length - 2];
        int concatenated = this->combine(prevLast, last);

        this->arr[this->length - 1] = 0;
        this->arr[this->length - 2] = 0;
        this->length--;
        this->arr[this->length - 1] = concatenated;
    } else if (line == "discard") {
        this->arr[this->length - 1] = 0;
        this->length--;
    }
}
void ComputeMachine::print() const {
    for(int i = 0; i < this->length; i++) {
        std::cout << this->arr[i] << std::endl;
    }
}
void ComputeMachine::copyValues(const ComputeMachine & other) {
    this->length = other.length;
    this->capacity = other.capacity;
    int * newArr = new int[other.capacity];
    for(int i = 0; i < this->length; i++) {
        newArr[i] = other.arr[i];
    }
    delete[] this->arr;
    this->arr = newArr;
}
int ComputeMachine::combine(int a, int b) {
   /*int times = 1;
   while (times <= b)
      times *= 10;
   return a*times + b;*/

   int times = 1;
   do {
      times *= 10;
   } while(times <= b);
   return a*times + b;
}
