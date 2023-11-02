#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

void solution(vector<int> &files) {
  int n = files.size();

  vector<vector<int>> DP(n, vector<int>(n, INT32_MAX));
  vector<int> accSums(n+1, 0);

  for (int i = 0; i < n; ++i) {
    DP[i][i] = 0;
  }

  int accSum = 0;
  for (int i = 0; i < n; ++i) {
    accSum += files.at(i);
    accSums.at(i+1) = accSum;
  }


  for (int j = 1; j < n; ++j) {
    int i = 0;
    int k = j;
    while (k < n) {
      // [i,l][l+1, k]

      for (int l = i; l < k; ++l) {
        DP[i][k] = min(DP[i][k],
                       DP[i][l] + DP[l + 1][k]
                       + accSums[l + 1] - accSums[i]
                       + accSums[k + 1] - accSums[l + 1]);
      }

      i++;
      k++;
    }
  }

  cout << DP[0][n - 1] << "\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    int n;
    cin >> n;
    vector<int> files(n);

    for (int i = 0; i < n; ++i) {
      cin >> files.at(i);
    }

    solution(files);
  }
}