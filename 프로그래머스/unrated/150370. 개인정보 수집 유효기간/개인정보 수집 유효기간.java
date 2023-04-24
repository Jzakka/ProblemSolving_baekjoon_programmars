import java.util.*;

class Solution {
    Map<Character, Integer> termGuide = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        for (String term : terms) {
            termGuide.put(term.charAt(0), Integer.parseInt(term.substring(2)));
        }

        ArrayList<Integer> expiredTerms = new ArrayList<>();
        int i = 1;
        for (String privacy : privacies) {
            String[] p = privacy.split(" ");
            int[] contractDate = getFullDate(p[0]);
            char term = p[1].charAt(0);

            int[] expireDate = minusADay(plusMonth(contractDate, termGuide.get(term)));
            if (expired(getFullDate(today), expireDate)) {
                expiredTerms.add(i);
            }
            i++;
        }

        return expiredTerms.stream().mapToInt(Integer::valueOf).toArray();
    }

    public boolean expired(int[] today, int[] expireDate) {
        if(today[0] > expireDate[0]) return true;
        else if (today[0] == expireDate[0] && today[1] > expireDate[1]) {
            return true;
        } else if (today[0] == expireDate[0] &&
                today[1] == expireDate[1] && today[2] > expireDate[2]) {
            return true;
        }
        return false;
    }

    public int[] getFullDate(String date) {
        return Arrays
                .stream(date.split("\\."))
                .mapToInt(Integer::valueOf)
                .toArray();
    }

    public int[] plusMonth(int[] date, int leapedMonth) {
        int year = date[0];
        int month = date[1];
        int day = date[2];

        month += leapedMonth;
        int y = (month - 1) / 12;
        int m = (month - 1) % 12 + 1;

        year += y;
        month = m;

        return new int[]{year, month, day};
    }

    public int[] minusADay(int[] date) {
        int year = date[0];
        int month = date[1];
        int day = date[2];

        day--;
        if (day == 0) {
            month--;
            day = 28;
            if (month == 0) {
                year--;
                month = 12;
            }
        }

        return new int[]{year, month, day};
    }
}