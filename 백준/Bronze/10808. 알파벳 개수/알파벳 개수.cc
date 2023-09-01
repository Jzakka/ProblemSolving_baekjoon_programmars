#include<iostream>
#include<cmath>
#include <unordered_map>
#include <unordered_set>
#include <vector>
#include <algorithm>
#include <tuple>
#include <set>
#include <map>
#include <queue>
#include <stack>

using namespace std;

void solution(string word){
    int counts[26];
    for(int i=0;i<26;i++){
        counts[i] = 0;
    }

    for(int i=0;i<word.length();i++){
        char letter = word.at(i);
        if(letter != ' '){
            counts[letter-'a']++;
        }
    }

    for(int i=0;i<26;i++){
        cout << counts[i] << " ";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string word;
    cin >> word;

    solution(word);

    return 0;
}

