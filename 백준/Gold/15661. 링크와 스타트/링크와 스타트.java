import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] graph;
    static boolean[] selected;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new boolean[N];

        // 팀의 크기를 1부터 N-1까지 변경하면서 조합을 탐색
        for (int teamSize = 1; teamSize < N; teamSize++) {
            combination(0, 0, teamSize);
        }

        System.out.println(ans);
    }

    static void combination(int depth, int start, int teamSize) {
        if (depth == teamSize) {
            int A = 0, B = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (selected[i] && selected[j]) {
                        A += graph[i][j];
                    } else if (!selected[i] && !selected[j]) {
                        B += graph[i][j];
                    }
                }
            }

            ans = Math.min(ans, Math.abs(A - B));
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            combination(depth + 1, i + 1, teamSize);
            selected[i] = false;
        }
    }
}