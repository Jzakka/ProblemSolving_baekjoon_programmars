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

void solution(int n, vector<pair<int, int>> &edges, int r) {
  vector<set<int>> graph(n + 1);
  for (const auto &edge: edges) {
    int src = edge.first;
    int dest = edge.second;

    graph.at(src).insert(dest);
    graph.at(dest).insert(src);
  }

  vector<bool> visited(n + 1);
  vector<int> trace(n + 1);
  int order = 0;
  queue<int> q;
  q.push(r);
  visited.at(r) = true;

  while (!q.empty()) {
    int qLen = q.size();

    for (int i = 0; i < qLen; ++i) {
      int poped = q.front();
      q.pop();
      trace.at(poped) = ++order;

      for (const auto &incident: graph.at(poped)){
        if (!visited.at(incident)) {
          visited.at(incident) = true;
          q.push(incident);
        }
      }
    }
  }

  for (int i = 1; i <= n; ++i) {
    cout << trace.at(i) << "\n";
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