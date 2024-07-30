import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] square = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            String[] S = br.readLine().split("");
            for(int j = 1; j <= M; j++) {
                square[i][j] = Integer.parseInt(S[j-1]);
            }
        }
        int[][] dp = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(square[i][j]==1){
                    int temp = Math.min(dp[i-1][j],dp[i][j-1]);
                    dp[i][j] = Math.min(temp,dp[i-1][j-1])+1;
                }
            }
        }
        int max=0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(max<=dp[i][j]){
                    max=dp[i][j];
                }
            }
        }
        System.out.println(max*max);
    }
}
