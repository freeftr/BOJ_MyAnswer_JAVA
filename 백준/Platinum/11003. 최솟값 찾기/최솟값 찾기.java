import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        StringBuilder ans = new StringBuilder();
        Deque<int[]> dq = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int  i = 0; i < N; i++)
        {
            int K = Integer.parseInt(st.nextToken());
            while(!dq.isEmpty() && dq.peekLast()[1] > K){
                dq.removeLast();
            }
            dq.add(new int[]{i, K});
            if(i - dq.peekFirst()[0] >= L){
                dq.removeFirst();
            }
            ans.append(dq.peekFirst()[1] + " ");
        }
        bw.write(ans.toString());
        bw.close();
    }
}

