#include <iostream>
#include <vector>
#include <set>
#define fastio ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef __int128_t int128;

vector<int128> X;
int128 A, B, C, D;

inline int128 calcCost(int i, int j) {
    if (i > j) swap(i, j);
    return (((static_cast<int128>(X[i]) * A % C) + (static_cast<int128>(X[j]) * B % C)) % C) ^ D;
}

int main() {
    fastio;

    int n;
    cin >> n;

    long long a, b, c, d;
    cin >> a >> b >> c >> d;

    A = a; B = b; C = c; D = d;
    A %= C;
    B %= C;

    X.resize(n);
    for (auto &x : X) {
        long long tmp;
        cin >> tmp;
        x = tmp % C;
    }

    long long ans = 0;
    vector<long long> distance(n, INT64_MAX);
    vector<bool> visited(n, 0);
    distance[0] = 0;

    for (int i = 0; i < n; ++i) {
        long long minDist = INT64_MAX;
        int minNode = -1;
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && minDist > distance[j]) {
                minDist = distance[j];
                minNode = j;
            }
        }

        visited[minNode] = 1;
        ans += minDist;

        for (int j = 0; j < n; ++j) {
            long long dist;
            if(!visited[j] && (dist = calcCost(minNode, j)) < distance[j]){
                distance[j] = dist;
            }
        }

    }


    cout << ans;

    return 0;
}

/*
 * 입력제한도 정도껏 해야지
 * 10^12는 시발련아
 * java로는 풀지도 못하고
 * c++로 몸 ㅈㄴ꼬아가며 풀어야하네
 * 애미뒤진 문제
 */
