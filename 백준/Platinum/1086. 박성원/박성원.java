import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        String[] set = new String[n];

        for (int i = 0; i < n; i++) {
            set[i] = br.readLine();
        }

        int k = Integer.parseInt(br.readLine());

        solution(set, k);

        printRes();
    }

    private static void solution(String[] set, int k) {
        int n = set.length;
        long[][] DP = new long[1 << n][k];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, 0));
        DP[0][0] = 1;
        BigInteger K = new BigInteger(String.valueOf(k));
        int[] mod = Arrays.stream(set).mapToInt(num -> new BigInteger(num).mod(K).intValue()).toArray();
        int[] appendLens = Arrays.stream(set).mapToInt(num -> new BigInteger("10").pow(num.length()).mod(K).intValue()).toArray();
        int opCount = 0;
        for (int bits = 0; bits < DP.length; bits++) {
            for (int j = 0; j < DP[0].length; j++) {
                for (int i = 0; i < n; i++) {
                    if (((bits >> i) & 1) == 0) {
                        int newRemain = (((appendLens[i] % k) * j % k) + mod[i]) % k;

                        DP[bits | (1 << i)][newRemain] += DP[bits][j];

//                        System.out.printf("bits:%d, j:%d, i:%d%n", bits, j, i);
                    }
                    opCount++;
                }
            }
        }

//        System.out.println("opCount = " + opCount);
//        System.out.println("resulting");
        long totalCases = 1l;
        for (int i = 1; i <= n; i++) {
            totalCases *= i;
        }

        String reducedFraction = reduceFraction(DP[DP.length - 1][0], totalCases);
        res.append(reducedFraction);
    }

    private static String reduceFraction(long numerator, long denominator) {
        if (numerator == denominator) {
            return "1/1";
        } else if (numerator == 0) {
            return "0/1";
        }
        long gcd = gcd(numerator, denominator);

        return (numerator/gcd) + "/" + (denominator/gcd);
    }

    private static long gcd(long a, long b) {
        if (a > b) { // a가 b보다 작게 만들기
            long temp = a;
            a = b;
            b = temp;
        }

        do {
            long remain = b% a;
            b = a;
            a = remain;
        } while (a!=0);

        return b;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
15
12341234121234123412123412341212341234121234123412
12341234121234123412123412341212341234121234123412
12341234121234123412123412341212341234121234123412
12341234121234123412123412341212341234121234123412
12341234121234123412123412341212341234121234123411
12341234121234123412123412341212341234121234123414
12341234121234123412123412341212341234121234123413
12341234121234123412123412341212341264121234123412
12341234121234123412123412341212341234121234123418
12311234121234123412123412341212341234421234123412
12341234121234123412123412341212341234121234123412
12341234121234123412123412341212341234121234123412
12341234121234123412123412341212341234121214123412
12341234121234123412123412341212341234121234113412
12341234121234123412123412341212341234121234123412
99
 */