#ifndef INITIALIZATION_H
#define INITIALIZATION_H

#include "CommandInterface.h"

#include <memory>
#include <string>

class CutTransform : public TextTransform {
public:
    virtual void invokeOn(std::string &text, int startIndex, int endIndex) override {
        if (startIndex == endIndex) {
            this->clearLastCut();
        } else {
            this->lastCut = text.substr(startIndex, endIndex - startIndex);
            text.erase(startIndex, endIndex - startIndex);
        }
    }
    std::string getLastCut() const {
        return this->lastCut;
    }
private:
    std::string lastCut;
    void clearLastCut() {
        this->lastCut = "";
    }
};

class PasteTransform : public TextTransform {
    std::shared_ptr<CutTransform> cutTransform;
public:
    PasteTransform(std::shared_ptr<CutTransform> cutTransform) :
        cutTransform(cutTransform) {}

    virtual void invokeOn(std::string &text, int startIndex, int endIndex) override {
        if (startIndex == endIndex) {
            std::string &replacedPtr = text.insert(startIndex, this->cutTransform->getLastCut());
        } else {
            std::string &replacedPtr = text.replace(startIndex, endIndex - startIndex, this->cutTransform->getLastCut());
        }
    }
};

class ExtendedCommandInterface : public CommandInterface {
public:
    ExtendedCommandInterface(std::string& text) :
        CommandInterface(text)
    {}
protected:
    virtual std::vector<Command> initCommands() override {
        std::vector<Command> commands = CommandInterface::initCommands();

        std::shared_ptr<CutTransform> cutTransform = std::make_shared<CutTransform>();
        std::shared_ptr<PasteTransform> pasteTransform = std::make_shared<PasteTransform>(cutTransform);

        commands.push_back(Command("cut", cutTransform));
        commands.push_back(Command("paste", pasteTransform));
        return commands;
    }
};

std::shared_ptr<CommandInterface> buildCommandInterface(std::string& text) {
    auto interface = std::make_shared<ExtendedCommandInterface>(text);
    interface->init();
    return interface;
}

#endif // INITIALIZATION_H
