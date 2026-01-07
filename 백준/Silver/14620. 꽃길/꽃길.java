import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[][] garden;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        garden = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int depth) {
        if (depth == 3) {
            int result = calculate();

            if (result != -1) {
                answer = Math.min(answer, result);
            }
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                dfs(depth + 1);
                visited[i][j] = false;
            }
        }
    }

    static int calculate() {
        boolean[][] flower = new boolean[N][N];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j]) {
                    flower[i][j] = true;
                    sum += garden[i][j];

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (flower[nx][ny] || visited[nx][ny]) return -1;

                        flower[nx][ny] = true;
                        sum += garden[nx][ny];
                    }
                }
            }
        }
        
        return sum;
    }
}

/*
조건
- 꽃 심기
- 3개의 씨앗
- + 모양으로 개화
- 꽃잎이 안겹치게
- 격자마다 가격있음

요구
- 꽃 심는 최소 비용 구하기

풀이
- 완탐, 백트래킹
 */