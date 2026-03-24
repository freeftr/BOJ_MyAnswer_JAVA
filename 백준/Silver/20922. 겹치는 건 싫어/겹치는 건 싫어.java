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
        int[] cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int answer = 0;

        while (right < N) {
            cnt[arr[right]]++;

            while (cnt[arr[right]] > K) {
                cnt[arr[left]]--;
                left++;
            }

            answer = Math.max(answer, right - left + 1);
            right++;
        }

        System.out.println(answer);
    }
}

/*
조건
- 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열 구하기

요구
- 최장 길이 구하기

풀이
- 투포인터
 */