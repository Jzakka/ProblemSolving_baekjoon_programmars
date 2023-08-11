import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    // E   S  W
    public static void main(String[] args) {
        String[] nms = sc.nextLine().split("\\s+");
        int n = Integer.parseInt(nms[0]);
        int m = Integer.parseInt(nms[1]);

        int[][] works = new int[2][3];
        works[0] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        works[1] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solution(n, m, works);
    }

    private static void solution(int n, int m, int[][] works) {
        System.out.println(recursive(m, n, works, 0, -1, -1));
    }

    private static int recursive(final int majino, int remainDay, int[][] works, int contribute, int workType, int workPlace){
        if (remainDay == 0) {
            return contribute >= majino ? 1 : 0;
        }
        if (contribute >= majino) {
            return (int)Math.pow(6, remainDay);
        }

        int res = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int delta = works[i][j];
                if ( workPlace == j) {
                    delta >>= 1;
                }
                res += recursive(majino, remainDay - 1, works, contribute + delta, i, j);
            }
        }

        return res;
    }
}