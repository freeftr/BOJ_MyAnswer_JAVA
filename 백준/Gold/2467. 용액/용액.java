import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;
        int ansL = 0;
        int ansR = 0;

        while (left < right) {
            int sum = (arr[left] + arr[right]);
            if (Math.abs(sum) <= min) {
                min = Math.abs(sum);
                ansL = arr[left];
                ansR = arr[right];
            }

            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                System.out.println(ansL + " " + ansR);
                System.exit(0);
            }
        }

        System.out.println(ansL + " " + ansR);
    }
}
