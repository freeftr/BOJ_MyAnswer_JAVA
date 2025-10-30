import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int min = 0;

        int[] lectures = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            min = Math.max(min, lectures[i]);
        }

        long left = min;
        long right = N * 10_000L;

        while (left < right) {
            long mid = (left + right) / 2;

            if (check(N, M, mid, lectures)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean check(int N, int M, long mid, int[] arr) {
        long sum = 0;
        long cnt = 1;

        for (int i = 0; i < N; i++) {
            sum += arr[i];

            if (sum > mid) {
                sum = arr[i];
                cnt++;
            }
        }

        return cnt <= M;
    }
}

/*
조건
- 기타강의를 판다.
- 블루레이에는 N개의 강의
- 강의 순서 바뀌면 안댐.
- M개의 블루레이 만들기.
- 블루레이의 크기를 최소.

요구
- 각 강의의 길이가 주어질 때, 가능한 블루레이의 크기 중 최소 구하기.

풀이
- M을 기준으로 이분탐색하는거같은데.
 */