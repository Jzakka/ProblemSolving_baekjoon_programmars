import java.util.stream.*;

class Solution {
    public String solution(String phone_number) {
        StringBuilder sb = new StringBuilder(phone_number);

        IntStream.range(0, phone_number.length()).forEach(i->{
            if (i + 4 < phone_number.length()) sb.replace(i, i + 1, "*");
        });

        return sb.toString();
    }
}