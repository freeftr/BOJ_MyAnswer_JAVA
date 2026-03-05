import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] friends = new String[N];

        for (int i = 0; i < N; i++) {
            friends[i] = br.readLine();
        }

        int left = 0;
        int right = K;
        long answer = 0;
        int[] cnt = new int[300001];

        for (int i = 0; i <= right; i++) {
            cnt[friends[i].length()]++;
        }

        answer += cnt[friends[0].length()] - 1;

        while (left < N - 1) {
            cnt[friends[left].length()]--;
            left++;
            right++;
            if (right < N) {
                cnt[friends[right].length()]++;
            }
            answer += cnt[friends[left].length()] - 1;
        }

        System.out.println(answer);
    }
}

/*
조건
- 반 등수와 친구
- 자신과 반 등수의 차이가 K를 넘으면 친구가 아님
- 좋은 친구는 이름의 길이가 같아야 함.
 */