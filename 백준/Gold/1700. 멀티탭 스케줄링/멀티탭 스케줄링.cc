#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>
#include <deque>

using namespace std;

vector<deque<int>> nextAppear(101);

struct compare{
  bool operator () (const int& a,const int& b) const{
    return nextAppear[a].front() > nextAppear[b].front();
  }
};

void solution(int n, vector<int> &electronics) {
  int k = electronics.size();

  multiset<int, compare> pq;

  for (int i = 0; i < k; ++i) {
    int electronic = electronics[i];
    nextAppear[electronic].push_back(i);
  }

  for (int i = 1; i <= k; ++i) {
    nextAppear[i].push_back(INT32_MAX);
  }

  int ans = 0;
  for (const auto &electronic: electronics) {
    auto it = pq.find(electronic);
    if (it != pq.end()) {
      pq.erase(it);
    } else {
      if (pq.size() == n) {
        pq.erase(pq.begin());
        ans++;
      }
    }

    nextAppear[electronic].pop_front();
    pq.insert(electronic);
  }

  cout << ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n, k;
  cin >> n >> k;
  vector<int> electronics(k);

  for (int i = 0; i < k; ++i) {
    cin >> electronics[i];
  }

  solution(n, electronics);
}