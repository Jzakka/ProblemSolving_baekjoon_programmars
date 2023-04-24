import java.util.ArrayList;

class Solution {
    public int solution(String dartResult) {
        ArrayList<Integer> scores = parseRes(dartResult);
        return scores.stream().reduce(Integer::sum).get();
    }

    private ArrayList<Integer> parseRes(String dartResult) {
        ArrayList<Integer> scores = new ArrayList<>();

        int num = 0;
        int lastIdx = -1;
        int i = 0;
        while (i < dartResult.length()) {
            char letter = dartResult.charAt(i);
            if (Character.isDigit(letter)) {
                int[] numAndIdx = parseNum(dartResult, i);
                num = numAndIdx[0];
                i = numAndIdx[1];
                scores.add(num);
                lastIdx++;
            } else {
                int lastScore = scores.get(lastIdx);
                if (letter == 'D') {
                    scores.set(lastIdx, lastScore * lastScore);
                } else if (letter == 'T') {
                    scores.set(lastIdx, lastScore * lastScore * lastScore);
                } else if (letter == '*') {
                    if (lastIdx - 1 >= 0) {
                        int prevScore = scores.get(lastIdx - 1);
                        scores.set(lastIdx-1, prevScore*2);
                    }
                    scores.set(lastIdx, lastScore*2);
                } else if (letter == '#') {
                    scores.set(lastIdx, lastScore*-1);
                }
                i++;
            }
        }

        return scores;
    }

    private int[] parseNum(String dartResult, int i) {
        int[] numAndIdx = new int[2];

        if (dartResult.charAt(i) != '1') {
            numAndIdx[0] = dartResult.charAt(i) - '0';
            numAndIdx[1] = i + 1;
        } else {
            if (++i >= dartResult.length()) {
                return new int[]{1, i};
            }

            char letter = dartResult.charAt(i);
            if (letter == '0') {
                numAndIdx[0] = 10;
                numAndIdx[1] = i+1;
            } else {
                numAndIdx[0] = 1;
                numAndIdx[1] = i;
            }

        }
        return numAndIdx;
    }
}