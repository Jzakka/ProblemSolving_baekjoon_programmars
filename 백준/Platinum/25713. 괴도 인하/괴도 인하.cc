#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

void solution(int n, int m, vector<vector<int>> &cameras) {
  vector<vector<int>> DP(n, vector<int>(m));
  vector<vector<int>> r(n + 1, vector<int>(m + 1, 0));
  vector<vector<int>> c(n + 1, vector<int>(m + 1, 0));

  for (const auto &camera: cameras) {
    int x1 = camera[0] - 1;
    int y1 = camera[1] - 1;
    int x2 = camera[2] - 1;
    int y2 = camera[3] - 1;

    c[x1][y1]++;
    c[x2 + 1][y1]--;

    r[x1][y1]++;
    r[x1][y2 + 1]--;
  }

  // 가로방향 누적합
  for (int i = 0; i < n; ++i) {
    for (int j = 1; j < m; ++j) {
      r[i][j] += r[i][j - 1];
    }
  }
  // 세로방향 누적합
  for (int j = 0; j < m; ++j) {
    for (int i = 1; i < n; ++i) {
      c[i][j] += c[i - 1][j];
    }
  }

  DP[0][0] = min(r[0][0], c[0][0]);

  for (int i = 1; i < n; ++i) {
    DP[i][0] += DP[i - 1][0] + r[i][0];
  }

  for (int i = 1; i < m; ++i) {
    DP[0][i] += DP[0][i - 1] + c[0][i];
  }

  for (int i = 1; i < n; ++i) {
    for (int j = 1; j < m; ++j) {
      int up= DP[i - 1][j];
      int left= DP[i][j - 1];

      DP[i][j] = min(up + r[i][j], left + c[i][j]);
    }
  }

  cout << DP[n - 1][m - 1];
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n, m;
  cin >> n >> m;
  int k;
  cin >> k;
  vector<vector<int>> cameras;

  while (k--) {
    vector<int> camera(4);
    cin >> camera[0] >> camera[1] >> camera[2] >> camera[3];
    cameras.push_back(camera);
  }

  solution(n, m, cameras);
}

