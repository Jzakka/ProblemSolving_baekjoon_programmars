import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        String[] numbers = new String[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = br.readLine();
        }

        solution(numbers);

        printRes();
    }

    private static void solution(String[] numbers) {
        long[] score = new long[10];

        for (String number : numbers) {
            for (int i = 0; i < number.length(); i++) {
                int idx = number.charAt(i) - 'A';
                score[idx] += Math.pow(10, number.length() - i - 1);
            }
        }

        Character[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        Arrays.sort(letters, Comparator.comparingLong(c -> score[c - 'A']));

        // 이니셜은 0이 안되게
        Set<Character> initials = Arrays.stream(numbers).map(num -> num.charAt(0)).collect(Collectors.toSet());
        int replaceIdx = 1;
        while (initials.contains(letters[0])) {
            Character replaceLetter = letters[replaceIdx];
            letters[replaceIdx] = letters[0];
            letters[0] = replaceLetter;
            replaceIdx++;
        }

        Map<Character, Integer> digits = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            digits.put(letters[i], i);
        }

        long ans = 0;
        for (String number : numbers) {
            long num = 0;
            for (int i = 0; i < number.length(); i++) {
                num *= 10;
                num += digits.get(number.charAt(i));
            }
            ans += num;
        }

        res.append(ans);
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
50
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
ABCDEFGHIJ
 */