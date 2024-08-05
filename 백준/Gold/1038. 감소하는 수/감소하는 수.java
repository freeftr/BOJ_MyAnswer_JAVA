import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static long cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N==0){
            System.out.println(0);
            System.exit(0);
        }
        System.out.println(bfs());
    }
    public static long bfs(){
        Queue<Long> q = new LinkedList<>();
        for(long i = 1; i<=9; i++){
            q.add(i);
        }
        while(!q.isEmpty()){
            cnt++;
            long v = q.poll();
            if(cnt==N){
                return v;
            }
            for(long i = 0; i < v%10; i++){
                q.add(v*10+i);
            }
        }
        return -1;
    }
}
