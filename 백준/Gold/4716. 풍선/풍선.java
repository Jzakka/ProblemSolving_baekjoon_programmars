import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    static class Team implements Comparable<Team> {
        private static final int A = 0;
        private static final int B = 1;
        int require;
        int[] dist = {0, 0};

        public Team(int require, int aDist, int bDist) {
            this.require = require;
            this.dist[0] = aDist;
            this.dist[1] = bDist;
        }

        public int nearestRoom() {
            return dist[0] > dist[1] ? B : A;
        }

        @Override
        public int compareTo(Team o) {
            int compareTo = Math.abs(o.dist[0] - o.dist[1]) - Math.abs(dist[0] - dist[1]);
            if (compareTo == 0) {
                return Math.max(o.dist[0], o.dist[1]) - Math.max(dist[0], dist[1]);
            }
            return compareTo;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] info;

        while ((info = getInts()) != null && !endOfFile(info)) {
            int n = info[0];
            int A = info[1];
            int B = info[2];

            Team[] teams = new Team[n];
            for (int i = 0; i < n; i++) {
                int[] teamInfo = getInts();
                teams[i] = new Team(teamInfo[0], teamInfo[1], teamInfo[2]);
            }

            solution(A,B,teams);
        }

        printRes();
    }

    private static boolean endOfFile(int[] info) {
        return info[0] == info[1] && info[1] == info[2] && info[0] == 0;
    }

    private static void solution(int aBalloons, int bBalloons, Team[] teams) {
        int[] balloons = {aBalloons, bBalloons};
        Arrays.sort(teams);

        int ans = 0;
        for (Team team : teams) {
            int nearestRoom = team.nearestRoom();

            if (balloons[nearestRoom] >= team.require) {
                ans += team.require * team.dist[nearestRoom];
                balloons[nearestRoom] -= team.require;
            } else {
                int otherRoom = 1 - nearestRoom;
                int deficiency = team.require - balloons[nearestRoom];

                ans += balloons[nearestRoom] * team.dist[nearestRoom];
                ans += deficiency * team.dist[otherRoom];

                balloons[nearestRoom] = 0;
                balloons[otherRoom] -= deficiency;
            }
        }

        res.append(ans).append("\n");
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
58
3
5
1
2
0

37
0
0
0
0
7

17
20
0
3
0
0

13
12
0
3
0
0

7
6
5
0
0
0

49
7
0
2
0
0

32
7
0
3
0
0
 */