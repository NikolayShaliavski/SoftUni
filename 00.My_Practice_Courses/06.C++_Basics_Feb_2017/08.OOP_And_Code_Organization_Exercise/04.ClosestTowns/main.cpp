#include<iostream>
#include<float.h>

#include "City.h"
#include "Vector2D.h"

using namespace std;

int main() {
    int lines;
    cin >> lines;
    City *cities[lines];
    for(int i = 0; i < lines; i++) {
        string name;
        double x;
        double y;
        cin >> name;
        cin >> x;
        cin >> y;
        cities[i] = new City(name, x, y);
    }
    int leftCityIndex = 0;
    int rightCityIndex = 0;
    double bestDistance = DBL_MAX;
    for(int i = 0; i < lines; i++) {
        City *leftCity = cities[i];
        for(int k = i + 1; k < lines; k++) {
            City *rightCity = cities[k];
            double currentDistance = leftCity->getDistance(*rightCity);
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                leftCityIndex = i;
                rightCityIndex = k;
            }
        }
    }
    cout << cities[leftCityIndex]->getName() << "-" << cities[rightCityIndex]->getName() << endl;
    return 0;
}
