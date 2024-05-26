import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        double[][] points = new double[n][];
        for (int i = 0; i < n; i++) {
            points[i] = getDoubles();
        }

        double ans = solution(points);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static double solution(double[][] points) {
        Edge[] edges = getEdges(points);
        Arrays.sort(edges);
        int[] groups = IntStream.range(0, points.length).toArray();

        double ans = 0;

        for (Edge edge : edges) {
            int src = edge.src;
            int dest = edge.dest;
            double cost = edge.cost;

            int srcLeader = getLeader(groups, src);
            int destLeader = getLeader(groups, dest);

            if (srcLeader != destLeader) {
                union(groups, srcLeader, destLeader);
                ans += cost;
            }
        }

        return ans;
    }

    private static void union(int[] groups, int node1, int node2) {
        if (node1 < node2) {
            groups[node2] = node1;
            return;
        }
        groups[node1] = node2;
    }

    private static int getLeader(int[] groups, int node) {
        while (groups[node] != node) {
            node = groups[node];
        }
        return node;
    }

    private static Edge[] getEdges(double[][] points) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            double[] src = points[i];
            for (int j = i + 1; j < points.length; j++) {
                double[] dest = points[j];
                edges.add(new Edge(i, j, getDistance(src, dest)));
            }
        }

        return edges.toArray(Edge[]::new);
    }

    private static double getDistance(double[] src, double[] dest) {
        double x1 = src[0];
        double y1 = src[1];
        double x2 = dest[0];
        double y2 = dest[1];

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }


    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }

    public static double[] getDoubles() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        double[] doubles = new double[n];
        for (int i = 0; i < n; i++) {
            doubles[i] = Double.parseDouble(st.nextToken());
        }
        return doubles;
    }
}

class Edge implements  Comparable<Edge>{
    int src,dest;
    double cost;

    public Edge(int src, int dest, double cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(cost, o.cost);
    }
}