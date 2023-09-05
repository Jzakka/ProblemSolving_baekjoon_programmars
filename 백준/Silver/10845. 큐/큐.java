import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Deque<Integer> q = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] commands = br.readLine().split("\\s+");

            String command = commands[0];
            if (command.equals("push")) {
                q.add(Integer.parseInt(commands[1]));
            } else if (command.equals("pop")) {
                res.append(q.isEmpty() ? -1 : q.poll()).append("\n");
            } else if (command.equals("size")) {
                res.append(q.size()).append("\n");
            } else if (command.equals("front")) {
                res.append(q.isEmpty() ? -1 : q.getFirst()).append("\n");
            } else if (command.equals("back")) {
                res.append(q.isEmpty() ? -1 : q.getLast()).append("\n");
            } else { //empty
                res.append(q.isEmpty() ? 1 : 0).append("\n");
            }
        }

        printRes();
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*

 */