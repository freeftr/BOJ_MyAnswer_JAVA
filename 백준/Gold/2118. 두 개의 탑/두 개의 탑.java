import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());

        int[] towers = new int[N + 1];
        int[] preSum = new int[N + 1];
        int[] locations = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            towers[i] = Integer.parseInt(br.readLine());
        }

        locations[0] = 0;
        for (int i = 1; i <= N; i++) {
            locations[i] = towers[i] + locations[i - 1];
        }

        int left = 0;
        int right = 1;
        int answer = 0;

        while (left < right && right <= N) {
            int in = locations[right] - locations[left];
            int out = locations[N] - locations[right] + locations[left];

            answer = Math.max(answer, Math.min(in, out));
            if (in < out) {
                right++;
            } else {
                left++;
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 1 ~ N번 지점. 원형
- 두 개의 탑을 세우는데 그 사이 거리가 최대가 되도록
- 거리 = 시계 방향, 반시계방향으로 경로 길이 중 작은 것.

요구
- 두 탑 거리의 최댓값 구하기.

풀이
- 누적합 해놓고 투포인터로 탐색.
 */
