import java.util.Arrays;

class Solution {
    public int solution(int n) {
        boolean[] table = new boolean[n + 1];
        Arrays.fill(table, true);
        table[0] = table[1] = false;

        for (int i = 2; i <= Math.ceil(Math.sqrt(n)); i++) {
            if (table[i]) {
                for (int j = i * i; j <= n; j+=i) {
                    table[j] = false;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (table[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}