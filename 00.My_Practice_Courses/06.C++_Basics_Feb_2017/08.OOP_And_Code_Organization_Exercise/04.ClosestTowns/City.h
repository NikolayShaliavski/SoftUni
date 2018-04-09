#include<string>
#include "Vector2D.h"
#ifndef CITY_H
#define CITY_H

using namespace std;

class City {
private:
    string name;
    Vector2D coordinates;

public:
    City(string name, double x, double y) :
        name(name),
        coordinates(x, y) {
    }
    string getName() const {
        return this->name;
    }
    Vector2D getCoordinates() const {
        return this->coordinates;
    }
    double getDistance(const City &other) {
        double distance = 0;
        Vector2D mergedCoordinates = this->coordinates - other.getCoordinates();

        distance = mergedCoordinates.getLength();
        return distance;
    }
};
#endif // CITY_H
