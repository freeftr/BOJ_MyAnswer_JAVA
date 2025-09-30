import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
            sum += arr[i];
        }

        long left = max;
        long right = sum;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid, arr)) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean check(long mid, int[] arr) {
        int m = 1;
        long K = mid;
        for (int i = 0; i < N; i++) {
            if (arr[i] <= K) {
                K -= arr[i];
            } else {
                K = mid - arr[i];
                m++;
            }
        }

        return m <= M;
    }
}

/*
조건
- N일 중에 M번만 꺼내기
- K원 인출
- 가능 시 그대로 사용.
- 불가능 시 남은 금액 통장에 집어넣고 인출

요구
- 최소금액 K 구하기
 */
