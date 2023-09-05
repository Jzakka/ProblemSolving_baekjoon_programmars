import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String str = br.readLine();

        int n = Integer.parseInt(br.readLine());

        String[][] commands = new String[n][];
        for (int i = 0; i < n; i++) {
            commands[i] = br.readLine().split("\\s+");
        }

        solution(str, commands);

        printRes();
    }

    private static void solution(String str, String[][] commands) {
        Stack<Character> prefix = new Stack<>();
        Stack<Character> suffix = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            prefix.push(c);
        }

        for (String[] command : commands) {
            String action = command[0];
            if (action.equals("L")) {
                if (!prefix.isEmpty()) {
                    suffix.push(prefix.pop());
                }
            } else if (action.equals("D")) {
                if (!suffix.isEmpty()) {
                    prefix.push(suffix.pop());
                }
            } else if (action.equals("B")) {
                if (!prefix.isEmpty()) {
                    prefix.pop();
                }
            } else {
                prefix.push(command[1].charAt(0));
            }
        }

        prefix.stream().forEach(c -> res.append(c));
        while (!suffix.isEmpty()) {
            res.append(suffix.pop());
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
abcdyx
 */