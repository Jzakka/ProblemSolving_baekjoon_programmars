class Solution {
    public long solution(int n, long l, long r) {
        long result = one(n, r) - one(n, l - 1);
        return result;
    }

    private long one(int n, long x) {
        if (n == 0) {
            return 0;
        }

        int sector = (int) (x / (long)Math.pow(5, n - 1));

        return switch (sector) {
            case 0 -> one(n - 1, x % (long) Math.pow(5, n - 1));
            case 1 -> one(n - 1, x % (long) Math.pow(5, n - 1)) + (long) Math.pow(4, n - 1);
            case 2 -> 2 * (long) Math.pow(4, n - 1);
            case 3 -> 2 * (long) Math.pow(4, n - 1) + one(n - 1, x % (long) Math.pow(5, n - 1));
            case 4 -> 3 * (long) Math.pow(4, n - 1) + one(n - 1, x % (long) Math.pow(5, n - 1));
            default -> 4 * (long) Math.pow(4, n - 1);
        };
    }
}