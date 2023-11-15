#include <iostream>
#include <cmath>
#include <vector>

using namespace std;

void solution(vector<vector<int>>& adjacentMatrix){
  int n = adjacentMatrix.size();

  /*

   */

  for (int j = 0; j < n; ++j) {
    for (int i = 0; i < n; ++i) {
      if(adjacentMatrix[i][j] == 1){
        for (int k = 0; k < n; ++k) {
          if(adjacentMatrix[j][k] == 1){
            adjacentMatrix[i][k] = 1;
          }
        }
      }
    }
  }

  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
      cout << adjacentMatrix[i][j] << " ";
    }
    cout << "\n";
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n;
  cin >> n;

  vector<vector<int>> adjacentMatrix(n, vector<int>(n));

  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
      cin >> adjacentMatrix[i][j];
    }
  }

  solution(adjacentMatrix);
}