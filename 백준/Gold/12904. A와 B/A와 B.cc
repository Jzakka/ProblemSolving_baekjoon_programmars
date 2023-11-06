#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>
#include <unordered_map>

using namespace std;

bool convertable(string s, string t, int front, int dir) {
  for (int i = 0; i < s.length(); ++i, front+=dir) {
    if (s.at(i) != t.at(front)) {
      return false;
    }
  }

  return true;
}

bool trace(string s,string t, int front, int back){
  while (abs(back - front) + 1 > s.length()) {
    if (t.at(back) == 'A') {
      if (front < back){
        back--;
      } else {
        back++;
      }
    } else {
      int tmp = front;

      if (front < back) {
        front = back - 1;
      } else {
        front = back + 1;
      }

      back = tmp;
    }
  }

  return convertable(s, t, front, (front < back) ? 1 : -1);
}

void solution(string s, string t){
  bool result = trace(s, t, 0, t.length() - 1);

  if (result) {
    cout << 1;
  } else {
    cout << 0;
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  string s;
  string t;

  cin >> s >> t;


  solution(s, t);
}