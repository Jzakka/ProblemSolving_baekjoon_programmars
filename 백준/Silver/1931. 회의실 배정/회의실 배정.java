import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] meetings = new int[n][];

        for (int i = 0; i < n; i++) {
            meetings[i] = getInts();
        }

        solution(meetings);

        printRes();
    }

    static class Meeting {
        int startTime;
        int endTime;
        int meetingNum;

        public Meeting(int startTime, int endTime, int meetingNum) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.meetingNum = meetingNum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meeting meeting = (Meeting) o;
            return startTime == meeting.startTime && endTime == meeting.endTime && meetingNum == meeting.meetingNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime, meetingNum);
        }
    }

    private static void solution(int[][] meetings) {
        Arrays.sort(meetings, (m1,m2)->{
            if (m1[0] == m2[0]) {
                return m1[1] - m2[1];
            }
            return m1[0] - m2[0];
        });
        PriorityQueue<Meeting> PQ = new PriorityQueue<>(Comparator.comparingInt(meeting -> meeting.endTime));
        TreeSet<Meeting> meetingSet = new TreeSet<>((m1, m2) -> {
            if (m1.meetingNum == m2.meetingNum) {
                if (m1.endTime == m2.endTime) {
                    if (m1.startTime == m2.startTime) {
                        return -1;
                    }
                    return m1.startTime - m2.startTime;
                }
                return m1.endTime - m2.endTime;
            }
            return m2.meetingNum - m1.meetingNum;
        });
        // meetingNum[i] : i번째 미팅은 안겹치고 몇번째 미팅?
        int[] meetingNum = new int[meetings.length];
        int maxUse = 0;
        for (int i = 0; i < meetings.length; i++) {
            int[] meeting = meetings[i];

            if (PQ.isEmpty()) {
                Meeting m = new Meeting(meeting[0], meeting[1], 1);
                PQ.add(m);
                meetingSet.add(m);
                meetingNum[i] = 1;
            } else {
                while (!PQ.isEmpty() && PQ.peek().endTime <= meeting[0]) {
                    Meeting nonOverlap = PQ.poll();
                    meetingNum[i] = Math.max(nonOverlap.meetingNum + 1, meetingNum[i]);
                    meetingSet.remove(nonOverlap);
                }

                if (!PQ.isEmpty()) {
                    meetingNum[i] = Math.max(meetingNum[i], meetingSet.first().meetingNum);
                }

                Meeting newM = new Meeting(meeting[0], meeting[1], meetingNum[i]);
                PQ.add(newM);
                meetingSet.add(newM);
            }
            maxUse = Math.max(maxUse, meetingNum[i]);
        }

        res.append(maxUse);
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