import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[100];
        int[] P = new int[100];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[100];
        if(T[N]>1){
            dp[N] = 0;
        }
        for(int i = N; i>=1; i--){
            if(N-i+1<T[i]){
                dp[i] = dp[i+1];
            }
            else{
                dp[i]=Math.max(dp[i+T[i]]+P[i], dp[i+1]);
            }
        }
        System.out.println(dp[1]);
    }
}
