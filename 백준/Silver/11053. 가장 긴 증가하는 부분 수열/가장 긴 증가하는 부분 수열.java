import java.io.*;
import java.util.*;
public class Main {

    static int n;
    static int max;
    static int[] arr;
    static int[] dp;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        max = 1;

        n = sc.nextInt();

        arr = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for( int j=0;j < i;j++) {
                if (arr[j] < arr[i] && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

}