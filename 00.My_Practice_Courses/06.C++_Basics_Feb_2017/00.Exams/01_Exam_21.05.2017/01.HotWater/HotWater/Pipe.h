#ifndef PIPE
#define PIPE


class Pipe {
private:
    int strength;
    int damage;
    int yearsRemaining;

public:
    Pipe(int initialStrength, int strength);
    void calculateYearsRemaining();
    //int getStrength();
    //int getDamage();
    int getYearsRemaining();
    //void decreaseStrength(int val);
    //void operator++();
};

#endif // PIPE
