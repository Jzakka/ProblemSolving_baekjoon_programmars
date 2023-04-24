import java.math.BigInteger;
import java.util.*;

//BigInteger 의 isProbablePrime() 은 진짜 전설이다..
//인수 cenrtainty가 높을 수록 정확도가 높아진다. (1-(1/2)^cer)
class Solution {
    public int solution(int n, int k) {
        String converted = convert(n, k);

        return (int) Arrays
                .stream(converted.split("0+"))
                .map(BigInteger::new)
                .filter(num -> num.isProbablePrime(100))
                .count();
    }

    public String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int remain = n % k;
            sb.append(remain);
            n /= k;
        }

        return sb.reverse().toString();
    }
}