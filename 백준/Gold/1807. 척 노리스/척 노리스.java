import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        long s;
        while ((s = Long.parseLong(br.readLine())) != 0) {
            solution(s);
        }

        printRes();
    }

    private static void solution(long k) {
        long lo = 0;
        long hi = 2*k;

        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            long lenOfSeq = lenOfSeq(mid - 1);
            long lenOfNum = lenOfNum(mid);
            if (lenOfSeq >= k) {
                hi = mid;
            } else if (lenOfSeq + lenOfNum < k) {
                lo = mid;
            } else {
                long an = AN(mid);
                int digit = getDigit(an, lenOfNum - (k - lenOfSeq - 1) - 1);
                res.append(digit).append("\n");
                return;
            }
        }

        return;
    }

    private static int getDigit(long num, long i) {
        while (i > 0) {
            num /= 10;
            i--;
        }
        return (int) (num % 10);
    }

    private static long lenOfNum(long num) {
        long len = num % 4 == 0 ? 0 : 1;
        while (num > 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    private static long AN(long N) {
        if (N % 4 == 0) {
            return N;
        }

        int lastDigit = (int) (N % 10);
        switch (lastDigit) {
            case 0:
                return N * 10;
            case 1:
                return N * 10 + 2;
            case 2:
                return N * 10;
            case 3:
                return N * 10 + 2;
            case 4:
                return N * 10;
            case 5:
                return N * 10 + 2;
            case 6:
                return N * 10;
            case 7:
                return N * 10 + 2;
            case 8:
                return N * 10;
            default: // 9
                return N * 10 + 2;
        }
    }

    private static long lenOfSeq(long n) {
        long N = n;
        long len = 0;
        int i = 0;
        while (n / 10 > 0) {
            len += 9 * Math.pow(10, i) * (i + 2);
            i++;
            n /= 10;
        }
        len += (N - Math.pow(10, i) + 1) * (i + 2);
        len -= N / 4;

        return len;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }

    /*
    12
    20
    32
    4
    52
    60
    72
    8
    92
    104
    112
    12
    132
    140
     */
}