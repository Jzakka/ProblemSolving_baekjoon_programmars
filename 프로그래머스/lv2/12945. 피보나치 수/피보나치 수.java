class Solution {
    public int solution(int n) {
        int nMinusTwothFibonacci = 0;
        int nMinusOnethFibonacci = 1;
        int nthFibonacci = 0;


        // 0 1 2

        for (int i = 2; i <= n; i++) {
            nthFibonacci = (nMinusOnethFibonacci%1234567 + nMinusTwothFibonacci%1234567)%1234567;
            nMinusTwothFibonacci = nMinusOnethFibonacci%1234567;
            nMinusOnethFibonacci = nthFibonacci%1234567;
        }

        return nthFibonacci % 1234567;
    }
}