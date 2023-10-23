#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

void solution(int n, vector<int>& calendar){
  int m = calendar.size();

  vector<int> nextRainyDays(n + 1, -1);
  vector<int> preprocess(m);

  for (int i = m - 1; i >= 0; --i) {
    if (calendar[i] > 0) {
      preprocess[i] = nextRainyDays[calendar[i]];
      nextRainyDays[calendar[i]] = i;
    }
  }

  auto cmp = [&](int a, int b) { return nextRainyDays[a] > nextRainyDays[b]; };
  priority_queue<int, vector<int>, decltype(cmp)> toDrink(cmp);

  for (int i = 1; i <= n; ++i) {
    if (nextRainyDays[i] != -1) {
      toDrink.push(i);
    }
  }


  vector<bool> lakeStates(n + 1, true);
  vector<int> drinkedLake;

  for (int day = 0; day < m; ++day) {
    if (calendar[day] == 0) {
      if (!toDrink.empty()) {
        int drinkLake = toDrink.top();
        toDrink.pop();

        if (lakeStates[drinkLake]) {
          drinkedLake.push_back(drinkLake);
          lakeStates[drinkLake] = false;
        } else {
          drinkedLake.push_back(0);
        }
      } else {
        drinkedLake.push_back(0);
      }
    } else {
      int rainyLake = calendar[day];

      if (lakeStates[rainyLake]) {
        cout << "NO" << endl;
        return;
      } else {
        lakeStates[rainyLake] = true;
        if (preprocess[day] > 0) {
          nextRainyDays[rainyLake] = preprocess[day];
          toDrink.push(rainyLake);
        }
      }
    }
  }
  cout << "YES" << endl;
  for (int &lake: drinkedLake)
    cout << lake << " ";
  cout << endl;
}

int main() {
  int t;
  cin >> t;

  while (t--) {
    int n, m;
    cin >> n >> m;

    vector<int> calendar(m);
    for (int i = 0; i < m; i++) {
      cin >> calendar[i];
    }

    solution(n, calendar);
  }
}