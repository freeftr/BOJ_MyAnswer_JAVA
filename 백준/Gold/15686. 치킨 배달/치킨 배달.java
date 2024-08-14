import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] city;
    static int ans;
    static boolean[] check;
    static ArrayList<Node> bbq = new ArrayList<>();

    public static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2) {
                    bbq.add(new Node(i, j));
                }
            }
        }

        check = new boolean[bbq.size()];
        BackTrack(0, 0);
        System.out.println(ans);
    }

    // 백트래킹으로 치킨집을 선택하는 함수
    public static void BackTrack(int depth, int start) {
        if (depth == M) { // M개의 치킨집을 선택한 경우
            ans = Math.min(ans, city_dist());
            return;
        }
        for (int i = start; i < bbq.size(); i++) {
            check[i] = true;  // 치킨집을 선택
            BackTrack(depth + 1, i + 1);
            check[i] = false; // 선택한 치킨집을 다시 선택 해제
        }
    }

    // 도시의 치킨 거리를 계산하는 함수
    public static int city_dist() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (city[i][j] == 1) {  // 집인 경우
                    sum += chick_dist(i, j);
                }
            }
        }
        return sum;
    }

    // 특정 집의 치킨 거리를 계산하는 함수
    public static int chick_dist(int x, int y) {
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < bbq.size(); i++) {
            if (check[i]) {  // 선택된 치킨집만 고려
                Node chicken = bbq.get(i);
                int dist = Math.abs(x - chicken.x) + Math.abs(y - chicken.y);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }
}