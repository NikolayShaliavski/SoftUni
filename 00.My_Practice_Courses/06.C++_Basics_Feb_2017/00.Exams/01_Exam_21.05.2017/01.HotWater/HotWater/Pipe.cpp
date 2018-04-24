#include "Pipe.h"
#include <iostream>

using namespace std;

Pipe::Pipe(int initialStrength, int strength) :
    strength(strength),
    yearsRemaining(0) {
    this->damage = initialStrength - strength;
}
Pipe::getYearsRemaining() {
    return this->yearsRemaining;
}
void Pipe::calculateYearsRemaining() {
    while(this->strength >= this->damage) {
        this->strength -= this->damage;
        this->yearsRemaining++;
    }
}
