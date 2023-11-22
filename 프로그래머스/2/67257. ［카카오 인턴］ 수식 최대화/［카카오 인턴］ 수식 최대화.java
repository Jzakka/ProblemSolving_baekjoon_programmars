import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    class Node{
        Node next;
        Node prev;

        public Node() {
        }

        public Node(Node prev, Node next) {
            this.next = next;
            this.prev = prev;
            if (this.next != null) {
                this.next.prev = this;
            }
            if (this.prev != null) {
                this.prev.next = this;
            }
        }
    }
    class  Num extends Node{
        long val;

        public Num(long val) {
            this.val = val;
        }

        public Num(Node prev, Node next, long val) {
            super(prev, next);
            this.val = val;
        }
    }

    class Op extends Node{
        char op;

        public Op(char op) {
            this.op = op;
        }

        public Op(Node prev, Node next, char op) {
            super(prev, next);
            this.op = op;
        }

        Node operate(){
            Num prevNum = (Num) this.prev;
            Num nextNum = (Num) this.next;

            Num calced = new Num(this.prev.prev, this.next.next, operate(prevNum.val, nextNum.val));
            return calced;
        }

        private long operate(long prevNum,  long nextNum) {
            if (op == '-') {
                return prevNum - nextNum;
            }
            if (op == '+') {
                return prevNum + nextNum;
            }

            return prevNum * nextNum;
        }
    }
    public long solution(String expression) {
        Node head1 = parse(expression);
        Node head2 = parse(expression);
        Node head3 = parse(expression);
        Node head4 = parse(expression);
        Node head5 = parse(expression);
        Node head6 = parse(expression);

        long res1 = eval(head1, '*', '+', '-');
        long res2 = eval(head2, '*', '-', '+');
        long res3 = eval(head3, '+', '*', '-');
        long res4 = eval(head4,'+', '-', '*');
        long res5 = eval(head5, '-', '*', '+');
        long res6 = eval(head6, '-', '+', '*');

        return Math.max(res1, Math.max(res2, Math.max(res3, Math.max(res4, Math.max(res5, res6)))));
    }

    private Node parse(String expression) {
        int cursor = 0;
        long num = 0;
        Node cur = new Node();
        Node head = cur;

        while (cursor < expression.length()) {
            if (Character.isDigit(expression.charAt(cursor))) {
                num = num * 10 + expression.charAt(cursor) - '0';
            } else {
                Op op = new Op(expression.charAt(cursor));
                Num prevNum = new Num(cur, op, num);
                cur = op;
                num = 0;
            }
            cursor++;
        }
        Num lastNum = new Num(cur, null, num);
        return head;
    }

    long eval(Node head, char op1, char op2, char op3) {
        Node first = operate(head, op1);
        Node second = operate(first, op2);
        Node result = operate(second, op3);
        return Math.abs(((Num) result.next).val);
    }

    private Node operate(Node head, char op) {
        Node cursor = head.next;
        while (cursor != null) {
            if (cursor instanceof Op) {
                Op operation = (Op) cursor;
                if (operation.op == op) {
                    cursor = operation.operate();
                }
            }
            cursor = cursor.next;
        }
        return head;
    }
}