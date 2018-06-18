// Sudoku.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

#define DIM 9
#define BLANK 0
#define GRID_FULL std::make_pair(9, 9)

void print(int grid[DIM][DIM]);
bool isUsedInRow(int grid[DIM][DIM], int row, int value);
bool isUsedInColumn(int grid[DIM][DIM], int col, int value);
bool isUsedInBox(int grid[DIM][DIM], int boxStartRow, int boxStartCol, int value);
bool isSafe(int grid[DIM][DIM], int row, int col, int value);
std::pair<int, int> getUnassignedLocation(int grid[DIM][DIM]);
bool solveSudoku(int grid[DIM][DIM]);

int main()
{
	int testCases = 0;
	std::cin >> testCases;
	for (size_t i = 0; i < testCases; i++)
	{
		int grid[DIM][DIM];
		for (size_t row = 0; row < 9; row++)
		{
			for (size_t col = 0; col < 9; col++)
			{
				std::cin >> grid[row][col];
			}
		}
		/*std::cout << "-------------------------------------" << std::endl;
		print(grid);
		std::cout << "-------------------------------------" << std::endl;*/
		bool hasSolution = solveSudoku(grid);
		//print(grid);
		if (hasSolution)
		{
			std::cout << 1 << std::endl;
		}
		else
		{
			std::cout << 0 << std::endl;
		}
	}
    return 0;
}
void print(int grid[DIM][DIM])
{
	for (size_t row = 0; row < 9; row++)
	{
		for (size_t col = 0; col < 9; col++)
		{
			std::cout << grid[row][col] << " ";
		}
		std::cout << std::endl;
	}
}
bool isUsedInRow(int grid[DIM][DIM], int row, int value)
{
	for (size_t col = 0; col < DIM; col++)
	{
		if (grid[row][col] == value)
		{
			return true;
		}
	}
	return false;
}
bool isUsedInColumn(int grid[DIM][DIM], int col, int value)
{
	for (size_t row = 0; row < DIM; row++)
	{
		if (grid[row][col] == value)
		{
			return true;
		}
	}
	return false;
}
bool isUsedInBox(int grid[DIM][DIM], int boxStartRow, int boxStartCol, int value)
{
	for (size_t row = boxStartRow; row < boxStartRow + 3; row++)
	{
		for (size_t col = boxStartCol; col < boxStartCol + 3; col++)
		{
			if (grid[row][col] == value)
			{
				return true;
			}
		}
	}
	return false;
}
bool isSafe(int grid[DIM][DIM], int row, int col, int value)
{
	return !isUsedInRow(grid, row, value) &&
		   !isUsedInColumn(grid, col, value) &&
		   !isUsedInBox(grid, row - row % 3, col - col % 3, value);
}
std::pair<int, int> getUnassignedLocation(int grid[DIM][DIM])
{
	for (size_t row = 0; row < DIM; row++)
	{
		for (size_t col = 0; col < DIM; col++)
		{
			if (grid[row][col] == BLANK)
			{
				return std::make_pair(row, col);
			}
		}
	}
	return GRID_FULL;
}
bool solveSudoku(int grid[DIM][DIM])
{
	if (getUnassignedLocation(grid) == GRID_FULL)
	{
		return true;
	}
	std::pair<int, int> rowAndCol = getUnassignedLocation(grid);
	int row = rowAndCol.first;
	int col = rowAndCol.second;
	for (size_t num = 1; num <= DIM; num++)
	{
		if (isSafe(grid, row, col, num))
		{
			grid[row][col] = num;

			if (solveSudoku(grid))
			{
				return true;
			}

			grid[row][col] = BLANK;
		}
	}
	return false;
}
