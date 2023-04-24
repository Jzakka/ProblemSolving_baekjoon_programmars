class Solution {
    private Node root = new Node();
    public boolean solution(String[] phone_book) {
        for (String phoneNum : phone_book) {
            if (!search(phoneNum)) {
                return false;
            }
        }
        return true;
    }

    private boolean search(String phoneNum) {
        Node cur = root;

        for (int i = 0; i < phoneNum.length(); i++) {
            char digit = phoneNum.charAt(i);

            if (cur.next[digit - '0'] == null) {
                cur.next[digit - '0'] = new Node();
            }
            cur = cur.next[digit - '0'];
            if (cur.endOfWord) {
                return false;
            }
        }

        for (Node node : cur.next) {
            if (node != null) {
                return false;
            }
        }

        cur.endOfWord = true;
        return true;
    }

    class Node {
        boolean endOfWord;
        Node[] next = new Node[10];
    }
}