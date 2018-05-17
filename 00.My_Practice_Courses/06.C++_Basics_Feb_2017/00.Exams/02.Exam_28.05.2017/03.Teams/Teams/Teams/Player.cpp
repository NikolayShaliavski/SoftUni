#include "stdafx.h"

Player::Player(std::string name) :
	name(name),
	score(0)
{

}
void Player::incrementScore()
{
	this->score++;
}
int Player::getScore() const
{
	return this->score;
}