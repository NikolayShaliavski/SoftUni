#ifndef LIST_CPP
#define LIST_CPP

#include<string>
#include<sstream>
#include<iostream>
#include "List.h"

using namespace std;
//Node
List::Node::Node(T value, List::Node* prev, List::Node* next) :
    value(value),
    prev(prev),
    next(next) {
}
T List::Node::getValue() const {
    return this->value;
}
void List::Node::setValue(T value) {
    this->value = value;
}
List::Node* List::Node::getNext() const {
    return this->next;
}
void List::Node::setNext(List::Node* next) {
    this->next = next;
}
List::Node* List::Node::getPrev() const {
    return this->prev;
}
void List::Node::setPrev(List::Node* prev) {
    this->prev = prev;
}
//List
List::List() :
    head(nullptr),
    tail(nullptr),
    size(0) {
}
List::List(const List& other) :
    head(nullptr),
    tail(nullptr),
    size(0) {
    this->addAll(other);
}
List::~List() {
    this->removeAll();
}

T List::first() const {
    if (this->isEmpty()) {
        throw range_error("Cannot get first element of empty list");
    }
    return this->head->getValue();
}
void List::add(T value) {
    Node* newNode = new Node(value, nullptr, nullptr);
    if (this->isEmpty()) {
        this->head = newNode;
        this->tail = newNode;
    } else {
        newNode->setPrev(this->tail);
        this->tail->setNext(newNode);
        this->tail = newNode;
    }
    this->size++;
}
void List::addAll(const List& other) {
    /*
    if (other.isEmpty()) {
        return;
    }
    Node* otherHead = other.head;
    Node* otherTail = other.tail;
    if (this->isEmpty()) {
        this->head = otherHead;
        this->tail = otherTail;
    } else {
        this->tail->setNext(otherHead);
        otherHead->setPrev(this->tail);
        this->tail = otherTail;
    }
    this->size += other.size;
    */

    for (Node * node = other.head; node != nullptr; node = node->getNext()) {
        this->add(node->getValue());
    }

}
void List::removeFirst() {
    if (this->isEmpty()) {
        return;
    }
    if (this->head == this->tail) {
        //delete this->head;
        this->head = nullptr;
        this->tail = nullptr;
    } else {
        Node* nextNode = this->head->getNext();
        nextNode->setPrev(nullptr);
        delete this->head;
        this->head = nextNode;
    }
    this->size--;
}
void List::removeAll() {
    while(!this->isEmpty()) {
        this->removeFirst();
    }
}
size_t List::getSize() const {
    return this->size;
}
bool List::isEmpty() const {
    bool isEmpty = false;
    if (this->size == 0) {
        isEmpty = true;
    }
    return isEmpty;
}
string List::toString() const {
    ostringstream out;
    if (!this->isEmpty()) {
        Node* current = this->head;
        while(current != nullptr) {
            out << current->getValue();
            current = current->getNext();
            if (current != nullptr) {
                out << " ";
            }
        }
    }
    return out.str();
}
List List::getReversed(List l) {

}
List& List::operator<<(const T& value) {
    this->add(value);
    return *this;
}
List& List::operator<<(const List& other) {
    this->addAll(other);
    return *this;
}
List& List::operator=(const List& other) {
    if (this != &other) {
        this->removeAll();
        this->addAll(other);
    }
    return *this;
}
#endif // LIST_CPP
