import java.util.*;

class Solution {
    public int solution(int n) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> queen = new HashSet<>();
            queen.add(cantorPairing(0,i));
            ans += recursive(queen, 1, n);
            queen.clear();
        }
        return ans;
    }

    private int recursive(Set<Integer> queen, int cnt, int n) {
        if (cnt == n) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            boolean allAvailable = true;
            for (Integer q : queen) {
                int[] inverse = cantorPairingInverse(q);
                allAvailable &= available(inverse[0], inverse[1], cnt, i);
            }
            if (allAvailable) {
                int check = cantorPairing(cnt, i);
                queen.add(check);
                res += recursive(queen, cnt + 1, n);
                queen.remove(check);
            }
        }
        return res;
    }

    boolean available(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            return false;
        }
        if (y1 == y2) {
            return false;
        }
        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return false;
        }
        return true;
    }

    public int cantorPairing(int a, int b) {
        return (a + b) * (a + b + 1) / 2 + b;
    }

    public int[] cantorPairingInverse(int hash) {
        int t = (int) Math.floor((Math.sqrt(8 * hash + 1) - 1) / 2);
        int a = t * (t + 3) / 2 - hash;
        int b = hash - t * (t + 1) / 2;
        return new int[]{a, b};
    }

}