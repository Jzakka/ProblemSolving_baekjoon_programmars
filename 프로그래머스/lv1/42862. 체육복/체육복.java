import java.util.*;

class Solution {
    int totalStu;
    int[] students;
    int ans = 0;
    public int solution(int n, int[] lost, int[] reserve) {
        totalStu = n;
        students = new int[n+1];
        Arrays.fill(students, 1);
        students[0] = -1;
        for (int reserveStudent : reserve) {
            students[reserveStudent] = 2;
        }
        for (int lostStudent : lost) {
            students[lostStudent]--;
        }

        recursive(1);

        return ans;
    }

    void recursive(int idx) {
        if (idx > totalStu) {
            ans = Math.max(ans,countAvailableStu());
            return;
        }

        if (students[idx] == 2 && idx > 1 && students[idx - 1] == 0) {
            students[idx]--;
            students[idx - 1]++;
            recursive(idx+1);
            students[idx]++;
            students[idx - 1]--;
        }

        if (students[idx] == 2 && idx < totalStu && students[idx + 1] == 0) {
            students[idx]--;
            students[idx + 1]++;
            recursive(idx+1);
            students[idx]++;
            students[idx + 1]--;
        }

        recursive(idx+1);
    }

    private int countAvailableStu() {
        return Arrays
                .stream(students)
                .reduce(0, (cnt, cur) -> cnt += cur >= 1 ? 1 : 0);
    }
}