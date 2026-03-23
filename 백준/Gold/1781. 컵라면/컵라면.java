import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] p = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(p, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(p[i][1]);

            if (pq.size() > p[i][0]) pq.poll();
        }

        int answer = 0;

        while (!pq.isEmpty()) answer += pq.poll();

        System.out.println(answer);
    }
}

/*
조건
- 문제와 데드라인이 있음
- 문제당 컵라면 존재

요구
- 최대 컵라면 수 구하기

풀이
1. 가장 데드라인 가까운 문제 해결
2. 만약 여러 개면 점수 높은 순대로.
3. 시간++ 하고 지난 데드라인 전부 poll
 */
