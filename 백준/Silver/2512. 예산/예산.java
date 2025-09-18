import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
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

        int M = Integer.parseInt(br.readLine());

        int left = 0;
        int right = M;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid, arr, M)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int answer = 0;
        for (int i : arr) {
            if (i >= right) {
                answer = right;
                break;
            } else {
                answer = Math.max(answer, i);
            }
        }

        System.out.println(answer);
    }

    static boolean check(int mid, int[] arr, int M) {
        int sum = 0;
        for (int i : arr) {
            sum += i < mid ? i : mid;
        }

        return sum <= M;
    }
}
/*
예산을 분배
- 모든 요청에 배정 가능 => 그대로 배정
- 배정될 수 없는 경우 => 상한액 정해놓고 이하인 것들만 처리
 */