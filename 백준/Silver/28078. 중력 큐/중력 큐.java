import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int WALL = -1;
    private static int curState = 0;
    private static int totalBalls = 0;
    private static int totalWalls = 0;
    private static StringBuilder res = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] queries = new String[n];
        for (int i = 0; i < n; i++) {
            String query = br.readLine();
            queries[i] = query;
        }
        solution(queries);
    }

    private static void solution(String[] queries) throws IOException {
        Deque<Integer> gravityQueue = new ArrayDeque<>();

        for (String query : queries) {
            execute(query, gravityQueue);
        }
        bw.write(res.toString());
        bw.flush();
        bw.close();
    }

    private static void execute(String query, Deque<Integer> gravityQueue) {
        String[] args = query.split("\\s+");
        String action = args[0];

        if (action.equals("push")) {
            push(gravityQueue, args[1]);
        } else if (action.equals("count")) {
            count(args[1]);
        } else if (action.equals("rotate")) {
            rotate(args[1]);
        } else { // pop
            pop(gravityQueue);
        }

        if (curState == 1) {
            if (!gravityQueue.isEmpty() && gravityQueue.peekFirst() != WALL) {
                Integer balls = gravityQueue.pollFirst();
                totalBalls -= balls;
            }
        } else if (curState == 3) {
            if (!gravityQueue.isEmpty() && gravityQueue.peekLast() != WALL) {
                Integer balls = gravityQueue.pollLast();
                totalBalls -= balls;
            }
        }
    }

    private static void pop(Deque<Integer> gravityQueue) {
        if (!gravityQueue.isEmpty()) {
            Integer popped = gravityQueue.peekFirst();

            if (popped.equals(WALL)) {
                gravityQueue.pollFirst();
                totalWalls--;
            } else if (popped == 1) {
                gravityQueue.pollFirst();
                totalBalls--;
            } else {
                gravityQueue.offerFirst(gravityQueue.pollFirst() - 1);
                totalBalls--;
            }
        }
    }

    private static void rotate(String arg) {
        if (arg.equals("r")) {
            curState = (curState + 1) % 4;
        } else {
            curState = (curState + 3) % 4;
        }
    }

    private static void count(String arg) {
        if (arg.equals("b")) {
            res.append(totalBalls).append("\n");
        } else {
            res.append(totalWalls).append("\n");
        }
    }

    private static void push(Deque<Integer> gravityQueue, String arg) {
        if (arg.equals("b")) {
            if (gravityQueue.isEmpty() || gravityQueue.peekLast() == WALL) {
                gravityQueue.offerLast(1);
            } else {
                gravityQueue.offerLast(gravityQueue.pollLast() + 1);
            }
            totalBalls++;
        } else {
            gravityQueue.offerLast(WALL);
            totalWalls++;
        }
    }
}