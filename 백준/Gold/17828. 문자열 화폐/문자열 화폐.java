import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static String exchange(int n, int x){
        int price = n * 26;
        if(price < x || n > x){
            return "!";
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0;i<n;i++){
            int overed = price - x;

            int letterPrice = Math.max(1, 26 - overed);

            price -= (26 - letterPrice);

            ans.append((char) ('A' - 1 + letterPrice));
        }
        return ans.toString();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String ans = Result.exchange(info[0], info[1]);


        bufferedWriter.write(
                ans
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}