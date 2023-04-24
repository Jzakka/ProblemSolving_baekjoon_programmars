import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int boatCnt = 0;
        int boat = limit;
        int s = 0;
        int e = people.length-1;

        for (; e > s; e--, boatCnt++) {
            int bigger = people[e];
            int smaller = people[s];

            if (bigger + smaller <= limit) {
                s++;
            }
        }
        if (s == e) {
            boatCnt++;
        }

        return boatCnt;
    }
}