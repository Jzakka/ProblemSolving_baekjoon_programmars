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

pair<ll,ll> dfs(vector<vector<int>>& edges, vector<pair<ll, ll>>& costs,
                  vector<bool>& visited, int cur){
  visited.at(cur) = true;
  pair<ll, ll> ans = costs.at(cur);

  for (const auto &child: edges.at(cur)){
    if (!visited.at(child)) {
      auto subCosts = dfs(edges, costs, visited, child);
      ans.first += subCosts.second;
      ans.second += subCosts.first;
    }
  }

  return ans;
}

void solution(vector<vector<int>>& edges, vector<pair<ll, ll>>& costs){
  vector<bool> visited(edges.size());
  pair<ll, ll> colored = dfs(edges, costs, visited, 0);

  cout << min(colored.first, colored.second);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n;
  cin >> n;
  vector<vector<int>> edges(n);
  vector<pair<ll, ll>> costs(n);

  for (int i = 0; i < n - 1; ++i) {
    int src, dest;
    cin >> src >> dest;
    edges.at(src).push_back(dest);
    edges.at(dest).push_back(src);
  }
  for (int i = 0; i < n; ++i) {
    cin >> costs.at(i).first >> costs.at(i).second;
  }

  solution(edges, costs);
}