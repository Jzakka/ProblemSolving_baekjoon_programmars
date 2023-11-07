#include <iostream>
#include <vector>
#include <set>

using namespace std;

int customBound(set<int>& remainGates, int gate){
  auto it = remainGates.lower_bound(gate);

  if(it == remainGates.end()){
    return 0;
  }

  return *it;
}

void solution(int g, vector<int> &gates) {
  int p = gates.size();

  set<int> remainGates;
  for (int i = 1; i <= g; ++i) {
    remainGates.insert(-i);
  }

  for (const auto &gate: gates){
    int bind = customBound(remainGates, -gate);
    if (bind == 0) {
      break;
    }

    remainGates.erase(bind);
  }

  cout << g - remainGates.size();
}

int main(){
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int g,p;
  cin >> g >> p;

  vector<int> gates(p);
  for (int i = 0; i < p; ++i) {
    cin >> gates.at(i);
  }

  solution(g, gates);
}