import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int k = info[1];
        long[] rectangle = getLongs();
        String[] cards = new String[n];

        for (int i = 0; i < n; i++) {
            cards[i] = br.readLine();
        }

        String[] ans = solution(rectangle, cards, k);

        for (String e : ans) {
            res.append(e).append("\n");
        }

        bw.write(res.toString());
        bw.close();
    }

    private static String[] solution(long[] rectangle, String[] cards, int k) {
        Map<Character, PriorityQueue<Long>> cardMap = new HashMap<>();
        cardMap.put('A', new PriorityQueue<>(Comparator.<Long, Long>comparing(Function.identity()).reversed()));
        cardMap.put('B', new PriorityQueue<>(Comparator.<Long, Long>comparing(Function.identity()).reversed()));
        cardMap.put('C', new PriorityQueue<>(Comparator.<Long, Long>comparing(Function.identity()).reversed()));
        cardMap.put('D', new PriorityQueue<>(Comparator.<Long, Long>comparing(Function.identity()).reversed()));

        for (String card : cards) {
            String[] components = card.split("\\s+");
            char kind = components[0].charAt(0);
            long len = Long.parseLong(components[1]);

            cardMap.get(kind).add(len);
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            char selected = '.';
            for (Map.Entry<Character, PriorityQueue<Long>> entry : cardMap.entrySet()) {
                if (entry.getValue().isEmpty()) {
                    continue;
                }
                Character key = entry.getKey();
                if (selected == '.') {
                    selected = key;
                    continue;
                }

                long selectDelta = cardMap.get(selected).peek() * rectangle[key - 'A'];
                long currentDelta = cardMap.get(key).peek() * rectangle[selected - 'A'];
                if (selectDelta < currentDelta) {
                    selected = key;
                }
            }
            long delta = cardMap.get(selected).poll();
            rectangle[selected - 'A'] += delta;
            ans.add(selected + " " + delta);
        }

        return ans.toArray(String[]::new);
    }

    private static int getSize(Integer delta, int[] rectangle, int idx) {
        int size = 1;
        for (int i = 0; i < rectangle.length; i++) {
            if (i == idx) {
                size *= rectangle[i] + delta;
            } else {
                size *= rectangle[i];
            }
        }
        return size;
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