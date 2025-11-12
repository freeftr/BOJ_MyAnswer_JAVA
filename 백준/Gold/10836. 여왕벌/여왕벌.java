import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][M];

        for (int i = 0; i < M; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            map = leadGrowth(M, zero, one, map, two);
        }

        for (int y = 1; y < M; y++) {
            int max = map[0][y];
            for (int x = 1; x < M; x++) {
                map[x][y] = max;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[][] leadGrowth(int M, int zero, int one, int[][] map, int two) {
        int x = M - 1;
        int y = 0;

        while (zero != 0) {
            if (x == 0) {
                y++;
            } else {
                x--;
            }
            zero--;
        }

        while (one != 0) {
            map[x][y] += 1;
            if (x == 0) {
                y++;
            } else {
                x--;
            }
            one--;
        }

        while (two != 0) {
            map[x][y] += 2;
            if (x == 0) {
                y++;
            } else {
                x--;
            }
            two--;
        }

        return map;
    }
}

/*
조건
- M * M 격자 별집 있고, 각각 애벌레 있음
- 정오에 한번씩 자람
- 처음 크기는 1
- N일동안 반복
- +0, +1, +2 3가지 경우의 수 다음 규칙을 따름
- (M - 1, 0) -> (0, 0) -> (0, M - 1)
- 나머지는 왼쪽, 왼쪽 위, 위 중 가장 많이 자란 만큼 자란다.
 */
