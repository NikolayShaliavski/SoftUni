#include<string>
#include"TransportTime.h"

TransportTime::TransportTime(std::string time) :
    hours(0),
    minutes(0) {
    this->convert(time);
}
void TransportTime::convert(const std::string& time) {
    std::string hoursStr = time.substr(0, 2);
    std::string minutesStr = time.substr(2);
    this->hours = std::stoi(hoursStr);
    this->minutes = std::stoi(minutesStr);
}
int TransportTime::getDifference(const TransportTime& other) const {
    int diff = 0;
    diff += (other.hours - this->hours) * 60;
    diff += (other.minutes - this->minutes);

    return diff;
}
