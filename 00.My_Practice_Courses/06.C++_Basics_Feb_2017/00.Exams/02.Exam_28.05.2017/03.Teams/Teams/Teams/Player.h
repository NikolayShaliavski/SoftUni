#ifndef  PLAYER
#define PLAYER

#include <string>

class Player
{
public:
	Player(std::string name);
	void incrementScore();
	int getScore() const;
private:
	std::string name;
	int score;
};

#endif // ! PLAYER
