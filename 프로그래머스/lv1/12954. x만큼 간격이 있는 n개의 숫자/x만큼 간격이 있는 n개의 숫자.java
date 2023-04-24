class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        for (long i = x, j = 0; j < n; j++, i += x) {
            answer[(int) j] = i;
        }
        return answer;
    }
}