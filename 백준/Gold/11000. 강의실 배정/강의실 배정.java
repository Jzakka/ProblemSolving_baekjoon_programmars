import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(arr);

        printRes();
    }

    static class Multiset <K extends Comparable<K>>{
        long size = 0;
        private TreeMap<K, Integer> keyCount;

        public Multiset() {
            keyCount = new TreeMap<>();
        }

        public Multiset(Comparator<K> comparator) {
            keyCount = new TreeMap<>(comparator);
        }

        public void add(K element) {
            if (keyCount.containsKey(element)) {
                keyCount.put(element, keyCount.get(element) + 1);
            } else {
                keyCount.put(element, 1);
            }
            size++;
        }

        public void remove(K element) {
            keyCount.put(element, keyCount.get(element) - 1);
            if (keyCount.get(element) == 0) {
                keyCount.remove(element);
            }
            size--;
        }

        public K floorKey(K element) {
            return keyCount.floorKey(element);
        }
    }

    private static void solution(int[][] arr) {
        Multiset<Integer> multiset = new Multiset<>();

        Arrays.sort(arr, (time1, time2)->{
            if (time1[0] == time2[0]) {
                return time1[1] - time2[1];
            }
            return time1[0] - time2[0];
        });

        for (int[] time : arr) {
            Integer floorKey;
            if ((floorKey = multiset.floorKey(time[0])) == null) {
                multiset.add(time[1]);
            } else {
                multiset.remove(floorKey);
                multiset.add(time[1]);
            }
        }

        res.append(multiset.size);
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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}