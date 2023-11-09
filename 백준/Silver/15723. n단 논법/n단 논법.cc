#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <set>
#include <unordered_set>
#include <unordered_map>

#define ll long long

using namespace std;

bool assert(vector<bool> &visited, vector<vector<int>> &when, int sub, int ob) {
  visited[sub] = true;
  for (int i = 0; i < 26; ++i) {
    if (when[sub][i] == 1) {
      if (i == ob || (!visited[i] && assert(visited, when, i, ob))) {
        return true;
      }
    }
  }
  return false;
}

void solution(vector<vector<int>>& when, vector<vector<int>>& then){
  for (int i = 0; i < 26; ++i) {
    for (int j = 0; j < 26; ++j) {
      if(then[i][j]){
        vector<bool> visited(26);
        bool assertion = assert(visited, when, i, j);
        cout << (assertion ? 'T' : 'F') << "\n";
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  vector<vector<int>> when(26, vector<int>(26));
  vector<vector<int>> then(26, vector<int>(26));

  int n, m;
  cin >> n;

  for (int i = 0; i < n; ++i) {
    char subject, objective;
    string is;
    cin >> subject >> is >> objective;

    when[subject - 'a'][objective - 'a'] = 1;
  }

  cin >> m;
  for (int i = 0; i < m; ++i) {
    char subject, objective;
    string is;
    cin >> subject >> is >> objective;

    then[subject - 'a'][objective - 'a'] = 1;
  }

  solution(when, then);
}