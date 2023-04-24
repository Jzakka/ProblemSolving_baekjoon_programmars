class Solution {
    public int[] solution(int n, long left, long right) {
        int[] result = new int[(int) (right - left + 1)];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int)entity(n, i + left);
        }

        return result;
    }

    private long entity(final long n,long i) {
        long row = i / n;
        long col = i % n;
        return Math.max(row, col)+1;
    }
}