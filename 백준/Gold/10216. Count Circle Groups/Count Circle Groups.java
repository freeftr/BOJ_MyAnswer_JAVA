import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    static class Node {
        int x;
        int y;
        int r;
        Node(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            ArrayList<Node> nodes = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());

                nodes.add(new Node(x, y, R));
            }

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (find(i) != find(j) && checkDist(nodes.get(i), nodes.get(j))) {
                        union(i, j);
                    }
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (i == find(i)) cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static boolean checkDist(Node a , Node b) {
        long dx = (long)a.x - b.x;
        long dy = (long)a.y - b.y;
        long sumR = (long)a.r + b.r;
        return dx * dx + dy * dy <= sumR * sumR;
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }
}

/*
조건
- 2차원 평면의 N 곳에 적군의 진영
- 적진은 R 거리 내의 범위를 통신 영역으로 가짐.
- 통신 영역이 겹치면 통신가능.
- 다른데 통해서 가도댐.

요구
- 적진 그룹 개수 구하기

풀이
- 통신 영역 전처리
- 겹치는 곳끼리 노드, 간선화
- 그룹화
 */