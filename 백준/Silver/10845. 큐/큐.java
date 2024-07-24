import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        LinkedList<Integer> q = new LinkedList<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if(s.contains("push")){
                q.addLast(Integer.parseInt(s.split(" ")[1]));
            }
            if(s.equals("pop")){
                if(q.isEmpty()){
                    bw.write("-1\n");
                }
                else{
                    bw.write(q.pollFirst()+"\n");
                }
            }
            if(s.equals("size")){
                bw.write(q.size()+"\n");
            }
            if(s.equals("empty")){
                if(q.isEmpty()) {
                    bw.write("1\n");
                }
                else{
                    bw.write("0\n");
                }
            }
            if(s.equals("front")){
                if(q.isEmpty()){
                    bw.write("-1\n");
                }
                else{
                    bw.write(q.peekFirst()+"\n");
                }
            }
            if(s.equals("back")){
                if(q.isEmpty()){
                    bw.write("-1\n");
                }
                else{
                    bw.write(q.peekLast()+"\n");
                }
            }
        }
        bw.close();
    }
}
