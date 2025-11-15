import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<int[]> stations = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> fuels = new PriorityQueue<>((a, b) -> b - a);

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // a = 시작위치에서 주유소까지 거리
            // b = 그 주유소에서 채울 수 있는 연료의 양.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            stations.add(new int[]{a, b});
        }

        st = new StringTokenizer(br.readLine());
        // L = 성경이의 위치에서 마을까지 거리
        // P = 트럭에 원래 있던 연료의 양
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int answer = 0;

        while (P < L) {
            // 일단 다 들른다고 가정. 다음 마을을 갈 수 있는 경우. + 연료 대기열에 얻을 수 있는 연료 추가.
            while (!stations.isEmpty() && stations.peek()[0] <= P) {
                fuels.add(stations.poll()[1]);
            }

            // 연료 대기열에 연료가 없으면
            if (fuels.isEmpty()) {
                System.out.println(-1);
                return;
            }

            // 일단 하나씩 뽑아서 연료에 추가.
            // P가 L보다 커지면 갈 수 있다는 뜻이니깐 바로 종료함.
            answer++;
            P += fuels.poll();
        }

        System.out.println(answer);
    }
}

/*
조건
- 트럭 연료탱크에 구멍남.
- 1km 당 1L 연료가 나감.
- 중간에 주유소있는데 차를 최소한으로 멈춰야 함.
- 주유소별 얻을 수 있는 연료의 양이 정해져 있음

요구
- 주유소에서 멈추는 횟수 구하기.

0   4   5   11  15  25(마을)
 */