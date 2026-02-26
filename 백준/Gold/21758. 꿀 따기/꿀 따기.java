import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] preSum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        preSum[0] = arr[0];

        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        int answer = 0;

        // 벌통 오른쪽 끝 배치
        for (int i = 1; i < N - 1; i++) {
            int cur = preSum[N - 1] - arr[0] - arr[i];
            cur += preSum[N - 1] - preSum[i];

            answer = Math.max(answer, cur);
        }

        // 벌통 왼쪽 끝 배치
        for (int i = 1; i < N - 1; i++) {
            int cur = preSum[i - 1] + preSum[N - 2] - arr[i];

            answer = Math.max(answer, cur);
        }

        // 벌 벌통 벌
        for (int i = 1; i < N - 1; i++) {
            int cur = preSum[i] - arr[0] + preSum[N - 2] - preSum[i - 1];

            answer = Math.max(answer, cur);
        }

        System.out.println(answer);
    }
}

/*
조건
- 세 장소에 벌 2마리 벌통을 각각 배치
- 벌은 벌통으로 날아가며 지나가는 을 모두 딴다.
 */