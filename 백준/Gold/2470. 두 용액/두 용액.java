import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long ans = Long.MAX_VALUE;
        long alcal = 0;
        long acid = 0;

        for (int i = 0; i < N - 1; i++) {
            long current = arr[i];

            int left = i + 1;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                long sum = current + arr[mid];

                if (Math.abs(sum) < Math.abs(ans)) {
                    ans = sum;
                    alcal = current;
                    acid = arr[mid];
                }

                if (sum > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        if (alcal > acid) {
            long temp = alcal;
            alcal = acid;
            acid = temp;
        }

        System.out.println(alcal + " " + acid);
    }
}