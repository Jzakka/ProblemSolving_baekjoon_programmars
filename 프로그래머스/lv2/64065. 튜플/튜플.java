import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[][] sets = parse(s);

        ArrayList<Integer> tuple = new ArrayList<>();
        Arrays.sort(sets, Comparator.comparingInt((int[] a) -> a.length));

        for (int[] set : sets) {
            tuple.add(Arrays.stream(set).filter(num -> !tuple.contains(num)).findFirst().orElse(0));
        }

        return tuple.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[][] parse(String s) {
        String[] innerArrays = s.substring(2, s.length() - 2).split("\\},\\{");

        return Arrays.stream(innerArrays)
                .map(extracted -> extracted.split(","))
                .map(strArr -> Arrays.stream(strArr)
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .toArray(int[][]::new);
    }
}