#include <iostream>
#include <cmath>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>
#define  ll long long

using namespace std;

void solution(int k, vector<vector<pair<int, int>>>& graph){
//  vector<bool> visited(graph.size(), false);

  vector<ll> dist(graph.size(), INT32_MAX);
  auto comapre=  [&dist](int& a, int& b){
    return dist[a] > dist[b];
  };
  priority_queue<int, vector<int>, decltype(comapre)> pq(comapre);

  dist[k] = 0;
  pq.push(k);

  while (!pq.empty()) {
    int poped = pq.top();
    pq.pop();

//    if (visited[poped]) {
//      continue;
//    }
//
//    visited[poped] = true;

    for (const auto &next: graph[poped]){
      if( // !visited[next.first] &&
      dist[next.first] > dist[poped] + next.second){
        dist[next.first] = dist[poped] + next.second;
        pq.push(next.first);
      }
    }
  }

  for (int i = 1; i < dist.size(); ++i) {
    if(dist[i] == INT32_MAX){
      cout << "INF\n";
    } else {
      cout << dist[i] << "\n";
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int v,e;
  cin >> v >> e;

  int k;
  cin >> k;

  vector<vector<pair<int, int>>> graph(v + 1);

  for (int i = 0; i < e; ++i) {
    int src, dest, weight;
    cin >> src >> dest >> weight;

    graph[src].push_back({dest, weight});
  }

  solution(k, graph);
}