import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, int[]> dir = new HashMap<>();
    static int[] parent;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dir.put("W", new int[]{0, -1});
        dir.put("E", new int[]{0,  1});
        dir.put("N", new int[]{-1, 0});
        dir.put("S", new int[]{ 1, 0});

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        parent = new int[N * M];

        for (int i = 0; i < N * M; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = s[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int idx = i * M + j;
                int[] d = dir.get(map[i][j]);
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                int next = ni * M + nj;
                if (find(idx) != find(next)) {
                    union(idx, next);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int idx = i * M + j;
                if (find(idx) == idx) answer++;
            }
        }

        System.out.println(answer);
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parent[a] = b;
    }
}
