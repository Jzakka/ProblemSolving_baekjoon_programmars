import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    private static int[][] damages = {
            {-9, -3, -1},
            {-9, -1, -3},
            {-3, -1, -9},
            {-3, -9, -1},
            {-1, -3, -9},
            {-1, -9, -3},
    };

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] scv = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(n, scv);

        printRes();
    }

    private static void solution(int n, int[] scv) {
        List<Integer> scvs = Arrays.stream(scv).boxed().collect(Collectors.toList());
        Set<List<Integer>> visited = new HashSet<>();

        Queue<List<Integer>> Q = new LinkedList<>();
        Q.add(scvs);
        visited.add(scvs);

        int attacks = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                List<Integer> scvHps = Q.poll();

                for (int[] damage : damages) {
                    List<Integer> state = attack(scvHps, damage);
                    if (state.stream().allMatch(hp -> hp.equals(0))) {
                        res.append(attacks + 1);
                        return;
                    }
                    if (!visited.contains(state)) {
                        visited.add(state);
                        Q.add(state);
                    }
                }
            }

            attacks++;
        }
    }

    private static List<Integer> attack(List<Integer> scvs, int[] damage) {
        List<Integer> remainHps = new ArrayList<>();
        int dmgIdx = 0;
        for (Integer scvHp : scvs) {
            if (scvHp == 0) {
                remainHps.add(0);
            } else {
                int remainHp = scvHp + damage[dmgIdx];
                if (remainHp < 0) {
                    remainHp = 0;
                }
                remainHps.add(remainHp);
                dmgIdx++;
            }
        }
        return remainHps;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}