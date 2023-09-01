import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String word = br.readLine();
        solution(word);
        printRes();
    }

    private static void solution(String word) {
        int[] ans = new int[26];

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            if (letter != ' ') {
                ans[letter - 'a']++;
            }
        }

        for (int count : ans) {
            res.append(count).append(' ');
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}