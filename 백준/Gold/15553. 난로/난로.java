import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        T = new int[N];
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i < N; i++) {
            int gap = T[i] - T[i - 1] - 1;
            if (gap > 0) pq.add(gap);
        }

        int answer = T[N - 1] - T[0] + 1;

        for (int i = 0; i < K - 1; i++) {
            if (pq.isEmpty()) break;
            answer -= pq.poll();
        }

        System.out.println(answer);
    }
}
