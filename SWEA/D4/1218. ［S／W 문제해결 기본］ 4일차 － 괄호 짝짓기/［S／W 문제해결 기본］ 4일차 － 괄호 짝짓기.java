import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            Stack<Character> stack = new Stack<>();
            int len = Integer.parseInt(br.readLine());
            String s = br.readLine();
            for(int i = 0; i < s.length(); i++){
                char x = s.charAt(i);
                if(x==')' && stack.peek() == '(') stack.pop();
                else if(x==']' && stack.peek() == '[') stack.pop();
                else if(x=='}' && stack.peek() == '{') stack.pop();
                else if(x=='>' && stack.peek() == '<') stack.pop();
                else{
                    stack.push(x);
                }
            }
            if(stack.isEmpty()){
                sb.append("#" + t + " " + 1+ "\n");
            }
            else{
                sb.append("#" + t + " " + 0 + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}