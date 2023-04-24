import java.util.Arrays;

class Solution {
    private boolean[] primeNumbers;

    public int solution(int[] nums) {
        primeNumbers = erathos(100000);

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (isPrimeNumber(nums[i] + nums[j] + nums[k])) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    boolean[] erathos(int n) {
        boolean[] table = new boolean[n + 1];
        Arrays.fill(table, true);
        table[0] = table[1] = false;

        for (int i = 2; i <= Math.ceil(Math.sqrt(n)); i++) {
            if (table[i]) {
                for (int j = i * i; j < n + 1; j += i) {
                    table[j] = false;
                }
            }
        }
        return table;
    }

    boolean isPrimeNumber(int sum) {
        return primeNumbers[sum];
    }
}