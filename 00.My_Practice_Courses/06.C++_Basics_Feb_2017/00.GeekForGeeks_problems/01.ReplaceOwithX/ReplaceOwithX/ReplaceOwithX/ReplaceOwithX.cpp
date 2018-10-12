// ReplaceOwithX.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <sstream>
#include <vector>

void print(std::vector<std::vector<char>> matrix);
std::string saveOut(std::vector<std::vector<char>> matrix);
void updateMatrix(std::vector<std::vector<char>> &matrix, std::vector<std::vector<bool>> &visited, int rows, int cols);
bool isInRange(int row, int col, int rows, int cols);
bool traverseMatrix(int row, int col, int rows, int cols, std::vector<std::vector<char>> &matrix, std::vector<std::vector<bool>> &visited);

int main()
{
	int testCases = 0;
	std::cin >> testCases;
	std::vector<std::string> out;
	for (size_t i = 0; i < testCases; i++)
	{
		int rows = 0;
		int cols = 0;
		std::cin >> rows;
		std::cin >> cols;
		std::vector<std::vector<char>> matrix(rows);
		std::vector<std::vector<bool>> visited(rows);
		for (size_t row = 0; row < rows; row++)
		{
			matrix[row].resize(cols);
			visited[row].resize(cols);
			for (size_t col = 0; col < cols; col++)
			{
				char cell;
				std::cin >> cell;
				matrix[row][col] = cell;
				visited[row][col] = false;
			}
		}
		updateMatrix(matrix, visited, rows, cols);
		std::string res = saveOut(matrix);
		out.push_back(res);
		//print(matrix);
	}
	for (auto s : out)
	{
		std::cout << s << std::endl;
	}
	return 0;
}
void updateMatrix(std::vector<std::vector<char>>& matrix, std::vector<std::vector<bool>> &visited, int rows, int cols)
{
	for (size_t row = 0; row < rows; row++)
	{
		for (size_t col = 0; col < cols; col++)
		{
			if (matrix[row][col] == 'O')
			{
				//traverse and try to replace 'O' with 'X' if match condition
				bool success = traverseMatrix(row, col, rows, cols, matrix, visited);
			}
		}
	}
}
bool traverseMatrix(int row, int col, int rows, int cols, std::vector<std::vector<char>> &matrix, std::vector<std::vector<bool>> &visited)
{
	bool success = false;
	if (row == 3 &&
		col == 1)
	{
		std::cout << "";
	}
	if (row == 1 &&
		col == 6)
	{
		std::cout << "";
	}
	if (!isInRange(row, col, rows, cols))
	{
		return false;
	}
	if (matrix[row][col] == 'X')
	{
		return true;
	}
	if (visited[row][col])
	{
		return false;
	}
	visited[row][col] = true;
	
	
	/*if (matrix[row][col] == 'X')
	{
		visited[row][col] = false;
		return true;
	}*/
	bool successRight = traverseMatrix(row, col + 1, rows, cols, matrix, visited);//right
	bool successDown = traverseMatrix(row + 1, col, rows, cols, matrix, visited);//down
	bool successLeft = traverseMatrix(row, col - 1, rows, cols, matrix, visited);//left
	bool successUp = traverseMatrix(row - 1, col, rows, cols, matrix, visited);//up

	if (successRight &&
		successDown &&
		successLeft &&
		successUp)
	{
		success = true;
		matrix[row][col] = 'X';
	}
	//visited[row][col] = false;
	return success;
}
bool isInRange(int row, int col, int rows, int cols)
{
	return row >= 0 && row < rows && col >= 0 && col < cols;
}
std::string saveOut(std::vector<std::vector<char>> matrix)
{
	std::stringstream ss;
	for (size_t row = 0; row < matrix.size(); row++)
	{
		for (size_t col = 0; col < matrix[row].size(); col++)
		{
			ss << matrix[row][col] << " ";
		}
	}
	return ss.str();
}
void print(std::vector<std::vector<char>> matrix)
{
	for (size_t row = 0; row < matrix.size(); row++)
	{
		for (size_t col = 0; col < matrix[row].size(); col++)
		{
			std::cout << matrix[row][col] << " ";
		}
		//std::cout << std::endl;
	}
}
