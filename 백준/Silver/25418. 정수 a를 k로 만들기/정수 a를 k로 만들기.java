import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[1000001];

        q.add(new int[]{A, 0});
        visited[A] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];

            if (v == K) {
                System.out.println(cur[1]);
                return;
            }

            int nv = v + 1;

            if (nv <= 1000000 && !visited[nv]) {
                visited[nv] = true;
                q.add(new int[]{nv, cur[1] + 1});
            }

            nv = v * 2;

            if (nv <= 1000000 && !visited[nv]) {
                visited[nv] = true;
                q.add(new int[]{nv, cur[1] + 1});
            }
        }
    }
}
