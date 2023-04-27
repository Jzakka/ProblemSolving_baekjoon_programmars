#include <string>
#include <vector>
#include<set>

using namespace std;

vector<int> solution(vector<string> operations) {
    multiset<int> mulSet;

    for (const auto &item: operations){
        char op = item.at(0);
        char addOrSub = item.at(2);
        if (op == 'I') {
            int num = stoi(item.substr(2, item.size() - 2));
            mulSet.insert(num);
        } else {
            if (mulSet.empty()) {
                continue;
            }
            else if (addOrSub == '-') {
                mulSet.erase(mulSet.begin());
            } else {
                mulSet.erase(prev(mulSet.end()));
            }
        }
    }

    if (mulSet.empty()) {
        return {0, 0};
    }
    return {*mulSet.rbegin(), *mulSet.begin()};
}