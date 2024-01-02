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
        int[][] meats = new int[info[0]][];

        for (int i = 0; i < info[0]; i++) {
            meats[i] = getInts();
        }

        solution(info[0], info[1], meats);

        printRes();
    }

    private static void solution(int n, int m, int[][] meats) {
        TreeMap<Integer, List<Integer>> meatGroups = new TreeMap<>();

        for (int[] meat : meats) {
            int weight = meat[0];
            int price = meat[1];

            if (meatGroups.containsKey(price)) {
                meatGroups.get(price).add(weight);
            } else {
                meatGroups.put(price, new ArrayList<>(Arrays.asList(weight)));
            }
        }
        meatGroups.forEach((e, v) -> Collections.sort(v, Comparator.comparingInt(w -> -w)));

        int currentWeight = 0;
        int currentPrice = 0;

        for (Map.Entry<Integer, List<Integer>> entry : meatGroups.entrySet()) {
            Integer price = entry.getKey();
            List<Integer> meatGroup = entry.getValue();

            int groupWeight = 0;
            int groupPrice = 0;
            for (Integer weight : meatGroup) {
                groupWeight += weight;
                groupPrice += price;
                if (currentWeight + groupWeight >= m) {
                    Integer nextPrice = meatGroups.higherKey(price);

                    if (nextPrice != null) {
                        if (nextPrice >= groupPrice) {
                            res.append(groupPrice);
                        } else {
                            res.append(nextPrice);
                        }
                    } else {
                        res.append(groupPrice);
                    }
                    return;
                }
            }

            currentWeight += groupWeight;
            currentPrice = price;
        }

        if (currentWeight < m) {
            res.append(-1);
        } else {
            res.append(currentPrice);
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

/*
8 31
7 2
5 2
4 3
9 4
3 5
2 5
1 5
6 12

2 15
16 0
15 1

3 1
0 1
0 2
0 3
 */