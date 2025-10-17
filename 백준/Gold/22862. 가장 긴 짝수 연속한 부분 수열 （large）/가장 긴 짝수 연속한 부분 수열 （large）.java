import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int odd = 0;

        if (arr[0] % 2 == 1) odd++;

        int answer = 0;
        while (left <= right && right < N) {
            if (odd <= K) {
                answer = Math.max(answer, right - left + 1 - odd);
                right++;
                if ( right < N && arr[right] % 2 == 1) odd++;
            } else {
                if (arr[left] % 2 == 1) odd--;
                left++;
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 길이가 N인 수열 S
- K번 삭제 가능.

요구
- K번 삭제하고 남은 수열에서 짝수로 이루어져 있는 부분 수열중 가장 긴 길이 구하기.

풀이
- 두포인터로 길이별로 돌기
- 안에 홀수 개수가 K이하면 OK
 */