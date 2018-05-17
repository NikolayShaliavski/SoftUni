#ifndef EXTENDED_INTERPRETER_H
#define EXTENDED_INTERPRETER_H

#include <memory>
#include "MemoryStorage.h"
#include "MemoryRecallOperation.h"
#include "DivisionOperation.h"
#include "InputInterpreter.h"
#include "CalculationEngine.h"

class ExtendedInterpreter : public InputInterpreter {
	std::shared_ptr<MemoryStorage> memory;
public:
	ExtendedInterpreter(CalculationEngine& engine) : InputInterpreter(engine) {}

	std::shared_ptr<Operation> getOperation(std::string operation) override {
		if (operation == "/") {
			return std::make_shared<DivisionOperation>();
		}
		else if (operation == "ms") {
			if (!this->memory) {
				this->memory = std::make_shared<MemoryStorage>();
			}
			return this->memory;
		} else if (operation == "mr") {
			return std::make_shared<MemoryRecallOperation>(this->memory);
		}
		return InputInterpreter::getOperation(operation);
	}
};
#endif // EXTENDED_INTERPRETER_H

