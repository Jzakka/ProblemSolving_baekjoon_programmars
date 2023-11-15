import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Math.min;

class Main {

    static final int INFINITY = Integer.MAX_VALUE / 10;
    static int[][] graphMatrix;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        graphMatrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                graphMatrix[i][j] = (i == j) ? 0 : INFINITY;
            }
        }

        for (int i = 0; i < e; i++) {
            int u_, v_, w;
            u_ = sc.nextInt()-1;
            v_ = sc.nextInt()-1;
            w = sc.nextInt();

            if(graphMatrix[u_][v_] > w) {
                graphMatrix[u_][v_] = w;
                graphMatrix[v_][u_] = w;
            }

        }

        int v1 = sc.nextInt()-1;
        int v2 = sc.nextInt()-1;

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i != j && graphMatrix[j][i] != INFINITY) {
                    int value = graphMatrix[j][i];
                    for (int k = 0; k < v; k++) {
                        graphMatrix[j][k] = min(graphMatrix[j][k],graphMatrix[i][k] + value);
                    }
                }
            }
        }

        /*for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                try {
                    System.out.print(graphMatrix[i][j] + " ");
                } catch (Exception exception) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }*/

        int shortest =
                min(graphMatrix[0][v1] + graphMatrix[v1][v2] + graphMatrix[v2][v-1]
                ,graphMatrix[0][v2] + graphMatrix[v2][v1] + graphMatrix[v1][v-1]);
        System.out.printf("%d", (shortest>=INFINITY)?-1:shortest);
    }
}

