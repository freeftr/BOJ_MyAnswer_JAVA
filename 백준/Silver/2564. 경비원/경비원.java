import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());

        int[][] shops = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            shops[i][0] = dir;
            shops[i][1] = d;
        }

        st = new StringTokenizer(br.readLine());
        int direction = Integer.parseInt(st.nextToken());
        int dist = Integer.parseInt(st.nextToken());

        int answer = 0;

        for (int[] shop : shops) {
            int dir = shop[0];
            int d = shop[1];

            if (dir == direction) {
                answer += Math.abs(dist - d);
                continue;
            }

            if ((dir == 1 && direction == 2) || (dir == 2 && direction == 1)) {
                answer += r;
                answer += Math.min(dist + d, c - dist + c - d);
                continue;
            }

            if ((dir == 3 && direction == 4) || (dir == 4 && direction == 3)) {
                answer += c;
                answer += Math.min(dist + d, r - dist + r - d);
                continue;
            }

            if (dir == 1 && direction == 3) {
                answer += d + dist;
                continue;
            }

            if (dir == 1 && direction == 4) {
                answer += c - d + dist;
                continue;
            }

            if (dir == 2 && direction == 3) {
                answer += d + r - dist;
                continue;
            }

            if (dir == 2 && direction == 4) {
                answer += c - d + dist;
                continue;
            }

            if (dir == 3 && direction == 2) {
                answer += r - d + dist;
                continue;
            }

            if (dir == 3 && direction == 1) {
                answer += d + dist;
                continue;
            }

            if (dir == 4 && direction == 1) {
                answer += d + c - dist;
                continue;
            }

            if (dir == 4 && direction == 2) {
                answer += r - d + c - dist;
                continue;
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 동근이는 경비원
- 1-based 좌표

요구
- 각 상점까지 최단 거리

풀이
- 같은 방향 - Math.abs(dist, d)
- 동근 = 남 && 상점 = 북
 */
