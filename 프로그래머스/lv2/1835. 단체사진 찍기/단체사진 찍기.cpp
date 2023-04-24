#include <string>
#include <vector>
#include<cmath>
#include <unordered_map>
#include <algorithm>

using namespace std;

bool satisfy(unordered_map<char, int>& positions, string& constrain){
    char src = constrain.at(0);
    char dest = constrain.at(2);
    char op = constrain.at(3);
    char operand = constrain.at(4);

    int distance = abs(positions[src] - positions[dest]) - 1;
    if (op == '=') {
        return distance == operand - '0';
    } else if (op == '<') {
        return distance < operand - '0';
    }
    return distance > operand - '0';

}

bool satisfy(vector<char> &friends, vector<string> &data){
    unordered_map<char, int> positions;
    for (int i = 0; i < friends.size(); i++) {
        positions[friends.at(i)] = i;
    }

    for (auto &constrain: data) {
        if (!satisfy(positions, constrain)) {
            return false;
        }
    }
    return true;
}

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int n, vector<string> data) {
    vector<char> friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    int cnt = 0;
    do {
        if (satisfy(friends, data)) {
            cnt++;
        }
    } while (next_permutation(friends.begin(), friends.end()));

    return cnt;
}