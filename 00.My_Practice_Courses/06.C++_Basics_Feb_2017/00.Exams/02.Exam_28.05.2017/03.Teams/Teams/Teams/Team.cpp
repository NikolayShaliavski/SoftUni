#include "stdafx.h"

Team::Team(std::string name) :
	name(name),
	wins(0)
{

}
void Team::addPlayer(Player* p)
{
	this->players.push_back(p);
}
void Team::addWin()
{
	this->wins++;
	for (auto p : this->players)
	{
		p->incrementScore();
	}
}