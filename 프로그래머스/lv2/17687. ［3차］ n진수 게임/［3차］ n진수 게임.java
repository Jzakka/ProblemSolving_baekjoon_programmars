import java.util.*;

class Solution {
    Map<Integer, Character> alphabet = new HashMap<>();
    {
        alphabet.put(0, '0');
        alphabet.put(1, '1');
        alphabet.put(2, '2');
        alphabet.put(3, '3');
        alphabet.put(4, '4');
        alphabet.put(5, '5');
        alphabet.put(6, '6');
        alphabet.put(7, '7');
        alphabet.put(8, '8');
        alphabet.put(9, '9');

        alphabet.put(10, 'A');
        alphabet.put(11, 'B');
        alphabet.put(12, 'C');
        alphabet.put(13, 'D');
        alphabet.put(14, 'E');
        alphabet.put(15, 'F');
    }
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (sb.length() < t * m) {
            sb.append(convert(i++, n));
        }

        StringBuilder res = new StringBuilder();
        for (int s = 0; s < t; s++) {
            res.append(sb.charAt(p - 1 + m * s));
        }

        return res.toString();
    }

    public String convert(int decimal, int n) {
        if (decimal == 0) {
            return "0";
        }
        StringBuilder converted = new StringBuilder();
        while (decimal > 0) {
            converted.append(alphabet.get(decimal % n));
            decimal /= n;
        }
        return converted.reverse().toString();
    }
}

// 0 1 1 0 1 1 1 0 0 ...
// 0 1 2 1 0 1 1 1 2 2 0 ....

// 내 순서 2 이고 사람이 2명, 16개를 미리 알아야 함
// 16 * 2의 길이 까진 알아야 함
// 거기서 2+2d번째 수를 알아야함