import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        Deque<Integer> dq = new ArrayDeque<>();
        int N = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++) {
            String s = br.readLine();
            if(s.contains("push_back")) {
                int x = Integer.parseInt(s.split(" ")[1]);
                dq.addLast(x);
            }
            if(s.contains("push_front")) {
                int x = Integer.parseInt(s.split(" ")[1]);
                dq.addFirst(x);
            }
            if(s.equals("front")){
                if(dq.isEmpty()){
                    ans.append("-1\n");
                }
                else{
                    ans.append(dq.peekFirst()+"\n");
                }
            }
            if(s.equals("back")){
                if(dq.isEmpty()){
                    ans.append("-1\n");
                }
                else{
                    ans.append(dq.peekLast()+"\n");
                }
            }
            if(s.equals("empty")){
                if(dq.isEmpty()){
                    ans.append("1\n");
                }
                else{
                    ans.append("0\n");
                }
            }
            if(s.equals("size")){
                ans.append(dq.size()+"\n");
            }
            if(s.equals("pop_front")){
                if(dq.isEmpty()){
                    ans.append("-1\n");
                }
                else{
                    ans.append(dq.pollFirst()+"\n");
                }
            }
            if(s.equals("pop_back")){
                if(dq.isEmpty()){
                    ans.append("-1\n");
                }
                else{
                    ans.append(dq.pollLast() + "\n");
                }
            }
        }
        bw.write(ans.toString());
        bw.close();
    }
}
