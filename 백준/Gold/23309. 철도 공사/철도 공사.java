import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MAX_STATION = 1_000_001;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] stationInput = new int[n];
        st = new StringTokenizer(br.readLine());
        int k = 0;
        while (st.hasMoreTokens()) {
            stationInput[k++] = Integer.parseInt(st.nextToken());
        }

        String[] actions = new String[m];
        int[][] arguments = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            actions[i] = st.nextToken();
            arguments[i][0] = Integer.parseInt(st.nextToken());
            if (actions[i].charAt(0) == 'B') {
                arguments[i][1] = Integer.parseInt(st.nextToken());
            }
        }
        solution(stationInput, actions, arguments);

        printRes();
    }

    private static void solution(int[] stationInput, String[] actions, int[][] arguments) {
        int[] prevStation = new int[MAX_STATION];
        int[] nextStation = new int[MAX_STATION];

        for (int i = 1; i < stationInput.length - 1; i++) {
            int cur = stationInput[i];
            nextStation[cur] = stationInput[i + 1];
            prevStation[cur] = stationInput[i - 1];
        }
        int last = stationInput[stationInput.length - 1];
        int first = stationInput[0];
        nextStation[last] = first;
        prevStation[first] = last;
        if (stationInput.length > 1) {
            prevStation[last] = stationInput[stationInput.length - 2];
            nextStation[first] = stationInput[1];
        }

        for (int i = 0; i < actions.length; i++) {
            String command = actions[i];
            int[] args = arguments[i];
            if (command.equals("BN")) {
                res.append(nextStation[args[0]]).append("\n");
                insert(prevStation, nextStation, args[1], args[0], nextStation[args[0]]);
            } else if (command.equals("BP")) {
                res.append(prevStation[args[0]]).append("\n");
                insert(prevStation, nextStation, args[1], prevStation[args[0]], args[0]);
            } else if (command.equals("CN")) {
                res.append(nextStation[args[0]]).append("\n");
                delete(prevStation, nextStation, nextStation[args[0]]);
            } else {
                res.append(prevStation[args[0]]).append("\n");
                delete(prevStation, nextStation, prevStation[args[0]]);
            }
        }
    }

    private static void insert(int[] prevStation, int[] nextStation, int target, int prev, int next) {
        if (prevStation[target] != 0) {
            return;
        }

        nextStation[prev] = target;
        prevStation[next] = target;

        nextStation[target] = next;
        prevStation[target] = prev;
    }

    private static void delete(int[] prevStation, int[] nextStation, int target) {
        int prev = prevStation[target];
        int next = nextStation[target];

        nextStation[prev] = next;
        prevStation[next] = prev;

        prevStation[target] = nextStation[target] = 0;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}