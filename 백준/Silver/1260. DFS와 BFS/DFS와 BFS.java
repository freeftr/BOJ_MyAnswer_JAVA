import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V;
    static ArrayList<PriorityQueue<Integer>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graph2 = new ArrayList<>();
    static boolean[] visited;
    static boolean[] visited2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new PriorityQueue<>());
            graph2.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
            graph2.get(a).add(b);
            graph2.get(b).add(a);
        }

        for (int i = 0; i <= N; i++) {
            Collections.sort(graph2.get(i));
        }

        visited = new boolean[N + 1];
        dfs(V, graph, visited);
        System.out.println();  // 줄바꿈 처리

        visited2 = new boolean[N + 1];
        bfs(V);
        System.out.println();
    }

    static void dfs(int v, ArrayList<PriorityQueue<Integer>> graph, boolean[] visited) {
        // 현재 노드를 방문 처리
        visited[v] = true;
        System.out.printf("%d ", v);

        // 인접한 노드들에 대해 DFS 재귀 호출
        PriorityQueue<Integer> pq = graph.get(v);
        while (!pq.isEmpty()) {
            int nv = pq.poll();  // 우선순위 큐에서 하나씩 꺼냄
            if (!visited[nv]) {
                dfs(nv, graph, visited);
            }
        }
    }

    static void bfs(int x) { //queue
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        visited2[x] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");

            for (int nv : graph2.get(v)) {
                if (!visited2[nv]) {
                    visited2[nv] = true;
                    q.offer(nv);
                }
            }
        }
    }
}