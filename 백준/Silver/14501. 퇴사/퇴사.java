import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] schedule = new int[n][];
        for(int i=0;i<n;i++){
            schedule[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(schedule);

        printRes();
    }

    private static void solution(int[][] schedule) {
        int maxProfit = dfs(schedule, 0, 0);

        res.append(maxProfit);
    }

    private static int dfs(int[][] schedule, int today, int profit) {
        if (today >= schedule.length) {
            return profit;
        }

        int timeCost = schedule[today][0];
        int dayProfit = schedule[today][1];

        int doJob = dfs(schedule, today + timeCost, today+timeCost <= schedule.length ? profit + dayProfit : profit);
        int skipJob = dfs(schedule, today + 1, profit);

        return Math.max(doJob, skipJob);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}