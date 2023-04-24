class Solution {
    public long solution(long n) {
        double root = Math.sqrt(n);
        boolean flag = Math.floor(root) == root;

        return flag ? (long) ((root + 1) * (root + 1)) : -1;
    }
}