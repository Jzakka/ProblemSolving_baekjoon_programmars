import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    // E   S  W
    public static void main(String[] args) {
        String inputs = sc.nextLine();

        boolean[] exists = new boolean[26];

        for (int i = 0; i < inputs.length(); i++) {
            char letter = inputs.charAt(i);

            exists[letter - 'A'] = true;
        }

        System.out.println(exists['M' - 'A'] && exists['O' - 'A'] && exists['B' - 'A'] && exists['I' - 'A'] && exists['S' - 'A'] ? "YES" : "NO");
    }
}