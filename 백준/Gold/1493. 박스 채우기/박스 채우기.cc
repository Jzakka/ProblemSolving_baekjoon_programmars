#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <deque>
#include <algorithm>
#include <cmath>
#include <map>
#include <stack>
using namespace std;

void mapSub(vector<int>& cubeCnt, int minCube){
  cubeCnt[(int) log2(minCube)]--;
}

int floor(vector<int>& cubeCnt, int minSize){
  int f = (int) log2(minSize);
  for (; f >= 0; --f) {
    if (f <= 20 && cubeCnt[f] > 0) {
      return 1 << f;
    }
  }
  return -1;
}

void solution(int length, int width, int height, vector<pair<int,int>>& cubes){
  vector<int> cubeCnt(21);

  for (const auto &cube: cubes){
    if (cube.second > 0) {
      cubeCnt[cube.first] = cube.second;
    }
  }

  stack<tuple<int,int,int>> stk;
  stk.push({length, width, height});
  int ans = 0;

  while (!stk.empty()) {
    tuple<int,int,int> cubeInfo = stk.top();
    stk.pop();
    int minSize = min(get<0>(cubeInfo), min(get<1>(cubeInfo), get<2>(cubeInfo)));

    int minCube = floor(cubeCnt, minSize);
    if (minCube == -1) {
      ans = -1;
      break;
    }

    mapSub(cubeCnt, minCube);
    ans++;

    if(get<1>(cubeInfo) - minCube > 0){
      stk.push({minCube, get<1>(cubeInfo) - minCube, minCube});
    }
    if(get<0>(cubeInfo) - minCube > 0){
      stk.push({get<0>(cubeInfo) - minCube, get<1>(cubeInfo), minCube});
    }
    if(get<2>(cubeInfo) - minCube > 0){
      stk.push({get<0>(cubeInfo), get<1>(cubeInfo),get<2>(cubeInfo) -minCube});
    }
  }
  cout << ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int length, width, height;
  cin >> length >> width >> height;

  int n;
  cin >> n;
  vector<pair<int, int>> cubes(n);

  for (int i = 0; i < n; ++i){
    cin >> cubes[i].first >> cubes[i].second;
  }

  solution(length, width, height, cubes);

}