class Solution {
    public int solution(long n) {
        int jmpCnt = 0;
        while (n > 0) {
            if ((n & 1) == 0) {
                n >>= 1;
            } else {
                n--;
                jmpCnt++;
            }
        }
        return jmpCnt;
    }
}