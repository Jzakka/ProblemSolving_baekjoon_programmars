import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        long[] people = Arrays.stream(br.readLine().split("\\s+")).mapToLong(Long::parseLong).toArray();
        String[] thirdLine = br.readLine().split("\\s");
        long b = Long.parseLong(thirdLine[0]);
        long c = Long.parseLong(thirdLine[1]);
        solution(n, people, b, c);
        bw.write(res.toString());
        bw.flush();
        bw.close();
    }

    private static void solution(int n, long[] people, long b, long c) {
        long total = Arrays.stream(people).map(p -> {
            long count = 1;
            p -= b;
            if (p > 0) {
                long subCnt = p / c;
                count += subCnt;
                if (subCnt * c < p) {
                    count++;
                }
            }
            return count;
        }).reduce(0, (sum, cnt) -> sum += cnt);
        res.append(total);
    }
}