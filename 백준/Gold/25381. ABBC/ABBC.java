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

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static int ABBC(String s){
        Map<Character, Queue<Integer>> position = Map.of(
                'A', new LinkedList<>(),
                'B', new LinkedList<>(),
                'C', new LinkedList<>()
        );
        for(int i=0;i<s.length();i++){
            position.get(s.charAt(i)).add(i);
        }

        int ans = 0;
        for(int BPos:position.get('B')){
            Queue<Integer> CPos = position.get('C');
            Queue<Integer> APos = position.get('A');

            if (hasCMatch(CPos, BPos)) {
                ans++;
            } else if(hasAMatch(APos, BPos)) {
                ans++;
            }
        }

        return ans;
    }

    private static boolean hasAMatch(Queue<Integer> positions, int BPos) {
        if (!positions.isEmpty() && positions.peek() < BPos) {
            positions.poll();
            return true;
        }
        return false;
    }

    public static boolean hasCMatch(Queue<Integer> positions, int BPos){
        while (!positions.isEmpty() && positions.peek() < BPos) {
            positions.poll();
        }
        if (!positions.isEmpty()) {
            positions.poll();
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();
        int result = Result.ABBC(s);

        bufferedWriter.write(
                String.valueOf(result)
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}