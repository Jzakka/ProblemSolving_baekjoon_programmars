#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

bool ok(vector<int> &positions, string constraint) {
  int f1 = constraint.at(0) - 'A';
  int f2 = constraint.at(2) - 'A';

  char compare = constraint.at(3);

  int distance = constraint.at(4) - '0';

  if (compare == '<') {
    return abs(positions[f1] - positions[f2])-1 < distance;
  } else if (compare == '>') {
    return abs(positions[f1] - positions[f2])-1 > distance;
  }
  return abs(positions[f1] - positions[f2])-1 == distance;
}

bool ok(vector<char> &friends, vector<string> &data) {
  vector<int> positions(26);
  for (int i = 0; i < friends.size(); ++i) {
    char kakaoFriend = friends.at(i);

    positions[kakaoFriend - 'A'] = i;
  }
  for (const auto &constraint: data) {
    if (!ok(positions, constraint)) {
      return false;
    }
  }
  return true;
}

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int n, vector<string> data) {
  vector<char> friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

  int ans = 0;
  do {
    if (friends[0] == 'N'
        && friends[1] == 'F'
        && friends[2] == 'R'
        && friends[3] == 'A'
        && friends[4] == 'C'
        && friends[5] == 'T'
        && friends[6] == 'J'
        && friends[7] == 'M'){
      int a  = 1;
    }
      if (ok(friends, data)) {
        ans++;
      }
  } while (next_permutation(friends.begin(), friends.end()));

  return ans;
}

