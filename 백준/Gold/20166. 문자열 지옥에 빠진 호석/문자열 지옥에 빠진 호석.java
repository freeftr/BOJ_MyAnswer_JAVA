import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static char[][] map;
    static String[] likes;
    static int cnt = 0;

    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];
        likes = new String[K];

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = s.charAt(j-1);
            }
        }

        for (int i = 0; i < K; i++) {
            likes[i] = br.readLine();
        }

        HashMap<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < K; i++) {
            cnt = 0;
            if(cache.containsKey(likes[i])) {
                sb.append(cache.get(likes[i]));
                continue;
            }
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    dfs(j,k,new StringBuilder().append(map[j][k]),likes[i], 0);
                }
            }
            cache.put(likes[i], cnt);
            sb.append(cnt + "\n");
        }

        System.out.println(sb.toString());
    }

    static void dfs(int x, int y, StringBuilder S, String target, int depth) {
        if (S.toString().equals(target)) {
            cnt++;
            return;
        }

        if (depth >= target.length()) return;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1) nx = N;
            if (nx > N) nx = 1;
            if (ny < 1) ny = M;
            if (ny > M) ny = 1;

            if (map[nx][ny] == target.charAt(depth)) {
                S.append(map[nx][ny]);
                dfs(nx, ny, S, target, depth + 1);
                S.deleteCharAt(S.length() - 1);
            }
        }
    }
}
