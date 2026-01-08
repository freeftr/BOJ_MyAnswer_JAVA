import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Ball {
        int idx;
        int color;
        int size;
        Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Ball> balls = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls.add(new Ball(i, color, size));
        }

        Collections.sort(balls, (a, b) -> {
            if (a.size == b.size) return a.color - b.color;
            return a.size - b.size;
        });

        int[] answer = new int[N];
        int[] colorSum = new int[N + 1];

        int sum = 0;
        int lastSize = 0;
        int cnt = 0;
        Ball prev = null;

        for (Ball cur : balls) {
            answer[cur.idx] = sum - colorSum[cur.color];

            if (cur.size == lastSize) {
                if (prev != null && cur.color == prev.color) {
                    answer[cur.idx] = answer[prev.idx];
                } else {
                    answer[cur.idx] -= cnt * cur.size;
                }
                cnt++;
            } else {
                lastSize = cur.size;
                cnt = 1;
            }

            sum += cur.size;
            colorSum[cur.color] += cur.size;
            prev = cur;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.print(sb);
    }
}


/*
조건
- 특정한 색과 크기 가진 공 조종.
- 자기 공보다 작고, 색이 다른 공 잡음 -> 크기만큼 점수 획득
 */