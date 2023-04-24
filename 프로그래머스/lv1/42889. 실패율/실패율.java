import java.util.*;

class Solution {
    class FailRate implements Comparable<FailRate>{
        int stageNum;
        double failRate;

        public FailRate(int stageNum, double failRate) {
            this.stageNum = stageNum;
            this.failRate = failRate;
        }

        @Override
        public int compareTo(Solution.FailRate o) {
            return failRate < o.failRate ? 1 : failRate == o.failRate ? stageNum - o.stageNum : -1;
        }
    }
    public int[] solution(int N, int[] stages) {
        int[] clearCnt = new int[N + 1];
        int[] ingCnt = new int[N + 2];
        for (int currentStage : stages) {
            clearCnt[currentStage - 1]++;
            ingCnt[currentStage]++;
        }
        for (int i = N - 1; i > 0; i--) {
            clearCnt[i] += clearCnt[i + 1];
        }

        Set<FailRate> failRates = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            failRates.add(new FailRate(i, calcFailRate(clearCnt[i], ingCnt[i])));
        }
        int[] res = new int[N];
        Iterator<FailRate> iterator = failRates.iterator();
        for (int i = 0; i < N; i++) {
            res[i] = iterator.next().stageNum;
        }
        return res;
    }

    double calcFailRate(int cleared, int onGoing) {
        if(cleared + onGoing == 0) return 0;
        return 1 - ((double) cleared / (cleared + onGoing));
    }
}