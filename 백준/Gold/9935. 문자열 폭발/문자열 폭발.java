import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String bomb = br.readLine();
        int length = bomb.length();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));

            if (stack.size() >= length) {
                boolean flag = true;
                for (int j = 0; j < length; j++) {
                    if (stack.get(stack.size() - length + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < length; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        if (result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}