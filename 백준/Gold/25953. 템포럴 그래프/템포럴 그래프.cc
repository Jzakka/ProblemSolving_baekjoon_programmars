#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <unordered_map>

using namespace std;

void solution(int n, int t, int m, vector<vector<vector<int>>> &edges, int s, int e) {
  vector<unordered_map<int,vector<pair<int,int>>>> temporalGraph(t);

  for (int i = 0; i < t; ++i) {
    for (const auto &edge: edges.at(i)){
      int src = edge.at(0);
      int dest = edge.at(1);
      int cost = edge.at(2);
      
      temporalGraph.at(i)[src].push_back({dest, cost});
      temporalGraph.at(i)[dest].push_back({src, cost});
    }
  }
  
  vector<int> DP(n, INT32_MAX/2);
  DP[s] = 0;

  for (int i = 0; i < t; ++i) {
    for (const auto &edgeset: temporalGraph[i]){
      int src = edgeset.first;
      for (const auto &edge: edgeset.second){
        int dest = edge.first;
        int cost = edge.second;

        DP[dest] = min(DP[src] + cost, DP[dest]);
      }
    }
  }


  cout << ((DP[e] == INT32_MAX/2) ? -1 : DP[e]);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n,t,m;
  cin >> n >> t >> m;
  int s, e;
  cin >> s >> e;

  vector<vector<vector<int>>> edges(t);
  for (int i = 0; i < t; ++i) {
    for (int j = 0; j < m; ++j) {
      vector<int> edge = {0,0,0};
      cin >> edge.at(0) >> edge.at(1) >> edge.at(2);

      edges.at(i).push_back(edge);
    }
  }

  solution(n, t, m, edges, s, e);
}