
import java.util.*;

class Solution {
    public int solution(String numbers) {

        //9999999
        boolean[] table = new boolean[10000000];
        Arrays.fill(table, true);
        table[0] = table[1] = false;

        for (int i = 2; i < Math.ceil(Math.sqrt(9999999)); i++) {
            if (table[i]) {
                for (int j = i * i; j <= 9999999; j += i) {
                    table[j] = false;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i <= 9999999; i++) {
            if (table[i] && consistOf(i, numbers)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean consistOf(int i, String numbers) {
        int[] nums = new int[10];
        for (int s = 0; s < numbers.length(); s++) {
            nums[numbers.charAt(s) - '0']++;
        }

        String numStr = String.valueOf(i);
        for (int s = 0; s < numStr.length(); s++) {
            if (--nums[numStr.charAt(s) - '0'] < 0) {
                return false;
            }
        }
        return true;
    }
}