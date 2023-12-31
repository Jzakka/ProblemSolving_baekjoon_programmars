import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int k = info[1];
        int s = info[2];

        int[][] students = new int[n][];
        for (int i = 0; i < n; i++) {
            students[i] = getInts();
        }

        solution(n, k, s, students);

        printRes();
    }

    private static void solution(int n, int k, int s, int[][] students) {
        TreeMap<Integer, Integer> underStudents = new TreeMap<>(Comparator.comparingInt(i -> -i));
        TreeMap<Integer, Integer> overStudents = new TreeMap<>(Comparator.comparingInt(i -> -i));

        for (int[] student : students) {
            if (student[0] < s) {
                underStudents.put(2 * s - student[0], student[1]);
            } else {
                overStudents.put(student[0], student[1]);
            }
        }

        int underMinDist = getMinDist(underStudents, s, k);
        int overMinDist = getMinDist(overStudents, s, k);

        res.append(underMinDist + overMinDist);
    }

    private static int getMinDist(TreeMap<Integer, Integer> students, int busPos, int busCap) {
        int[][] studentsGroup = students.entrySet().stream()
                .map(e -> new int[]{e.getKey(), e.getValue()})
                .toArray(int[][]::new);

        int minDist = 0;

        for (int i = 0; i < studentsGroup.length; i++) {
            int studentsPos = studentsGroup[i][0];
            int studentsCnt = studentsGroup[i][1];

            int q = studentsCnt / busCap;
            int r = studentsCnt - q * busCap;

            minDist += (studentsPos - busPos) * 2 * q;
            if (r > 0) {
                minDist += (studentsPos - busPos) * 2;
                int additiveCap = busCap - r;
                for (int j = i + 1; j < studentsGroup.length && additiveCap > 0; j++) {
                    if (studentsGroup[j][1] >= additiveCap) {
                        studentsGroup[j][1] -= additiveCap;
                        additiveCap = 0;
                    } else {
                        additiveCap -= studentsGroup[j][1];
                        studentsGroup[j][1] = 0;
                    }
                }
            }
        }

        return minDist;
    }

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}