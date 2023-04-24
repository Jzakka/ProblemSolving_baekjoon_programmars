import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int p1Cnt, p2Cnt, p3Cnt;
        p1Cnt = p2Cnt = p3Cnt = 0;

        for (int i = 0; i < answers.length; i++) {
            int num = answers[i];
            if (p1[i % p1.length] == num) {
                p1Cnt++;
            }
            if (p2[i % p2.length] == num) {
                p2Cnt++;
            }
            if (p3[i % p3.length] == num) {
                p3Cnt++;
            }
        }

        int highest = Math.max(p1Cnt, Math.max(p2Cnt, p3Cnt));
        ArrayList<Integer> res = new ArrayList<>();
        if (p1Cnt == highest) {
            res.add(1);
        }
        if (p2Cnt == highest) {
            res.add(2);
        }
        if (p3Cnt == highest) {
            res.add(3);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}