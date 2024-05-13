import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int k = info[1];

        int[] friends = new int[n];

        for (int i = 0; i < n; i++) {
            friends[i] = Integer.parseInt(br.readLine());
        }

        int ans = solution(friends, k);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int[] friends, int k) {
        int[][] sections = getSection(friends);

        int mergeSectionCounts = sections.length - k;
        if (mergeSectionCounts <= 0) {
            return friends.length;
        }

        // {다음 그룹과의 거리, 현재 그룹의 idx}
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        for (int i = 0; i < sections.length - 1; i++) {
            int diff = sections[i + 1][0] - sections[i][1];
            PQ.add(new int[]{diff, i});
        }

        int[][] addedSections = new int[mergeSectionCounts][];
        for (int i = 0; i < mergeSectionCounts; i++) {
            int[] distAndIdx = PQ.poll();
            int dist = distAndIdx[0];
            int idx = distAndIdx[1];

            addedSections[i] = new int[]{sections[idx][1], sections[idx][1] + dist};
        }

        int ans = 0;

        for (int[] section : sections) {
            ans += section[1] - section[0];
        }
        for (int[] addedSection : addedSections) {
            ans += addedSection[1] - addedSection[0];
        }

        return ans;
    }

    private static int[][] getSection(int[] friends) {
        List<int[]> sections = new ArrayList<>();

        sections.add(new int[]{friends[0], friends[0] + 1});

        for (int i = 1; i < friends.length; i++) {
            int[] back = sections.get(sections.size() - 1);
            if (back[1] == friends[i]) {
                sections.set(sections.size() - 1, new int[]{back[0], friends[i] + 1});
            } else {
                sections.add(new int[]{friends[i], friends[i] + 1});
            }
        }

        return sections.toArray(int[][]::new);
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

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}