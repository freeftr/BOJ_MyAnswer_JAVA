import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N+2];

        dp[1] = 0;
        dp[2] = 1;
        if (N==1){
            System.out.println(0);
            System.exit(0);
        }
        else if (N==2){
            System.out.println(1);
            System.exit(0);
        }

        for(int i = 3; i <= N; i++){
            dp[i] = (i-1)*((dp[i-1]+dp[i-2]))%1000000000;
        }
        System.out.println(dp[N]);
    }
}