class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcd1 = gcd(arrayA);
        int gcd2 = gcd(arrayB);

        int res = 0;
        if(allSeoroso(arrayA, gcd2) &&allSeoroso(arrayB,gcd1)){
            res = Math.max(gcd1, gcd2);
        }
        else if (allSeoroso(arrayB, gcd1)) {
            res = gcd1;
        } else if (allSeoroso(arrayA, gcd2)) {
            res = gcd2;
        }
        return res;
    }

    private int gcd(int[] array) {
        int gcd = array[0];
        for (int i = 1; i < array.length; i++) {
            gcd = gcd(gcd, array[i]);
        }
        return gcd;
    }

    private int gcd(int a, int b) {
        if (a > b) {
            int tmp =  b;
            b = a;
            a = tmp;
        }

        while (a > 0) {
            int rem = b % a;
            b = a;
            a = rem;
        }
        return b;
    }

    private boolean allSeoroso(int[] array, int num) {
        for (int i : array) {
            if (i % num == 0) {
                return false;
            }
        }
        return true;
    }
}