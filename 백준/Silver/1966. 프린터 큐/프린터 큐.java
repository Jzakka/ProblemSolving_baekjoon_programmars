import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Deque<Integer> q = new ArrayDeque<>();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] inputs = br.readLine().split("\\s+");
            int target = Integer.parseInt(inputs[1]);

            int[] docs = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            solution(docs, target);
        }

        printRes();
    }

    static class Number implements Comparable<Number> {
        int order;
        int num;

        public Number(int order, int num) {
            this.order = order;
            this.num = num;
        }

        @Override
        public int compareTo(Number o) {
            if (o.num == num) {
                return order - o.order;
            }
            return o.num - num;
        }
    }

    public static void solution(int[] docArr, int t) {
        List<Number> docs = IntStream.range(0, docArr.length)
                .mapToObj(i -> new Number(i, docArr[i]))
                .collect(Collectors.toList());

        Number target = docs.get(t);

        int i = 0;
        int cnt = 1;
        while (i < docs.size()) {
            Number number = docs.get(i);

            if (biggest(docs, i, number)) {
                if (number.equals(target)) {
                    res.append(cnt).append("\n");
                    return;
                }
                cnt++;
            } else {
                docs.add(number);
            }
            i++;
        }
    }

    private static boolean biggest(List<Number> docs, int start, Number number) {
        for (int i = start; i < docs.size(); i++) {
            Number doc = docs.get(i);
            if (doc.num > number.num) {
                return false;
            }
        }
        return true;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*

 */