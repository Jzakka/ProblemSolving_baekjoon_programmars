class Solution {
    public int[] solution(int n, int m) {
        int gcd = gcd(n, m);
        int scm = n * m / gcd;
        return new int[]{gcd, scm};
    }

    public int gcd(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (a > 0) {
            int r = b % a;
            b = a;
            a = r;
        }

        return b;
    }
}