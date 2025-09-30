import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        long left = 1;
        long right = arr[N - 1];
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid, arr)) {
                answer = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static boolean check(long mid, long[] arr) {
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt += arr[i] / mid;
        }

        return cnt >= K;
    }
}

/*
조건
- N개 주전자
- K명에게 똑같이 배분

요구
- 최대한의 막거리 주기

풀이
- 매개변수 탐색
- 막걸리 분배 용량을 기준으로 설정.
 */