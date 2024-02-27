import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private final static int END = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        List<Integer> preOrder = new ArrayList<>();
        String input;
        while ((input = br.readLine()) != null && !input.equals("")) {
            preOrder.add(Integer.parseInt(input));
        }

        solution(preOrder);

        printRes();
    }

    static class Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    private static void solution(List<Integer> preOrder) {
        Node root = new Node(preOrder.get(0));

        preOrderTraversal(root, preOrder, new IntRef(1), -9999999, 9999999);

        postOrderTraversal(root);
    }

    private static void postOrderTraversal(Node node) {
        if (node.left != null) {
            postOrderTraversal(node.left);
        }
        if (node.right != null) {
            postOrderTraversal(node.right);
        }
        res.append(node.val).append("\n");
    }

    static class IntRef{
        int val;

        public IntRef(int val) {
            this.val = val;
        }

        int getAndAdd() {
            return val++;
        }
    }

    private static int preOrderTraversal(Node parent, List<Integer> preOrder, IntRef idx, final int min, final int max) {
        if (preOrder.size() == idx.val) {
            return END;
        }

        Integer val = preOrder.get(idx.getAndAdd());

        if (val < parent.val && min < val) {
            Node left = new Node(val);
            parent.left = left;
            val = preOrderTraversal(left, preOrder, idx, min, parent.val);
        }

        if (val > parent.val && val < max) {
            Node right = new Node(val);
            parent.right = right;
            val = preOrderTraversal(right, preOrder, idx, parent.val, max);
        }

        return val;
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