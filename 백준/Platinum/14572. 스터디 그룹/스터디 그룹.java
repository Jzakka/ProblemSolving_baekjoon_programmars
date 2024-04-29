import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int N = info[0];
        int K = info[1];
        int D = info[2];

        Student[] students = new Student[N];

        for (int i = 0; i < N; i++) {
            int[] studentInfo = getInts();
            int ability = studentInfo[1];
            int[] algorithms = getInts();
            students[i] = new Student(algorithms, ability);
        }

        calc(students, K, D);

        bw.write(res.toString());
        bw.close();
    }

    private static void calc(Student[] students, int k, int d) {
        Arrays.sort(students);
        int[] multiset = new int[k + 1];

        int s = 0;
        int e = 0;

        int ans = 0;
        while (e < students.length) {
            if (students[e].ability - students[s].ability > d) {
                for (int algorithm : students[s++].algorithms) {
                    multiset[algorithm]--;
                }
            } else {
                for (int algorithm : students[e].algorithms) {
                    multiset[algorithm]++;
                }
                ans = Math.max(ans, getEfficiency(multiset, e - s + 1));
                e++;
            }
        }

        res.append(ans);
    }

    private static int getEfficiency(int[] multiset, int studentsCnt) {
        long union = Arrays.stream(multiset).filter(i -> i > 0).count();
        long intersection = Arrays.stream(multiset).filter(i -> i == studentsCnt).count();

        return (int) ((union - intersection) * studentsCnt);
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    static class Student implements Comparable<Student> {
        int[] algorithms;
        int ability;

        public Student(int[] algorithms, int ability) {
            this.algorithms = algorithms;
            this.ability = ability;
        }

        @Override
        public int compareTo(Student o) {
            if (ability == o.ability) {
                return algorithms.length - o.algorithms.length;
            }
            return ability - o.ability;
        }
    }
}
