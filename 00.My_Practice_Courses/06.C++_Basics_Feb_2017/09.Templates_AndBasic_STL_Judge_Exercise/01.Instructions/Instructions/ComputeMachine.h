#ifndef COMPUTE_MACHINE
#define COMPUTE_MACHINE
#include<string>

class ComputeMachine {
public:
    ComputeMachine();
    ComputeMachine(const ComputeMachine & other);
    ~ComputeMachine();
    void insertNumber(int number);
    void handleOperation(const std::string line);
    void print() const;
private:
    int * arr;
    size_t length;
    size_t capacity;

    void copyValues(const ComputeMachine & other);
    int combine(int a, int b);
};

#endif // COMPUTE_MACHINE
