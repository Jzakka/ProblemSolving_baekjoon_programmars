class Solution {
    public long solution(int k, int d) {
        long sum = 0;
        for (int i = 0; i*k <= d; i++) {
            long h = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(i * k, 2));
            sum += (h / k) + 1;
        }

        return sum;
    }
}