#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    if (s / n <= 0) {
        return {-1};
    }
    vector<int> answer;

    answer = vector<int>(n,s / n);
    for (int i = answer.size()-1; i >=answer.size()-s%n ; i--) {
        answer.at(i)++;
    }

    return answer;
}