#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>

using namespace std;

void solution(string &seq, int k) {
  vector<int> lis;

  for (int i = 0; i < seq.length(); i++) {
    int digit = seq.at(i) - '0';

    if (lis.empty() || lis.back() >= digit || k == 0) {
      lis.push_back(digit);
    } else {
      while (!lis.empty() && lis.back() < digit && k > 0){
        lis.pop_back();
        k--;
      }
      lis.push_back(digit);
    }
  }
  // 542221210

  for (int i=0;i<lis.size() - k;i++) {
    int digit = lis[i];
    cout << digit;
  }
}


int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n, k;
  cin >> n >> k;

  string seq;
  cin >> seq;

  solution(seq, k);
}