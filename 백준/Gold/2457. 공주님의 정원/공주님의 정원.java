import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int YEAR = 2023;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        LocalDate[][] input = new LocalDate[n][2];

        for (int i = 0; i < n; i++) {
            int[] info = getInts();
            input[i][0] = LocalDate.of(YEAR, info[0], info[1]);
            input[i][1] = LocalDate.of(YEAR, info[2], info[3]).minusDays(1);
        }

        solution(input);

        printRes();
    }

    public static void solution(LocalDate[][] dates) {
        int n = dates.length;

        Arrays.sort(dates, (date1, date2) -> {
            if (date1[0].isEqual(date2[0])) {
                return date1[1].compareTo(date2[1]);
            }
            return date1[0].compareTo(date2[0]);
        });

        int cnt = 0;
        LocalDate START_DATE = LocalDate.of(YEAR, 3, 1);
        LocalDate END_DATE = LocalDate.of(YEAR, 11, 30);
        LocalDate criteria = START_DATE; // 이 날짜부터 꽃이 심어지지 않았다

        int i = 0;
        for (; i < dates.length;) {
            LocalDate nextCriteria = criteria;

            boolean criteriaUpdate = false;
            for (; i < dates.length && !dates[i][0].isAfter(criteria); i++) {
                LocalDate end = dates[i][1];

                nextCriteria = max(nextCriteria, end);
                if (nextCriteria.isEqual(end)) {
                    criteriaUpdate = true;
                }
            }

            if (criteriaUpdate) { // criteria 이전의 꽃들은 모두 현재 criteria을 넘지 못했다. 즉, 빵구가 필연적이다.
                cnt++;
            } else {
                res.append(0);
                return;
            }

            criteria = nextCriteria.plusDays(1);
            if (criteria.isAfter(END_DATE)) {
                break;
            }
        }

        if (!criteria.isAfter(END_DATE)) {
            res.append(0);
            return;
        }
        res.append(cnt);
    }

    private static LocalDate max(LocalDate a, LocalDate b) {
        if (a.isAfter(b)) {
            return a;
        }
        return b;
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
5
3 1 4 30
4 1 10 5
4 30 10 30
10 30 10 30
10 31 12 5
 */