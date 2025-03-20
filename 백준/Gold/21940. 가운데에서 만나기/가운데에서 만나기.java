import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] cities;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cities = new int[N + 1];
        dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 40000000);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[from][to] = Math.min(cost, dist[from][to]);
        }

        K = Integer.parseInt(br.readLine());
        cities = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != 40000000 && dist[k][j] != 40000000) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int maxDistance = 0;
            boolean isValid = true;

            for (int city : cities) {
                if (dist[city][i] == 40000000 || dist[i][city] == 40000000) {
                    isValid = false;
                    break;
                }
                maxDistance = Math.max(maxDistance, dist[city][i] + dist[i][city]);
            }

            if (!isValid) continue;

            if (maxDistance < min) {
                min = maxDistance;
                list.clear();
                list.add(i);
            } else if (maxDistance == min) {
                list.add(i);
            }
        }

        Collections.sort(list);
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}