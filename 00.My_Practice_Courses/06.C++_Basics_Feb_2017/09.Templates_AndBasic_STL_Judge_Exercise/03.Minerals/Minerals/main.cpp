#include<iostream>
#include<queue>
#include<cmath>

using namespace std;

int bfs(const int totalMinerals, int startX, int startY, int x, int y);
int gk[] = {1, 0, -1, 0};
int gr[] = {0, 1, 0, -1};
bool matrix[1000][1000] = {false};
bool visited[1000][1000] = {false};

int main() {
    int mineralsCount = 0;
    cin >> mineralsCount;
    int x, y = 0;
    cin >> x;
    cin >> y;

    int lines = 0;
    cin >> lines;
    for(int i = 0; i < lines; i++) {
        int p, q = 0;
        cin >> p;
        cin >> q;
        matrix[p][q] = true;
    }
    int steps = bfs(mineralsCount, x, y, x, y);
    cout << steps << endl;
    /*
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 10; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }*/
    return 0;
}
int bfs(const int totalMinerals, int startX, int startY, int x, int y) {
    int steps = 0;
    int foundMinerals = 0;
    queue<int> queue_x, queue_y;
    visited[x][y] = true;
    queue_x.push(x);
    queue_y.push(y);

    int topX, topY;
    while(!queue_x.empty()) {
        if (foundMinerals == totalMinerals) {
            return steps;
        }
        topX = queue_x.front();
        topY = queue_y.front();
        queue_x.pop();
        queue_y.pop();


        if(matrix[topX][topY]){
            matrix[topX][topY] = false;
            steps += (abs(startX - topX) + abs(startY - topY)) * 2;
            foundMinerals++;
        }

        for(int i = 0; i < 4; i++){
            x = topX + gk[i];
            y = topY + gr[i];
            if(!visited[x][y] &&
               x >=0 &&
               x < 1000 &&
               y >= 0 &&
               y < 1000){
                visited[x][y] = true;
                queue_x.push(x);
                queue_y.push(y);
            }
        }
    }
    return steps;
}

