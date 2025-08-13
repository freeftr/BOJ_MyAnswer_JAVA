import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 1;
        int aIdx = N - 1;
        int bIdx = M - 1;
        long availableA = 1;
        long availableB = 0;

        while (aIdx >= 0 && bIdx >= 0) {
            if (availableA == 0) break;

            availableA--;
            availableB += A[aIdx];
            aIdx--;
            answer--;

            while (bIdx >= 0 && availableB > 0) {
                availableB--;
                availableA += B[bIdx];
                answer += B[bIdx];
                bIdx--;
            }
        }

        System.out.println(answer);
    }
}
// 콘센트는 A만
// 첫 멀티탭은 A에 꼽을 수 있고 B제공
// 두번째 멀티탭은 B에 꼽을 수 있고 A 제공