#ifndef MEMORY_RECALL_OPERATION_H
#define MEMORY_RECALL_OPERATION_H
#include <memory>

#include "Operation.h"
#include "MemoryStorage.h"

class MemoryRecallOperation : public Operation {
	std::shared_ptr<MemoryStorage> memory;
public:
	MemoryRecallOperation(std::shared_ptr<MemoryStorage> mem) : memory(mem) {}
	void addOperand(int operand) override {
	}
	bool isCompleted() override {
		return true;
	}
	int getResult() override {
		return this->memory->getResult();
	}
};
#endif // !MEMORY_RECALL_OPERATION_H
