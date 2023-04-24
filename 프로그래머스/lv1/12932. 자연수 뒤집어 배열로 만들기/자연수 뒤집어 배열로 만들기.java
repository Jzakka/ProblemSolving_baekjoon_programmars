class Solution {
    public int[] solution(long n) {
        return new StringBuffer(String.valueOf(n)).reverse().toString().chars().map(c->c-'0').toArray();
    }
}