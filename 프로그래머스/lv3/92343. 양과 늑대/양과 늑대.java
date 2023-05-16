import java.util.*;

class Solution {
    private final int SHEEP = 0;
    private final int WOLF = 1;

    private Set<Node> available = new HashSet<>();

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
        if (root.left != null) {
            nexts.add(root.left);
        }
        if (root.right != null) {
            nexts.add(root.right);
        }

        return search(root, nexts, 0, 0);
    }

    private int search(Node node,LinkedHashSet<Node> next, int sheepCnt, int wolfCnt) {
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
            LinkedHashSet<Node> copied = new LinkedHashSet<>(next);
            copied.remove(nextNode);
            if (nextNode.left != null) {
                copied.add(nextNode.left);
            }
            if (nextNode.right != null) {
                copied.add(nextNode.right);
            }
            max = Math.max(max, search(nextNode, copied, sheepCnt, wolfCnt));
        }
        return max;
    }

    class Node {
        final int NUMBER;
        final int TYPE;

        Node left;
        Node right;
        Node parent;

        public Node(int NUMBER, int TYPE) {
            this.NUMBER = NUMBER;
            this.TYPE = TYPE;
        }

        public void setLeft(Node left) {
            this.left = left;
            left.setParent(this);
        }

        public void setRight(Node right) {
            this.right = right;
            right.setParent(this);
        }

        private void setParent(Node parent) {
            this.parent = parent;
        }
    }
}