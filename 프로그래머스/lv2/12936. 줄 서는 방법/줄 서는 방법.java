import java.util.*;

class Solution {
    ArrayList<Long> factorialDP = new ArrayList<>();
    public int[] solution(int n, long k) {
        preFactorial(n);
        long remain = --k;
        ArrayList<Integer> remainNumbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            remainNumbers.add(i);
        }

        int[] result = new int[n];
        int i=0;

        while (remainNumbers.size() > 1) {
            long quot = getQuot(remain, n - 1);
            remain = getRemain(remain, n - 1);
            result[i++]= remainNumbers.remove((int)quot);
            n--;
        }
        result[i]=remainNumbers.get(0);

        return result;
    }

    private void preFactorial(long n) {
        factorialDP.add(1L);
        long fact=1;
        for (long i = 1; i <= n; i++) {
            fact *= i;
            factorialDP.add(fact);
        }
    }

    private long getQuot(long remain, int i) {
        return remain / factorialDP.get(i);
    }

    private long getRemain(long remain, int i) {
        return remain % factorialDP.get(i);
    }
}