import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int x = info[1];

        int[][] menu = new int[n][];
        for (int i = 0; i < n; i++) {
            menu[i] = getInts();
        }

        solution(menu, x);

        bw.write(res.toString());
        bw.close();
    }

    private static void solution(int[][] menu, int x) {
        int ans = Arrays.stream(menu).mapToInt(dailyMenu -> dailyMenu[1]).sum();
        int balance = x - menu.length * 1000;

        List<Integer> diffs = Arrays.stream(menu)
                .mapToInt(dailyMenu -> dailyMenu[0] - dailyMenu[1])
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (Integer diff : diffs) {
            if (diff > 0 && balance >= 4000) {
                ans += diff;
                balance -= 4000;
            }
        }

        res.append(ans);
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }
}
