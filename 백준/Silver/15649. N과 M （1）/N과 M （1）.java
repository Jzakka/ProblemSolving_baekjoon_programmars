import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int[] nk = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = nk[0];
        int k = nk[1];

        solution(n, k);

        printRes();
    }

    private static void solution(int n, int k) {
        int[] arr = IntStream.rangeClosed(1, n).toArray();
        Set<Integer> visited = new HashSet<>();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!visited.contains(i+1)) {
                list.add(i+1);
                visited.add(i + 1);
                recursive(visited, k, arr, 1, list);
                list.remove(list.size()-1);
                visited.remove(i+1);
            }
        }
    }

    private static void recursive(Set<Integer> visited, int k, int[] arr, int curIdx, List<Integer> list) {
        if (list.size() == k) {
            list.stream().forEach(num -> res.append(num).append(" "));
            res.append("\n");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited.contains(i+1)) {
                list.add(i+1);
                visited.add(i + 1);
                recursive(visited, k, arr, curIdx + 1, list);
                list.remove(list.size()-1);
                visited.remove(i+1);
            }
        }
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