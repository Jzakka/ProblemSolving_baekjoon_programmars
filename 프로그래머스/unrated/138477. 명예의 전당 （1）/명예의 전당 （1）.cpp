#include <string>
#include <vector>
#include <set>

using namespace std;

vector<int> solution(int k, vector<int> score) {
    multiset<int> window;
    vector<int> announce(score.size());
    int i = 0;

    for (; i < score.size() && i < k; i++) {
        window.insert(score[i]);
        announce[i] = *window.begin();
    }

    for (; i < score.size(); i++) {
        int lowestScore = *window.begin();
        int todayScore = score[i];

        if (todayScore > lowestScore) {
            window.insert(todayScore);
        }

        if (window.size() > k) {
            window.erase(window.begin());
        }

        announce[i] = *window.begin();
    }

    return announce;
}