import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            int cnt = 0;
            int left = 0;

            while (left < N - 2) {
                int right = left + 1;

                while (right < N && arr[right - 1] < arr[right]) {
                    right++;
                }

                int lcnt = right - left - 1;

                while (right < N && arr[right - 1] > arr[right]) {
                    right++;
                }

                int rcnt = right - (left + lcnt) - 1;


                if (lcnt > 0 && rcnt > 0) {
                    cnt += lcnt * rcnt;
                }

                left = right - 1;
            }
            sb.append("#" + t + " " + cnt + "\n");
        }
        System.out.println(sb.toString());
    }
}