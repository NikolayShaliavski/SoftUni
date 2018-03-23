#include <iostream>
#include <vector>
using namespace std;

void fillMatrix(vector<vector<char> > &matrix, char fillChar, char stepChar, int row, int col);
void printMatrix(vector<vector<char> > matrix);

int main() {
    int rows = 0;
    int cols = 0;
    cin >> rows;
    cin >> cols;

    vector<vector<char> > matrix(rows, vector<char>(cols));
    for(int row = 0; row < rows; row++) {
        for(int col = 0; col < cols; col++) {
            cin >> matrix[row][col];
        }
    }

    char fillChar = 'x';
    cin >> fillChar;
    int startRow = 0;
    int startCol = 0;
    cin >> startRow;
    cin >> startCol;

    char stepChar = matrix[startRow][startCol];

    fillMatrix(matrix, fillChar, stepChar, startRow, startCol);//up

    printMatrix(matrix);
    return 0;
}

void fillMatrix(vector<vector<char> > &matrix, char fillChar, char stepChar, int row, int col) {
    if (row < 0 ||
        row >= matrix.size() ||
        col < 0 ||
        col >= matrix[0].size() ||
        matrix[row][col] != stepChar) {
        return;
    }
    matrix[row][col] = fillChar;

    fillMatrix(matrix, fillChar, stepChar, row + 1, col);//up
    fillMatrix(matrix, fillChar, stepChar, row, col + 1);//right
    fillMatrix(matrix, fillChar, stepChar, row - 1, col);//down
    fillMatrix(matrix, fillChar, stepChar, row, col - 1);//left
}

void printMatrix(vector<vector<char> > matrix) {
    for(int row = 0; row < matrix.size(); row++) {
        for(int col = 0; col < matrix[row].size(); col++) {
            cout << matrix[row][col];
        }
        cout << endl;
    }
}
