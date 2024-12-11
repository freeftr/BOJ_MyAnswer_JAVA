import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H;
    static int ans = 4;
    static boolean[][] graph;
    static List<int[]> temp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        graph = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!graph[i][j]) {
                    temp.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i <= 3; i++) {
            dfs(0, 0, i);
        }

        System.out.println(-1);
    }

    static void dfs(int depth, int start, int num) {
        if (depth == num) {
            if (check()) {
                ans = Math.min(ans, depth);
                System.out.println(ans);
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < temp.size(); i++) {
            int[] arr = temp.get(i);
            int x = arr[0];
            int y = arr[1];

            if (!graph[x][y] && !graph[x][y - 1] && !graph[x][y + 1]) {
                graph[x][y] = true;
                dfs(depth + 1, i + 1, num);
                graph[x][y] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int now = i;
            for (int j = 1; j <= H; j++) {
                if (graph[j][now]){
                    now++;
                }
                else if (now > 1 && graph[j][now - 1]){
                    now--;
                }
            }
            if (now != i) return false;
        }
        return true;
    }
}