import java.time.LocalDate;
import static java.time.format.TextStyle.*;
import static java.util.Locale.US;

class Solution {
    public String solution(int a, int b) {
        return LocalDate.of(2016, a, b).getDayOfWeek().getDisplayName(SHORT, US).toUpperCase();
    }
}