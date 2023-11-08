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

void dfs(vector<set<int>> &graph, int cur, vector<int> &visited, int& order) {
  order++;
  visited.at(cur) = order;

  for (const auto &incident: graph.at(cur)) {
    if (visited.at(incident) == 0) {
      dfs(graph, incident, visited, order);
    }
  }
}

void solution(int n, vector<pair<int, int>> &edges, int r) {
  vector<set<int>> graph(n + 1);
  for (const auto &edge: edges) {
    int src = edge.first;
    int dest = edge.second;

    graph.at(src).insert(dest);
    graph.at(dest).insert(src);
  }

  vector<int> visited(n + 1);
  int order = 0;
  dfs(graph, r, visited, order);

  for (int i = 1; i <= n; ++i) {
    cout << visited.at(i) << "\n";
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n, m, r;
  cin >> n >> m >> r;
  vector<pair<int, int>> edges(m);

  for (int i = 0; i < m; ++i) {
    cin >> edges.at(i).first >> edges.at(i).second;
  }

  solution(n, edges, r);
}