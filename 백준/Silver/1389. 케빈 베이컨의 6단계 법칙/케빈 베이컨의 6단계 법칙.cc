#include <iostream>
#include <cmath>
#include <vector>
#include <queue>

using namespace std;

int getBacon(int person, vector<vector<int>>& graph){
  int res = 0;
  int dist = 0;

  queue<int> q;
  vector<bool> visited(graph.size());
  q.push(person);

  while (!q.empty()) {
    int qLen = q.size();

    for (int i = 0; i < qLen; ++i) {
      int poped = q.front();
      q.pop();

      res += dist;
      visited[poped] = true;

      for (const auto &incident: graph[poped]){
        if (!visited[incident]) {
          q.push(incident);
        }
      }
    }

    dist++;
  }

  return res;
}

void solution(vector<vector<int>>& graph){
  int n = graph.size() - 1;

  int ansPerson = 1;
  int minBacon = INT32_MAX;
  for (int i = 1; i <= n; ++i) {
    int bacon = getBacon(i, graph);
    if (bacon < minBacon) {
      minBacon = bacon;
      ansPerson = i;
    }
  }

  cout << ansPerson;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n,m;
  cin >> n >> m;

  vector<vector<int>> graph(n + 1);
  for (int i = 0; i < m; ++i) {
    int src,dest;
    cin >> src >> dest;

    graph[src].push_back(dest);
    graph[dest].push_back(src);
  }

  solution(graph);
}