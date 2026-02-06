import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] A = new int[a];
            int[] B = new int[b];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < a; i++) {
                A[i] = Integer.parseInt(st.nextToken());

            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < b; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);

            int answer = 0;

            for (int i = 0; i < a; i++) {
                int left = 0;
                int right = b;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (B[mid] >= A[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                answer += right;
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- A는 자기보다 작은 B만 먹을 수 있다.

요구
- A의 크기가 B보다 큰 쌍이 몇개 있는지

1 1 3 7 8
1 3 6
 */