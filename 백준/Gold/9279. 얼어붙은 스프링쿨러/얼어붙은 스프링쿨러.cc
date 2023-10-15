#include<iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

//string res = "";

int traversal(vector<vector<pair<int, int>>>& links, vector<bool>& visited, int curNode);

void solution(int n, vector<vector<int>> &edges, int c) {
    vector<vector<pair<int, int>>> links(n + 1);

    for (const auto &edge: edges) {
        int src = edge.at(0);
        int dest = edge.at(1);
        int cost = edge.at(2);

        pair<int, int> srcToDest = {dest, cost};
        pair<int, int> destToSrc = {src, cost};

        links.at(src).push_back(srcToDest);
        links.at(dest).push_back(destToSrc);
    }

    vector<bool> visited(n + 1, false);
    int cost = traversal(links, visited, c);

    cout << cost << "\n";
}

int traversal(vector<vector<pair<int, int>>>& links, vector<bool>& visited, int curNode){
    visited.at(curNode) = true;
    int subCost = 0;
    for (const auto &child: links.at(curNode)){
        if (!visited.at(child.first)) {
            subCost += min(traversal(links, visited, child.first), child.second);
        }
    }

    return subCost == 0 ? INT32_MAX : subCost;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, c;

    while (cin >> n >> c) {
        vector<vector<int>> edges(n - 1);

        for (int i = 0; i < n - 1; ++i) {
            int src, dest, cost;
            cin >> src >> dest >> cost;
            edges.at(i) = {src, dest, cost};
        }

        solution(n, edges, c);
    }
}