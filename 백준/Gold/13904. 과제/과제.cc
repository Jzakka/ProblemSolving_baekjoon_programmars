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

struct compare{
  bool operator () (const int & a,const int &b) const{
    return a > b;
  }
};

void solution(vector<pii>& subjects){
  map<int, vector<int>> subMap;

  for (const auto &subject: subjects){
    subMap[subject.first].push_back(subject.second);
  }

  int lastDay = prev(subMap.end())->first;

  multiset<int, compare> subjectSet;
  int ans = 0;
  for (int i = lastDay; i >= 0; --i) {
    if(!subjectSet.empty()){
      ans += *subjectSet.begin();
      subjectSet.erase(subjectSet.begin());
    }
    if (subMap.find(i) != subMap.end()) {
      subjectSet.insert(subMap[i].begin(), subMap[i].end());
    }
  }
  cout << ans << "\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n;
  cin >> n;

  vector<pii > subjects(n);

  for (int i = 0; i < n; ++i) {
    cin >> subjects.at(i).first >> subjects.at(i).second;
  }

  solution(subjects);
}