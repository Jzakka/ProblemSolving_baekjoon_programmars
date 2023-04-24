import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    Queue<Integer> mainBelt;
    Stack<Integer> subBelt = new Stack<>();
    final byte MAIN = 0;
    final byte SUB = 1;
    final byte OUT = 2;
    byte[] contentWhere;
    public int solution(int[] order) {
        mainBelt = new LinkedList<>(IntStream.range(1, order.length + 1).boxed().collect(Collectors.toList()));
        contentWhere = new byte[order.length + 1];
        int contentIdx = 0;
        while (contentIdx < order.length && pickable(order[contentIdx])){
            move(order[contentIdx]);
            pick(order[contentIdx]);
            contentIdx++;
        }
        return contentIdx;
    }

    private void pick(int content) {
        if (!mainBelt.isEmpty() && mainBelt.peek() == content) {
            mainBelt.poll();
        } else {
            subBelt.pop();
        }
        contentWhere[content] = OUT;
    }

    public boolean pickable(int content) {
        return contentWhere[content]==MAIN || (!subBelt.empty() && subBelt.peek() == content);
    }

    public void move(int content) {
        if (contentWhere[content]==MAIN) {
            while (!mainBelt.isEmpty() && mainBelt.peek() != content) {
                int moved = mainBelt.poll();
                subBelt.push(moved);
                contentWhere[moved] = SUB;
            }
        }
    }
}