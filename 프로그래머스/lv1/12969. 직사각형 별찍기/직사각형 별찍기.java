import java.util.Scanner;
import java.util.stream.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        IntStream.range(0,b).forEach(i-> {
            IntStream.range(0, a).forEach(j -> {
                System.out.print("*");
            });
            System.out.println();
        });
    }
}