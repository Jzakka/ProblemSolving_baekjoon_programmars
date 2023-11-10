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

void solution(vector<int> &cranes, multiset<int> &boxes){
  std::sort(cranes.begin(), cranes.end(), [](int a,int b){return a > b;});

  int time = 0;
  while (!boxes.empty()) {
    for (const auto &crane: cranes){
      auto lbIt = boxes.lower_bound(-crane);
      if (crane == cranes.front() && lbIt == boxes.end()) {
        cout << -1;
        return;
      }
      if (lbIt != boxes.end()) {
        boxes.erase(lbIt);
      }
    }
    time++;
  }

  cout << time;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n;
  cin >> n;

  vector<int> crane(n);
  for (int i = 0; i < n; ++i) {
    cin >> crane.at(i);
  }

  int m;
  cin >> m;

  multiset<int> boxes;
  for (int i = 0; i < m; ++i) {
    int box;
    cin >> box;
    boxes.insert(-box);
  }

  solution(crane, boxes);
}