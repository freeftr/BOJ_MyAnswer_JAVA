import java.io.*;
import java.util.*;

public class Main {

    static int N, K, M;
    static ArrayList<ArrayList<Integer>> hyperTube = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> station = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) { // 0부터 M-1까지 초기화
            hyperTube.add(new ArrayList<>());
        }

        for (int i = 0; i <= N; i++) { // 1부터 N까지 초기화
            station.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int a = Integer.parseInt(st.nextToken());
                hyperTube.get(i).add(a);
                station.get(a).add(i);
            }
        }

        System.out.println(bfs(1));
    }

    static int bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        boolean[] visitedTube = new boolean[M];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start, 1});
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int dist = cur[1];

            if (v == N) {
                return dist;
            }

            for (int nht : station.get(v)) {
                if (visitedTube[nht]) continue;
                visitedTube[nht] = true;

                for (int nv : hyperTube.get(nht)) {
                    if (!visited[nv]) {
//                        System.out.println("tube: " + nht + " station: " + nv + " dist: " + (dist + 1));
                        visited[nv] = true;
                        q.add(new int[]{nv, dist + 1});
                    }
                }
            }
        }

        return -1;
    }
}