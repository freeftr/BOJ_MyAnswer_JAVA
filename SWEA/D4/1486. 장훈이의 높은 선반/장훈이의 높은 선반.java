import java.util.*;
import java.io.*;

public class Solution {
    static int N, B;
    static int[] jeom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            jeom = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                jeom[i] = Integer.parseInt(st.nextToken());
            }

            int maxSum = Arrays.stream(jeom).sum(); 
            boolean[] dp = new boolean[maxSum + 1];
            dp[0] = true;  

            for (int i = 0; i < N; i++) {
                for (int j = maxSum; j >= jeom[i]; j--) {
                    if (dp[j - jeom[i]]) {
                        dp[j] = true;
                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int i = B; i <= maxSum; i++) {
                if (dp[i]) {
                    result = i - B;
                    break;
                }
            }

            System.out.println("#" + t + " " + result);
        }
    }
}