import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int N, ans = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] team1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        team1 = new int[N / 2];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(ans);
    }

    static void combination(int target, int start) {
        if (target == N / 2) {
            calculate();
            return;
        }
        for (int i = start; i < N; i++) {
            team1[target] = i;
            visited[i] = true;
            combination(target + 1, i + 1);
            visited[i] = false;
        }
    }

    static void calculate() {
        int sum1 = 0, sum2 = 0;
        ArrayList<Integer> team2 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!visited[i]) team2.add(i);
        }

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i == j) continue;
                sum1 += map[team1[i]][team1[j]];
            }
        }

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i == j) continue;
                sum2 += map[team2.get(i)][team2.get(j)];
            }
        }

        ans = Math.min(ans, Math.abs(sum1 - sum2));
    }
}
