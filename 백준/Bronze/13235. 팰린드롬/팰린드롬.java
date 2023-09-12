import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        String word = br.readLine();

        solution(word);

        printRes();
    }

    private static void solution(String word) {
        int l, r;
        l = 0;
        r = word.length() - 1;

        while (l < r) {
            if (word.charAt(l) != word.charAt(r)) {
                res.append("false");
                return;
            }
            l++;
            r--;
        }

        res.append("true");
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