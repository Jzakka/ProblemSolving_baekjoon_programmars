#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <set>
#include <unordered_set>
#include <unordered_map>

#define ll long long

using namespace std;

ll multipleMin(vector<ll> nums) {
  ll minVal = INT32_MAX;
  for (const auto &num: nums) {
    minVal = min(minVal, num);
  }

  return minVal;
}

void solution(ll n, vector<ll> &dice) {
  const ll &A = dice.at(0);
  const ll &B = dice.at(1);
  const ll &C = dice.at(2);
  const ll &D = dice.at(3);
  const ll &E = dice.at(4);
  const ll &F = dice.at(5);

  if(n == 1){
    ll ans = A + B + C + D + E + F - *max_element(dice.begin(), dice.end());
    cout << ans;
    return;
  }

  ll red = multipleMin({A+B+C,
                         A+B+D,
                         A+E+D,
                         A+E+C,
                         F+B+C,
                         F+B+D,
                         F+E+D,
                         F+E+C});

  ll blue = multipleMin({A+B,
                          A+C,
                          A+D,
                          A+E,
                          B+C,
                          B+D,
                          B+F,
                          C+E,
                          C+F,
                          D+E,
                          D+F,
                          E+F});

  ll orange = multipleMin({A, B, C, D, E, F});

  ll redCnt = 4;
  ll blueCnt = (n - 2) * 4 + (n - 1) * 4;
  ll orangeCnt = (n - 2) * (n - 2) + (n - 2) * (n - 1) * 4;

  ll ans = red * redCnt + blue * blueCnt + orange * orangeCnt;

  cout << ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  ll n;
  cin >> n;
  vector<ll> dice(6);
  for (int i = 0; i < 6; ++i) {
    cin >> dice.at(i);
  }

  solution(n, dice);
}