class Solution {
    public int solution(int a, int b, int n) {
        int totalService = 0;
        while (n >= a) {
            int remain = n%a;
            int service = n / a * b;
            totalService += service;
            n = remain + service;
        }
        return totalService;
    }
}