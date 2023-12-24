

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static Map<Character, Integer> digits = new HashMap<>();
    private static Map<Integer, Character> decimalMap = new HashMap<>();
    private static BigInteger BIG_36 = new BigInteger("36");

    public static void main(String[] args) throws Exception {
        //digits 초기화
        for (char i = '0'; i <= '9'; i++) {
            digits.put(i, i - '0');
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            digits.put(i, i - 'A' + 10);
        }

        //decimalMap 초기화
        for (int i = 0; i < 10; i++) {
            decimalMap.put(i, (char) ('0' + i));
        }
        for (int i = 10; i < 36; i++) {
            decimalMap.put(i, (char) ('A' + i - 10));
        }

        int n = Integer.parseInt(br.readLine());
        String[] numbers = new String[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = br.readLine();
        }
        int k = Integer.parseInt(br.readLine());

        solution(numbers, k);

        printRes();
    }

    private static void solution(String[] numbers, int k) {
        Character[] topKLetters = topKLetters(numbers, k);

        for (char topKLetter : topKLetters) {
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = numbers[i].replace(topKLetter, 'Z');
            }
        }

        String sum = "0";
        for (String number : numbers) {
            sum = add(sum, number);
        }

        res.append(sum);
    }

    private static String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        boolean carry = false;

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 && j >= 0) {
            Integer val1 = digits.get(num1.charAt(i));
            Integer val2 = digits.get(num2.charAt(j));

            int twoSum = val1 + val2 + (carry ? 1 : 0);
            carry = twoSum >= 36;
            if (carry) {
                twoSum -= 36;
            }
            sb.append(decimalMap.get(twoSum));
            i--;
            j--;
        }
        while (i >= 0) {
            Integer val = digits.get(num1.charAt(i)) + (carry ? 1 : 0);
            carry = val >= 36;
            if (carry) {
                val -= 36;
            }
            sb.append(decimalMap.get(val));
            i--;
        }
        while (j >= 0) {
            Integer val = digits.get(num2.charAt(j)) + (carry ? 1 : 0);
            carry = val >= 36;
            if (carry) {
                val -= 36;
            }
            sb.append(decimalMap.get(val));
            j--;
        }
        if (carry) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }


    static class Weight implements Comparable<Weight> {
        char letter;
        BigInteger weight;

        public Weight(char letter, BigInteger weight) {
            this.letter = letter;
            this.weight = weight;
        }

        @Override
        public int compareTo(Weight o) {
            if (this.letter == 'P' || o.letter == 'P') {
                int a = 1;
            }
            BigInteger thisDiff = new BigInteger(String.valueOf((int) ('Z' - this.letter - (Character.isDigit(this.letter) ? ('A' - '9' - 1) : 0))));
            BigInteger oDiff = new BigInteger(String.valueOf((int) ('Z' - o.letter - (Character.isDigit(o.letter) ? ('A' - '9' - 1) : 0))));

            return o.weight.multiply(oDiff).compareTo( this.weight.multiply(thisDiff));
        }
    }

    /*
    Z를 제외하고 가중치가 가장 큰 k개의 문자 선택
     */
    private static Character[] topKLetters(String[] numbers, int k) {
        Map<Character, Weight> weights = new HashMap<>();
        for (char i = '0'; i <= '9'; i++) {
            weights.put(i, new Weight(i, new BigInteger("0")));
        }
        for (char i = 'A'; i < 'Z'; i++) { // Z를 제외하고 k개
            weights.put(i, new Weight(i, new BigInteger("0")));
        }

        for (String number : numbers) {
            for (int i = 0; i < number.length(); i++) {
                char letter = number.charAt(i);
                if (letter == 'Z') {
                    continue;
                }
                weights.get(letter).weight =
                        weights.get(letter).weight.add(BIG_36.pow(number.length() - i - 1));
            }
        }

        return weights.values().stream()
                .sorted()
                .limit(k)
                .map(w -> w.letter)
                .toArray(Character[]::new);
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