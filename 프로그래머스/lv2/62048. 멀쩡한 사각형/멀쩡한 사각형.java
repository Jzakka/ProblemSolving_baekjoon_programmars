import java.math.BigInteger;

class Solution {
    public long solution(int w, int h) {
        if (w > h) {
            int tmp = w;
            w = h;
            h = tmp;
        }

        long _w = w / gcd(w, h);
        BigInteger mul = new BigInteger(String.valueOf(h)).multiply(new BigInteger(String.valueOf(w)));
        return mul.subtract(new BigInteger(String.valueOf((h + (w - 1) - (w - 1) / _w)))).longValueExact();
    }

    long gcd(int w, int h) {
        while (w > 0) {
            int remain = h % w;
            h = w;
            w = remain;
        }

        return h;
    }
}