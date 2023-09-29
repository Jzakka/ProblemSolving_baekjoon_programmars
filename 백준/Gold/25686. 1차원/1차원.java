import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        List<Integer> ans = divideAndConquer(IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList()));

        ans.forEach(num -> res.append(num).append(" "));
    }

    private static List<Integer> divideAndConquer(List<Integer> list) {
        if (list.size() == 1) {
            return list;
        }
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (((i + 1) & 1) == 1) {
                odds.add(list.get(i));
            } else {
                evens.add(list.get(i));
            }
        }

        List<Integer> left = divideAndConquer(odds);
        List<Integer> right = divideAndConquer(evens);

        left.addAll(right);
        return left;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */