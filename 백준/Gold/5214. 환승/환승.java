import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static ArrayList<ArrayList<Tube>> graph = new ArrayList<>();

    static class Tube {
        ArrayList<Integer> tubes;
        int idx;

        Tube (ArrayList<Integer> tubes, int idx) {
            this.tubes = tubes;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> tubes = new ArrayList<>();

            for (int j = 0; j < K; j++) {
                tubes.add(Integer.parseInt(st.nextToken()));
            }

            for (int j = 0; j < K; j++) {
                graph.get(tubes.get(j)).add(new Tube(tubes, i));
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        boolean[] visitedTubes = new boolean[M];

        q.add(new int[]{1, 1});
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int d = cur[1];

            if (v == N) return d;

            for(Tube t : graph.get(v)) {
                ArrayList<Integer> nexts = t.tubes;
                if (visitedTubes[t.idx]) continue;
                visitedTubes[t.idx] = true;

                for (int nv : nexts) {
                    if (visited[nv]) continue;
                    visited[nv] = true;
                    q.add(new int[]{nv, d + 1});
                }
            }
        }

        return -1;
    }
}

/*
조건
- 하이퍼튜브를 자주탐.
- K개를 서로 연결.
- 1번에서 N번가는 역의 최소 개수?
 */