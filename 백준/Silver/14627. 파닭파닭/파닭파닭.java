import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[S];
        long total = 0;
        int maxLen = 0;
        for (int i = 0; i < S; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
            if (arr[i] > maxLen) maxLen = arr[i];
        }

        int low = 1, high = maxLen, best = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            long cnt = 0;
            for (int len : arr) cnt += (len / mid);

            if (cnt >= C) {
                best = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        long leftover = total - (long) C * best;
        System.out.println(leftover);
    }
}
