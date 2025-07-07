import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1];

        int total = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        int left = 0;
        int right = total;
        int answer = 0;
        while(left <= right) {
            int mid = (left+right)/2;

            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                sum += arr[i];

                if (sum >= mid) {
                    sum = 0;
                    cnt++;
                }
            }

            if (cnt >= K) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }

        System.out.println(answer);
    }
}
