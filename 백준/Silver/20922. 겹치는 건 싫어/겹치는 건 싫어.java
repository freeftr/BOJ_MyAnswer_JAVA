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
        int answer = 0;
        int[] cnt = new int[100001];

        for (int right = 0; right < N; right++) {
            cnt[arr[right]]++;

            while (cnt[arr[right]] > K) {
                cnt[arr[left]]--;
                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        System.out.println(answer);
    }
}

/*
조건
- 수열에서 같은 원소가 여러 개 들어 있는 수열 싫어함
- K개 이하로 들어 있는 최장 연속 부분 수열 길이 구하기


 */