#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <set>
#include <unordered_set>
#include <unordered_map>
#include <stack>
#include <map>

#define ll long long
#define pdd pair<double,double>
#define pii pair<int,int>

using namespace std;

struct compare {
  bool operator()(const int& a, const int& b) const {
    return a > b;
  }
};

void iterate(int m, multiset<int, compare>& mulset, multiset<int, compare>::iterator& it){
  for (int i = 0; i < m && it != mulset.end(); ++i, it++);
}

void solution(int m, multiset<int, compare>& minuses, multiset<int, compare>& pluses){
  auto minusIt = minuses.begin();
  auto plusIt = pluses.begin();

  int postAdd = 0;
  if (minusIt != minuses.end() && plusIt != pluses.end()) {
    if(*minusIt > *plusIt){
      postAdd = *minusIt;
      iterate(m, minuses, minusIt);
    } else {
      postAdd = *plusIt;
      iterate(m, pluses, plusIt);
    }
  } else if (minusIt == minuses.end()) {
    postAdd = *plusIt;
    iterate(m, pluses, plusIt);
  } else {
    postAdd = *minusIt;
    iterate(m, minuses, minusIt);
  }

  int ans = 0;
  while (minusIt != minuses.end()) {
    int subSum = *minusIt;
    iterate(m, minuses, minusIt);
    ans += subSum * 2;
  }

  while (plusIt != pluses.end()) {
    int subSum = *plusIt;
    iterate(m, pluses, plusIt);
    ans += subSum * 2;
  }

  ans += postAdd;

  cout << ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n,m;
  cin >> n >> m;

  multiset<int, compare> minuses;
  multiset<int, compare> pluses;

  for (int i = 0; i < n; ++i) {
    int num;
    cin >> num;
    if (num > 0) {
      pluses.insert(num);
    } else {
      minuses.insert(-num);
    }
  }

  solution(m, minuses, pluses);
}