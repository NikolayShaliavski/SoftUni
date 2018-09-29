using SudokuSolver.Models;
using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Threading.Tasks;
using System.IO;

namespace SudokuSolver
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List<List<Cell>> matrix;

        public MainWindow()
        {
            InitializeComponent();
            this.InitMatrix();
        }
        private async void Solve_Click(object sender, RoutedEventArgs e)
        {
            this.MarkUserInput();
            bool solved = await Task.Run(async () => await this.SolveSudoku());

            if (solved)
            {
                this.Result.Text = "Sudoku solved.";
            }
            else
            {
                this.Result.Text = "Sudoku has no solution.";
            }
        }
        private async Task<bool> SolveSudoku()
        {
            int[] freeCell = this.FindFirstFreeCell();
            if (freeCell == null)
            {
                return true;
            }
            int freeRow = freeCell[0];
            int freeCol = freeCell[1];
            for (int num = 1; num <= 9; num++)
            {
                if (this.IsValid(num, freeRow, freeCol))
                {
                    this.Dispatcher.Invoke(() =>
                    {
                        this.matrix[freeRow][freeCol].Value = num;
                    });
                    if (await Task.Run(async () => await this.SolveSudoku()))
                    {
                        return true;
                    }
                    this.Dispatcher.Invoke(() =>
                    {
                        this.matrix[freeRow][freeCol].Value = null;
                    });
                }
            }
            return false;
        }
        private int[] FindFirstFreeCell()
        {
            for (int row = 0; row < 9; row++)
            {
                for (int col = 0; col < 9; col++)
                {
                    if (this.matrix[row][col].Value == null)
                    {
                        return new int[] { row, col };
                    }
                }
            }
            return null;
        }
        private bool IsValid(int val, int row, int col)
        {
            if (!this.IsValidInRow(val, row) ||
                !this.IsValiInColumn(val, col) ||
                !this.IsValidInCube(val, row, col))
            {
                return false;
            }
                   this.IsValidInCube(val, row, col);

            return true;
        }
        private bool IsValidInRow(int val, int row)
        {
            for (int col = 0; col < 9; col++)
            {
                if (this.matrix[row][col].Value == val)
                {
                    return false;
                }
            }
            return true;
        }
        private bool IsValiInColumn(int val, int col)
        {
            for (int row = 0; row < 9; row++)
            {
                if (matrix[row][col].Value == val)
                {
                    return false;
                }
            }
            return true;
        }
        private bool IsValidInCube(int val, int row, int col)
        {
            row = row - (row % 3);
            col = col - (col % 3);
            for (int r = row; r < row + 3; r++)
            {
                for (int c = col; c < col + 3; c++)
                {
                    if (this.matrix[r][c].Value == val)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        private void InitMatrix()
        {
            this.matrix = new List<List<Cell>>();
            for (int row = 0; row < 9; row++)
            {
                matrix.Add(new List<Cell>());
                for (int col = 0; col < 9; col++)
                {
                    matrix[row].Add(new Cell());
                }
            }
            this.SudokuTable.ItemsSource = matrix;
        }
        private void MarkUserInput()
        {
            foreach (var row in this.matrix)
            {
                foreach (var cell in row)
                {
                    if (cell.Value != null &&
                        cell.Value > 0)
                    {
                        cell.UserDefined = true;
                    }
                    else
                    {
                        cell.UserDefined = false;
                    }
                }
            }
        }

        private void Populate_Click(object sender, RoutedEventArgs e)
        {
            string path = @"C:\Users\Nikolay\Desktop\sudoku.txt";

            IEnumerable<string> lines = File.ReadLines(path);
            int counter = 0;
            foreach (var line in lines)
            {
                for (int i = 0; i < line.Length; i++)
                {
                    if (line[i] != '-')
                    {
                        int val = int.Parse(line[i] + "");
                        this.matrix[counter][i].Value = val;
                    }
                }
                counter++;
            }
        }
        private void Clear_Click(object sender, RoutedEventArgs e)
        {
            foreach (var row in this.matrix)
            {
                foreach (var cell in row)
                {
                    cell.Value = null;
                    cell.UserDefined = false;
                }
            }
            this.Result.Text = "";
        }
    }
}
