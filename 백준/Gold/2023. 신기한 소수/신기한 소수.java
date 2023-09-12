import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        List<Integer> strangePrimitives = new ArrayList<>();
        dfs(strangePrimitives, n, 2, 1);
        dfs(strangePrimitives, n, 3, 1);
        dfs(strangePrimitives, n, 5, 1);
        dfs(strangePrimitives, n, 7, 1);

        Collections.sort(strangePrimitives);

        strangePrimitives.stream().forEach(num -> res.append(num).append("\n"));
    }

    private static void dfs(List<Integer> strangePrimitives, int n, int current, int idx) {
        if (idx == n) {
            strangePrimitives.add(current);
            return;
        }

        if (isPrimitive(current * 10 + 1)) {
            dfs(strangePrimitives, n, current*10 + 1, idx+1);
        }

        if (isPrimitive(current * 10 + 3)) {
            dfs(strangePrimitives, n, current*10 + 3, idx+1);
        }

        if (isPrimitive(current * 10 + 5)) {
            dfs(strangePrimitives, n, current*10 + 5, idx+1);
        }

        if (isPrimitive(current * 10 + 7)) {
            dfs(strangePrimitives, n, current*10 + 7, idx+1);
        }

        if (isPrimitive(current * 10 + 9)) {
            dfs(strangePrimitives, n, current*10 + 9, idx+1);
        }
    }

    private static boolean isPrimitive(Integer num) {
        return new BigInteger(num.toString()).isProbablePrime(10);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
n=10
9876543210

n=11
89876543210
98765432101
10123456789

n=12
989876543210
789876543210
898765432101

898765432101
987654321010
987654321012

210123456789
010123456789
101234567898

n=13
8989876543210
9898765432101

8789876543210
6789876543210
7898765432101

9[]0 => 8[]0, 9[]1
b[]0 => a[]0, c[]0, b[]1
0[]b => 1[]b, 1[]
 */