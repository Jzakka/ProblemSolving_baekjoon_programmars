import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] course = new int[m][];
        for (int i = 0; i < m; i++) {
            course[i] = getInts();
        }

//        int n = 1_000_000_000;
//        int[][] course = new int[500_000][];
//        for (int i = 0; i < 500_000; i++) {
//            course[i] = new int[]{n - i - 1, i};
//        }

        solution(n, course);
        printRes();
    }

    static class Course implements Comparable<Course> {
        int start;
        int end;
        int num;

        public Course(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        // 시작이 같으면 더 작은 놈이 나중에 드감 =>  이미 Q에 넣었던것을 빼는 번거로운 작업을 안하려고
        @Override
        public int compareTo(Course o) {
            if (start == o.start) {
                return o.end - end;
            }
            return start - o.start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Course course = (Course) o;
            return start == course.start && end == course.end && num == course.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, num);
        }
    }

    private static void solution(int n, int[][] course) {
        Course[] courses = new Course[course.length];

        int last = -1;

        for (int i = 0; i < courses.length; i++) {
            int s = course[i][0];
            int e = course[i][1];
            if (s > e) {
                last = Math.max(last, e);
                e += n;
            }

            courses[i] = new Course(s, e, i + 1);
        }

        // {end, num}
        Deque<int[]> Q = new ArrayDeque<>();

        Arrays.sort(courses);

        for (Course c : courses) {
            if (Q.isEmpty() || Q.peekLast()[0] < c.end) {
                Q.offerLast(new int[]{c.end, c.num});
            }
        }

        while (Q.peekFirst()[0] <= last) {
            Q.pollFirst();
        }

        Q.stream().mapToInt(i -> i[1]).sorted().forEach(i -> res.append(i).append(" "));

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

/*
8
4
7 0
6 1
5 2
4 3
 */