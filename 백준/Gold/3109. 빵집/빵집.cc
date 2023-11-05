#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>
#include <unordered_map>

using namespace std;

vector<int> dx = {-1, 0, 1};
vector<int> dy = {1, 1, 1};
int pathCnt = 0;

bool available(int n, int m, int x, int y) {
  return 0 <= x && x < n && 0 <= y && y < m;
}

bool greedy(vector<vector<int>> &dp, int x, int y) {
  dp[x][y] = 1;

  if (y == dp[0].size() - 1) {
    pathCnt++;
    return true;
  }

  for (int i = 0; i < 3; ++i) {
    int nx = x + dx[i];
    int ny = y + dy[i];

    if (available(dp.size(), dp[0].size(), nx, ny) && dp[nx][ny] == 0 && greedy(dp, nx, ny)) {
      return true;
    }
  }

  return false;
}

void solution(int r, int c, vector<vector<int>> &matrix) {
  for (int i = 0; i < r; ++i) {
    greedy(matrix, i, 0);
  }

  cout << pathCnt;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int r, c;
  cin >> r >> c;

  vector<vector<int>> matrix(r, vector<int>(c));
  for (int i = 0; i < r; ++i) {
    for (int j = 0; j < c; ++j) {
      char cell;
      cin >> cell;
      if (cell == 'x') {
        matrix[i][j] = 1;
      }
    }
  }

  solution(r, c, matrix);
}