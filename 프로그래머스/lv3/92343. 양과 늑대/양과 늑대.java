import java.util.*;

class Solution {
    private final int SHEEP = 0;

    public int solution(int[] info, int[][] edges) {
        Map<Integer, Node> nodes = new HashMap<>();

        for (int i = 0; i < info.length; i++) {
            nodes.put(i, new Node(i, info[i]));
        }

        for (int[] edge : edges) {
            Node parent = nodes.get(edge[0]);
            Node child = nodes.get(edge[1]);

            if (parent.left == null) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        Node root = nodes.get(0);
        LinkedHashSet<Node> nexts = new LinkedHashSet<>();
        appendChildernToNextNodes(root, nexts);

        return getMaxSheepCount(root, nexts, 0, 0);
    }

    private int getMaxSheepCount(Node node, LinkedHashSet<Node> next, int sheepCnt, int wolfCnt) {
        if (node.TYPE == SHEEP) {
            sheepCnt++;
        } else {
            wolfCnt++;
        }
        if (sheepCnt == wolfCnt) {
            return 0;
        }

        int max = sheepCnt;
        for (Node nextNode : next) {
            LinkedHashSet<Node> copied = getNodes(next, nextNode);
            max = Math.max(max, getMaxSheepCount(nextNode, copied, sheepCnt, wolfCnt));
        }
        return max;
    }

    private LinkedHashSet<Node> getNodes(LinkedHashSet<Node> next, Node nextNode) {
        LinkedHashSet<Node> copied = new LinkedHashSet<>(next);
        copied.remove(nextNode);
        appendChildernToNextNodes(nextNode, copied);
        return copied;
    }

    private void appendChildernToNextNodes(Node parent, LinkedHashSet<Node> copied) {
        if (parent.left != null) {
            copied.add(parent.left);
        }
        if (parent.right != null) {
            copied.add(parent.right);
        }
    }

    class Node {
        final int NUMBER;
        final int TYPE;

        Node left;
        Node right;

        public Node(int NUMBER, int TYPE) {
            this.NUMBER = NUMBER;
            this.TYPE = TYPE;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}