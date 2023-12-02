import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[][] times = new long[n][];

        for (int i = 0; i < n; i++) {
            times[i] = getLongs();
        }

        solution(times);

        printRes();
    }

    private static void solution(long[][] times) {
        Arrays.sort(times, (r1, r2)->{
            if (r1[1] == r2[1]) {
                return (int)(r1[2] - r2[2]);
            }
            return (int)(r1[1] - r2[1]);
        });

        TreeSet<Long> rooms = new TreeSet<>();
        Map<Long, Integer> counts = new HashMap<>();

        for (long[] time : times) {
            long start = time[1];
            long end = time[2];


            Long floor;
            if (rooms.isEmpty() || (floor = rooms.floor(start)) == null) {
                setAdd(rooms, counts, end);
            } else{
                setSub(rooms, counts, floor);
                setAdd(rooms, counts, end);
            }
        }

        int ans = counts.values().stream().mapToInt(Integer::intValue).sum();

        res.append(ans);
    }

    private static void setAdd(TreeSet<Long> rooms, Map<Long, Integer> counts, Long val) {
        rooms.add(val);
        if (!counts.containsKey(val)) {
            counts.put(val, 1);
        } else {
            counts.put(val, counts.get(val) + 1);
        }
    }

    private static void setSub(TreeSet<Long> rooms, Map<Long, Integer> counts, Long val) {
        counts.put(val, counts.get(val) - 1);
        if (counts.get(val) == 0) {
            counts.remove(val);
            rooms.remove(val);
        }
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