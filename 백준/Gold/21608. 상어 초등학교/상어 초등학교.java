import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static HashMap<Integer, HashSet<Integer>> likeMap = new HashMap<>();
    static int[] scores = {0, 1, 10, 100, 1000};
    static int[][] classroom;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];

        int[] order = new int[N * N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            order[i] = student;

            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 4; j++) set.add(Integer.parseInt(st.nextToken()));
            likeMap.put(student, set);
        }

        for (int idx = 0; idx < N * N; idx++) {
            int now = order[idx];
            HashSet<Integer> likes = likeMap.get(now);

            int bestX = -1, bestY = -1;
            int bestLike = -1, bestEmpty = -1;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (classroom[x][y] != 0) continue;

                    int likeCnt = 0, emptyCnt = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d], ny = y + dy[d];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                        if (classroom[nx][ny] == 0) emptyCnt++;
                        else if (likes.contains(classroom[nx][ny])) likeCnt++;
                    }

                    if (likeCnt > bestLike
                            || (likeCnt == bestLike && emptyCnt > bestEmpty)
                            || (likeCnt == bestLike && emptyCnt == bestEmpty && (bestX == -1 || x < bestX
                            || (x == bestX && y < bestY)))) {
                        bestLike = likeCnt;
                        bestEmpty = emptyCnt;
                        bestX = x;
                        bestY = y;
                    }
                }
            }

            classroom[bestX][bestY] = now;
        }

        int answer = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                int cur = classroom[x][y];
                HashSet<Integer> likes = likeMap.get(cur);
                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (likes.contains(classroom[nx][ny])) cnt++;
                }

                answer += scores[cnt];
            }
        }

        System.out.println(answer);
    }
}
