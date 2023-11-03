#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>

using namespace std;

void solution(vector<int>& irons){
  int cursor = 1;

  sort(irons.begin(), irons.end());

  for (const auto &iron: irons) {
    int diff = cursor - iron;

    if (diff < 0) {
      cout << cursor;
      return;
    }

    cursor = cursor + iron;
  }

  cout << cursor;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n;
  cin >> n;

  vector<int> irons(n);
  for (int i = 0; i < n; ++i) {
    cin >> irons[i];
  }

  solution(irons);
}