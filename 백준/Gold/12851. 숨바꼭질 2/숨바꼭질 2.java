import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] dx = {-1, 1, 0};
    static int[] visited = new int[100001];
    static int answer = 0, cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N==K) {
            System.out.println(0);
            System.out.println(1);
            System.exit(0);
        }

        bfs(N);
        System.out.println(answer);
        System.out.println(cnt);
    }

    static void bfs(int n) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{n,0});
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[n] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int time = cur[1];

            if (x==K){
                if (answer == time) {
                    cnt++;
                } else {
                    answer = time;
                }
            }

            for (int i = 0; i < 3; i++) {
                int next = x + dx[i];
                if (i==2) {
                    next = x*2;
                }

                if (next < 0 || next > 100000) continue;
                if (visited[next]< time) continue;
                visited[next] = time;
                q.add(new int[]{next, time+1});
            }
        }

    }
}
