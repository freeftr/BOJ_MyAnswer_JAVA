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

        int[] times = new int[N];
        int maxT = 0;

        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            if (times[i] > maxT) maxT = times[i];
        }

        long low = 0;
        long high = (long) maxT * M;
        long answer = 0;

        while (low <= high) {
            long mid = (low + high) / 2;

            if (check(mid, times)) {
                high = mid - 1;
                answer = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean check(long mid, int[] times) {
        long cnt = 0;
        for (int t : times) {
            cnt += mid / t;
            if (cnt >= M) return true;
        }
        return cnt >= M;
    }
}


/*
조건
- M명, N개의 심사대
- 항상 빈자리 안채워도 되고 더 빨리 끝나면 거기 가도댐.

요구
- 걸리는 시간 최솟값 구하기

풀이
- 매개변수 탐색
- 걸리는 시간으로 기준으로 설정

a(7)    b(10)
7       10

 */
