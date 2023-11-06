#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>
#include <unordered_map>

using namespace std;

void solution(vector<int> &sensors, int k){
  std::sort(sensors.begin(), sensors.end());

  vector<int> diffs;
  for (int i = 1; i < sensors.size(); ++i) {
    int diff = sensors.at(i) - sensors.at(i - 1);
    diffs.push_back(diff);
  }

  sort(diffs.begin(), diffs.end(), [](int a,int b){return b < a;});

  int ans = 0;
  for (int i = k - 1; i < diffs.size(); ++i) {
    ans += diffs.at(i);
  }

  cout << ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n, k;
  cin >> n >> k;

  vector<int> sensors(n);
  for (int i = 0; i < n; ++i) {
    cin >> sensors.at(i);
  }

  solution(sensors, k);
}