import java.io.*;
import java.util.*;
public class Main {
    static int N ;
    static long[][] food;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        food = new long[N+1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long s = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            food[i][0] = s;
            food[i][1] = b;
        }
        if(N==1){
            System.out.println(Math.abs(food[1][0]-food[1][1]));
            System.exit(0);
        }
        ans=Long.MAX_VALUE;
        dfs(1,0, -1,-1);
        dfs(1,1,-1,-1);
        System.out.println(ans);
    }
    public static void dfs(int idx, int check, long sumS, long sumP){
        if(idx>N){
            if(sumS==-1&&sumP==-1){
                sumS=-1;
                sumP=-1;
                return;
            }
            ans=Math.min(ans,Math.abs(sumS-sumP));
            sumS=-1;
            sumP=-1;
            return;
        }
        if(sumS==-1 && sumP==-1 && check==1){
            sumS=food[idx][0];
            sumP=food[idx][1];
        }
        else if(check==1){
            sumS*=food[idx][0];
            sumP+=food[idx][1];
        }
        dfs(idx+1,0, sumS, sumP);
        dfs(idx+1,1, sumS, sumP);
    }
}
