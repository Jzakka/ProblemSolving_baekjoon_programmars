import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] people = new int[n];
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(br.readLine());
        }
        solution(people);
        bw.write(res.toString());
        bw.flush();
        bw.close();
    }

    private static void solution(int[] people) {
        Arrays.sort(people);
        int time = 0;

        StringBuilder path = new StringBuilder();
        int left = people.length;
        while (left > 0) {
            switch (left) {
                case 1:
                    time += people[0];
                    left--;
                    path.append(people[0]).append("\n");
                    break;
                case 2:
                    time +=  people[1];
                    left -= 2;
                    path.append(people[0]).append(" ").append(people[1]).append("\n");
                    break;
                case 3:
                    time += people[0] + people[1] + people[2];
                    left -= 3;
                    path
                            .append(people[0]).append(" ").append(people[2]).append("\n")
                            .append(people[0]).append("\n")
                            .append(people[0]).append(" ").append(people[1]).append("\n");
                    break;
                default:
                    int case1 = 2 * people[0] + people[left - 1] + people[left - 2];
                    int case2 = people[0] + 2 * people[1] + people[left - 1];
                    if (case1 <= case2) {
                        path
                                .append(people[0]).append(" ").append(people[left - 1]).append("\n")
                                .append(people[0]).append("\n")
                                .append(people[0]).append(" ").append(people[left - 2]).append("\n")
                                .append(people[0]).append("\n");
                        time += case1;
                    } else {
                        path
                                .append(people[0]).append(" ").append(people[1]).append("\n")
                                .append(people[0]).append("\n")
                                .append(people[left - 1]).append(" ").append(people[left - 2]).append("\n")
                                .append(people[1]).append("\n");
                        time += case2;
                    }
                    left -= 2;
            }
        }
        res.append(time).append("\n").append(path);
    }
}