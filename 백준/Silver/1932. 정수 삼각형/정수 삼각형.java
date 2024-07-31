import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        int sum = 0;
        int[] temp;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for(int i = 1; i < n; i++) {
            arr[i][0] += arr[i-1][0];
        }
        for(int i = 1; i < n; i++) {
            arr[i][i] += arr[i-1][i];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= i; j++) {
                arr[i][j] += Math.max(arr[i-1][j-1], arr[i-1][j]);
            }
        }
        int max=0;
        for(int i = 0; i < n; i++){
            if(max<=arr[n-1][i]){
                max = arr[n-1][i];
            }
        }
        System.out.println(max);
    }
}
