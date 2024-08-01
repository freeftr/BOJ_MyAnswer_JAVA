import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        int max = 0;
        for(int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            max=Math.min(max, arr[i]);
        }
        int[] dp = new int[n+1];
        dp[1] = arr[1];
        for(int i = 2; i <= n; i++) {
            dp[i]= Math.max(dp[i-1] + arr[i],arr[i]);
            max= Math.max(max,dp[i]);
        }
        max=Math.max(max,dp[1]);
        System.out.println(max);
    }
}
