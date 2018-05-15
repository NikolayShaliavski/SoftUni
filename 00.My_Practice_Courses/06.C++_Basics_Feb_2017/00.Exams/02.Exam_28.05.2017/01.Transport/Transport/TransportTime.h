#ifndef TRANSPORT_TIME
#define TRANSPORT_TIME

#include<string>

class TransportTime {
public:
    TransportTime(std::string time);
    int getDifference(const TransportTime& other) const;
    void convert(const std::string& time);
private:
    int hours;
    int minutes;
};
#endif // TRANSPORT_TIME
