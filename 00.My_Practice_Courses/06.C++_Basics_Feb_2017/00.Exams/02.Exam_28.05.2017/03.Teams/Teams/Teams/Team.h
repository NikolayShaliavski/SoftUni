#ifndef TEAM
#define TEAM

#include <string>
#include <vector>
#include "Player.h"

class Team
{
public:
	Team(std::string name);
	void addWin();
	void addPlayer(Player* p);
private:
	std::string name;
	int wins;
	std::vector<Player*> players;
};

#endif // !TEAM