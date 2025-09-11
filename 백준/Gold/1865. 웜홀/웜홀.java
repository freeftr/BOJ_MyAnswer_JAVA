import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, W;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            edges.clear();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges.add(new Edge(S, E, T * -1));
            }

            int[] dist = new int[N + 1];
            Arrays.fill(dist, 0);

            boolean negative = false;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < edges.size(); j++) {
                    Edge edge = edges.get(j);

                    if (dist[edge.e] > dist[edge.s] + edge.t) {
                        dist[edge.e] = dist[edge.s] + edge.t;
                        if (i == N) {
                            negative = true;
                            break;
                        }
                    }
                }
                if (negative) break;
            }

            System.out.println(negative ? "YES" : "NO");
        }
    }

    static class Edge {
        int s;
        int e;
        int t;

        Edge(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }
}

/*
N개의 지점
M개의 도로
W 웜홀 => 음의 간선
*/
