// Teams.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

std::map<std::string, Player*> players;
std::map<std::string, Team*> teams;

void readTeams(const int numberOfTeams);
void readGames(const int numberOfGames);

int main()
{
	std::cin.sync_with_stdio(false);
	std::cout.sync_with_stdio(false);

	int numberOfTeams = 0;
	std::cin >> numberOfTeams;
	std::cin.ignore();
	readTeams(numberOfTeams);

	int numberOfGames = 0;
	std::cin >> numberOfGames;
	std::cin.ignore();
	readGames(numberOfGames);

	for (auto p : players)
	{
		std::cout << p.second->getScore() << " ";
	}
	std::cout << std::endl;
	
    return 0;
}
void readTeams(const int numberOfTeams)
{
	for (size_t i = 0; i < numberOfTeams; i++)
	{
		std::string line;
		getline(std::cin, line);
		std::istringstream iss(line);
		std::string teamName;
		iss >> teamName;
		Team* team = new Team(teamName);
		teams.insert(std::make_pair(teamName, team));
		std::string playersNum;
		iss >> playersNum;
		int numberOfPlayers = std::stoi(playersNum);
		for (int i = 0; i < numberOfPlayers; i++)
		{
			std::string playerName;
			iss >> playerName;
			if (players.find(playerName) == players.end())
			{
				Player* p = new Player(playerName);
				players.insert(std::make_pair(playerName, p));
				team->addPlayer(p);
			}
			else
			{
				team->addPlayer(players.find(playerName)->second);
			}
		}
	}
}
void readGames(const int numberOfGames)
{
	for (int i = 0; i < numberOfGames; i++)
	{
		std::string winner;
		std::cin >> winner;
		if (teams.find(winner) != teams.end())
		{
			Team* t = teams.find(winner)->second;
			t->addWin();
		}
	}
}

