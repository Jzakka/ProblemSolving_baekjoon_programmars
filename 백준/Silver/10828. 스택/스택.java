import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    static class Stack{
        int[] container = new int[10_001];
        int t = -1;

        void push(int num){
            container[++t] = num;
        }

        int pop(){
            if (t == -1) {
                return -1;
            }
            return container[t--];
        }

        int size(){
            return t + 1;
        }

        boolean empty() {
            return size() == 0;
        }

        int top() {
            if (t == -1) {
                return -1;
            }
            return container[t];
        }
    }
    public static void main(String[] args) throws Exception {
        Stack stk = new Stack();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] commands = br.readLine().split("\\s+");

            String command = commands[0];
            if (command.equals("push")) {
                stk.push(Integer.parseInt(commands[1]));
            } else if (command.equals("top")) {
                res.append(stk.top()).append("\n");
            } else if (command.equals("size")) {
                res.append(stk.size()).append("\n");
            } else if (command.equals("pop")) { // pop
                res.append(stk.pop()).append("\n");
            } else { //empty
                res.append(stk.empty() ? 1 : 0).append("\n");
            }
        }

        printRes();
    }



    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}