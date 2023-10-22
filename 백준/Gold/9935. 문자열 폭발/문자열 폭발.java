import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String text, subString;

        text = bf.readLine();
        subString = bf.readLine();
        Stack<Character> stack = new Stack<>();
        int n = text.length();
        int m = subString.length();
        char s = subString.charAt(m - 1);

        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            stack.push(c);
            if(c == s){
                boolean isSubString = true;
                try{
                    int t = stack.size();
                    for (int z = m - 1, x = t-1; z >= 0; z--, x--) {
                        if (stack.get(x) != subString.charAt(z)) {
                            isSubString = !isSubString;
                            break;
                        }
                    }
                    if(isSubString){
                        for (int z = 0; z < m; z++) {
                            stack.pop();
                        }
                    }
                }catch (Exception e){}

            }
        }

        if(stack.isEmpty())
            System.out.println("FRULA");
        else{
            StringBuilder sb = new StringBuilder();
            for (Character character : stack) {
                sb.append(character);
            }
            System.out.println(sb);
        }
    }
}

