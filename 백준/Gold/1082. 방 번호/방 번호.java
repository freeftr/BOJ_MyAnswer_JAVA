import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] P = new int[N + 1];

        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        int firstCost = Integer.MAX_VALUE;
        int firstIdx = -1;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());

            if (P[i] <= min) {
                min = P[i];
                minIdx = i;
            }

            if (i >= 1) {
                if (P[i] <= firstCost) {
                    firstCost = P[i];
                    firstIdx = i;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());

        if (firstCost > M) {
            System.out.println(0);
            return;
        }

        // 1. 첫자리 설정 및 구입
        M -= firstCost;

        // 2. 자릿수 구하기
        int len = M / min + 1;

        // 3. 일단 제일 작은 비용으로 세팅하기
        int[] answer = new int[len];

        answer[0] = firstIdx;

        for (int i = 1; i < len; i++) {
            answer[i] = minIdx;
            M -= min;
        }

        // 4. 남는 비용으로 숫자 업그레이드
        for (int i = 0; i < len; i++) {
            for (int j = N - 1; j >= 0; j--) {
                // 현재 자리의 숫자하고 비용 비교
                int diff = P[j] - P[answer[i]];

                if (diff <= M) {
                    M -= diff;
                    answer[i] = j;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(answer[i]);
        }

        System.out.println(sb.toString());
    }
}

/*
조건
- M원 있음
- 같은 숫자 여러 개 구매 가능
- 0 ~ N - 1

요구
- 최대 M원을 사용해서 만들 수 있는 가장 큰 수 구하기

풀이
- 일단 자릿수가 커야함 -> 많은 개수의 숫자를 사기
- 숫자 자체가 커야함 -> 되도록 큰 숫자 구매

1. 제일 작은 비용의 숫자 구하기 -> 자릿수를 크게 만들 수 있음 대신, 0제외
2. 큰숫자로 채우기
 */