import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[100001];
        long answer = 0;
        int right = 0;

        for (int left = 0; left < N; left++) {
            while (right < N && cnt[arr[right]] == 0) {
                cnt[arr[right]]++;
                right++;
            }
            answer += (right - left);
            cnt[arr[left]]--;
        }

        System.out.println(answer);
    }
}
