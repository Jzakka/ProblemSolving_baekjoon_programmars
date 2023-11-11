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

vector<int> failure(string &sub) {
  int m = sub.length();
  vector<int> failTable(m);
  failTable[0] = 0;

  int i = 1;
  int j = 0;

  while (i < m) {
    if (sub.at(i) == sub.at(j)) {
      failTable[i++] = ++j;
    } else if (j == 0) {
      failTable[j] = 0;
      i++;
    } else {
      j = failTable[j - 1];
    }
  }

  return failTable;
}

void solution(string &origin, string &sub) {
  vector<int> F = failure(sub);
  int n = origin.length();
  int m = sub.length();

  int i = 0, j = 0;

  int cnt = 0;
  vector<int> matchIdx;
  while (i < n) {
    if (j < m && origin.at(i) == sub.at(j)) {
      i++;
      j++;
      if (j == m) {
        cnt++;
        matchIdx.push_back(i - m + 1);
        j = F[j - 1];
      }
    } else if (j == 0) {
      i++;
    } else {
      j = F[j - 1];
    }
  }

  cout << cnt << "\n";
  for (const auto &idx: matchIdx){
    cout << idx << " ";
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  string origin;
  string sub;

  getline(cin, origin);
  getline(cin, sub);

  solution(origin, sub);
}