class Solution {
    public int solution(int num) {
        long copied = num;
        int cnt = 0;
        while (cnt < 500) {
            // System.out.println(copied);
            if (copied == 1) {
                return cnt;
            }
            else if ((copied & 1) == 0) {
                copied >>= 1;
            } else {
                copied = copied * 3 + 1;
            }
            cnt++;
        }
        return -1;
    }
}