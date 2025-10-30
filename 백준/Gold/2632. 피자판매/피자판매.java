import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] A = new int[m * 2 + 1];
        int[] B = new int[n * 2 + 1];

        for (int i = 1; i <= m; i++) {
            A[i] = Integer.parseInt(br.readLine());
            A[m + i] = A[i];
        }

        for (int i = 1; i <= n; i++) {
            B[i] = Integer.parseInt(br.readLine());
            B[n + i] = B[i];
        }

        for (int i = 1; i <= m * 2; i++) {
            A[i] += A[i - 1];
        }

        for (int i = 1; i <= n * 2; i++) {
            B[i] += B[i - 1];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int answer = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = i; j < i + m - 1; j++) {
                int sum = A[j] - A[i - 1];

                if (sum == size) answer++;
                if (sum < size) {
                    map.merge(sum, 1, (a, b) -> a + b);
                }
            }
        }

        int aTotal = A[m] - A[0];
        if (aTotal == size) answer++;
        else if (aTotal < size) {
            map.merge(aTotal, 1, (a, b) -> a + b);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i; j < i + n - 1; j++) {
                int sum = B[j] - B[i - 1];

                if (sum == size) answer++;
                if (sum < size) {
                    if (map.containsKey(size - sum)) {
                        int left = map.get(size - sum);
                        answer += left;
                    }
                }
            }
        }

        int bTotal = B[n] - B[0];
        if (bTotal == size) answer++;
        else if (bTotal < size) {
            Integer left = map.get(size - bTotal);
            if (left != null) answer += left;
        }

        System.out.println(answer);
    }
}

/*
조건
- 피자를 주문함.
- 여러 조각으로 나누어져있음.
- 2조각 이상 판매할 떄는 반드시 연속된 조각들을.
- 근데 이제 합이 주문과 같아야 함.
- 피자 종류를 섞어도댐.

요구
- 판매하는 모든 방법의 가지 수 계산.

풀이
- 각각 피자를 누적합으로 해논다. (연속된 사이즈를 빠르게 측정 위함).
-
 */
