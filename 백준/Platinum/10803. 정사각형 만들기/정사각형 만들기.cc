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

int DP[10'001][101];

int calc(int n, int m) {
  if (n < m) {
    swap(n, m);
  }
  if (DP[n][m] > 0) {
    return DP[n][m];
  }
  if (n % m == 0) {
    DP[n][m] = n / m;
    return DP[n][m];
  }

  int ans = n * m;
  if (n >= 3 * m) { // 이거 왜 *3인지 이해한 새끼 아무도 없음. 
    // 구글링 해서 뒤져봐도 안나옴
    // 이 문제 푼 애들 -> 걍 다 베낌ㅇㅇ
    DP[n][m] = calc(n - m, m) + 1;
    return DP[n][m];
  }
  for (int i = n-1; i >= (n + 1)/2; i--) {
    int up = calc(i, m);
    int down = calc(n - i, m);
    ans = min(ans, up+down);
  }
  for (int i = m-1; i >= (m + 1)/2; i--) {
    int left = calc(n, i);
    int right = calc(n, m - i);
    ans = min(ans, left + right);
  }
  DP[n][m] = ans;
  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  int n,m;
  cin >> n >> m;

  cout << calc(n,m);

}