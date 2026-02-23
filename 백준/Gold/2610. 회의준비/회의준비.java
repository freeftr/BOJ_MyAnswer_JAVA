import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int[][] dist = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;

                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        HashMap<Integer, ArrayList<Integer>> teams = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            teams.computeIfAbsent(find(i), k -> new ArrayList<>()).add(i);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int key : teams.keySet()) {
            ArrayList<Integer> team = teams.get(key);

            int min = N * N;
            int best = -1;
            for (int leader : team) {
                int longest = 0;

                for (int follower : team) {
                    longest = Math.max(dist[follower][leader], longest);
                }

                if (longest < min) {
                    min = longest;
                    best = leader;
                }
            }

            result.add(best);
        }

        Collections.sort(result);

        sb.append(teams.size()).append("\n");

        for (int r : result) {
            sb.append(r).append("\n");
        }

        System.out.println(sb);
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}

/*
조건
- 위원회를 구성
    - 서로 알고 있으면 같은 위원회
    - 위원회의 수는 최대가 되게
- 각 위원회 별 대표를 뽑음
- 참석자는 자신이 알고 있는 사람에게만 의견 전달 가능
- 의사전달시간: 대표에게 의견을 전달하는 가장 적은 사람 거치는 수

요구
- 위원회 별로 모든 참석자들의 의사전달 시간 중 최댓값이 최소가 되게

풀이
- 플로이드 워셜로 구성원간 의사 전달 시간 구하기
- 대표는 분리 집합으로 찾기
 */