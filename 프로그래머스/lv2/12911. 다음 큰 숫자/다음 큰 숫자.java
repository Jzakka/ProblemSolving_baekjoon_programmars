class Solution {
    public int solution(int n) {
        int i=0;
        while ((n & (1 << i)) == 0) {
            i++;
        }

        int oneCount = 0;
        while ((n & (1 << i)) != 0) {
            i++;
            oneCount++;
        }

        int mask = ~((1 << i) - 1);

        n &= mask;
        n |= 1 << i;
        n |= (1 << (oneCount-1))-1;

        return n;
    }
}