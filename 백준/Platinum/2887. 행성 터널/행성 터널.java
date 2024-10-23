import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] parent;
    static ArrayList<Planet> list = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Planet {
        int index;
        int x;
        int y;
        int z;

        public Planet(int index, int x, int y, int z) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int cost;
        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 행성 좌표 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.add(new Planet(i, x, y, z));
        }

        // x에 대해 정렬 후 간선 추가
        Collections.sort(list, (o1, o2) -> o1.x - o2.x);
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = list.get(i);
            Planet p2 = list.get(i+1);
            int dist = Math.abs(p1.x - p2.x);
            pq.offer(new Edge(p1.index, p2.index, dist));
        }

        // y에 대해 정렬 후 간선 추가
        Collections.sort(list, (o1, o2) -> o1.y - o2.y);
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = list.get(i);
            Planet p2 = list.get(i+1);
            int dist = Math.abs(p1.y - p2.y);
            pq.offer(new Edge(p1.index, p2.index, dist));
        }

        // z에 대해 정렬 후 간선 추가
        Collections.sort(list, (o1, o2) -> o1.z - o2.z);
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = list.get(i);
            Planet p2 = list.get(i+1);
            int dist = Math.abs(p1.z - p2.z);
            pq.offer(new Edge(p1.index, p2.index, dist));
        }

        // 크루스칼 알고리즘으로 최소 스패닝 트리 구하기
        int ans = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int a = e.a;
            int b = e.b;
            int cost = e.cost;

            if (find(a) != find(b)) {
                union(a, b);
                ans += cost;
                cnt++;
                if (cnt == N - 1) break;
            }
        }

        System.out.println(ans);
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 > v2) {
            parent[v2] = v1;
        } else {
            parent[v1] = v2;
        }
    }
}