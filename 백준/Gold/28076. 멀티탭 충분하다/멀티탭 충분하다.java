import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(sc.nextLine());
        int[] multitaps = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solution(n, multitaps);
    }

    private static void solution(int n, int[] multitaps) {
        multitaps = Arrays.stream(multitaps).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray();


        if (n == 1) {
            System.out.println(multitaps[0]);
            return;
        } else if (n == 2) {
            System.out.println(multitaps[0] + multitaps[1] - 1);
            return;
        } else if (n == 3) {
            System.out.println(multitaps[0] + multitaps[1] + multitaps[2] - 3);
            return;
        }

        int k = n / 3;
        if (n % 3 == 0) {
            System.out.println(multitaps[0] + multitaps[k] + multitaps[2*k] - 3);
        } else if (n % 3 == 1) {
            int res = Math.min(multitaps[0] + multitaps[k + 1] + multitaps[2 * k + 1] - 3,
                    Math.min(multitaps[0] + multitaps[k] + multitaps[2 * k + 1] - 3,
                            multitaps[0] + multitaps[k] + multitaps[2 * k] - 3));
            System.out.println(res);
        } else {
            int res = Math.min(multitaps[0] + multitaps[k + 1] + multitaps[2 * k + 2] - 3,
                    Math.min(multitaps[0] + multitaps[k] + multitaps[2 * k + 1] - 3,
                            multitaps[0] + multitaps[k + 1] + multitaps[2 * k + 1] - 3));
            System.out.println(res);
        }
    }
}