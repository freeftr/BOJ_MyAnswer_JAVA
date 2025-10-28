import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N - 1;
        int a = 0;
        int b = 0;
        long max = Long.MAX_VALUE;
        while (left < right) {
            long sumVal = (long) arr[left] + arr[right];
            long sum = Math.abs(sumVal);

            if (sum < max) {
                max = sum;
                a = arr[left];
                b = arr[right];
                if (sum == 0) break;
            }

            if (sumVal > 0) right--;
            else left++;
        }

        System.out.println(a + " " + b);
    }
}

/*
조건
- 산성용액 특성값 1 ~ 1_000_000_000
- 알칼리 특성값 -1 ~ -1_000_000_000
- 혼합 특성값 = 혼합에 사용된 각 용액의 특성값의 합.
- 0에 가장 가깝게 하려고 함.

요구
- 0에 가장 가까운 용액을 만들어내는 두 용액 특성값 찾기.
 */