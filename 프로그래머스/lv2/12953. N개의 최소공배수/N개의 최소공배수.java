class Solution {
    public int solution(int[] arr) {
        int gcd;
        int lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(lcm, arr[i]);
            lcm = lcm * arr[i] / gcd;
        }

        return lcm;
    }

    private int gcd(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        int remain;

        while (small > 0) {
            remain = big % small;
            big = small;
            small = remain;
        }

        return big;
    }
}