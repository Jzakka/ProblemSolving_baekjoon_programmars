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

        solution(n, course);
        printRes();
    }

    static class Course implements Comparable<Course>{
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
        for (int i = 0; i < courses.length; i++) {
            int s = course[i][0];
            int e = course[i][1];

            courses[i] = new Course(s, e, i + 1);
        }

        // key : val
        // start: {end, num}
        TreeMap<Integer, int[]> Q1 = new TreeMap<>(); // 내가 들어갈 수 있니 Q
        TreeMap<Integer, int[]> Q2 = new TreeMap<>(); // 내가 들어가고 나서 삭제해야 하는 Q

        Arrays.sort(courses);
        TreeSet<Integer> ans = IntStream.rangeClosed(1, courses.length).boxed().collect(Collectors.toCollection(TreeSet::new));

        for (Course c : courses) {
            if (c.start <= c.end) {
                if (!Q1.isEmpty() && Q1.lastEntry().getValue()[0] >= c.end) {
                    ans.remove(c.num);
                } else {
                    Q1.put(c.start, new int[]{c.end, c.num});
                    Q2.put(c.start + n, new int[]{c.end + n, c.num});
                }
            } else {
                if (!Q1.isEmpty() && Q1.lastEntry().getValue()[0] >= c.end + n) {
                    ans.remove(c.num);
                } else {
                    // 난 들어가고 기존 것들을 삭제해야 함
                    while (!Q2.isEmpty()) {
                        Map.Entry<Integer, int[]> ceilingEntry = Q2.ceilingEntry(c.start);
                        if (ceilingEntry != null && ceilingEntry.getValue()[0] <= c.end + n) {
                            int ceilNum = ceilingEntry.getValue()[1];
                            Q2.remove(ceilingEntry.getKey());
                            ans.remove(ceilNum);
                        } else {
                            break;
                        }
                    }
                    int[] value = {c.end + n, c.num};
                    Q1.put(c.start, value);
                    Q2.put(c.start, value);
                }
            }
        }

        ans.forEach(i -> res.append(i).append(" "));
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
6
1 1 1 3 3 3
 */