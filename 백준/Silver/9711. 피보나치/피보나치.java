import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] PQ = br.readLine().split("\\s+");
            int p = Integer.parseInt(PQ[0]);
            int q = Integer.parseInt(PQ[1]);

            solution(i+1, p,q);
        }
        printRes();
    }

    private static void solution(int testNum, int p, int q) {
        res.append("Case #").append(testNum).append(": ");
        if (p == 1 || p == 2) {
            res.append(1 % q).append("\n");
            return;
        }

        long prePrev = 1;
        long prev = 1;

        for (int i = 3; i <=p; i++) {
            long curPipo = (prev + prePrev) % q;
            prePrev = prev;
            prev = curPipo;
        }

        res.append(prev).append("\n");
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}