import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }

            Deque<Integer> dq = new ArrayDeque<>();

            int cnt = 0;
            dq.add(pq.poll());

            /*
            2 4 5 7 9
            9
            9 7
            5 9 7
             */

            while (!pq.isEmpty()) {
                if (cnt % 2 == 1) {
                    dq.addLast(pq.poll());
                } else {
                    dq.addFirst(pq.poll());
                }
                cnt++;
            }

            int max = Math.abs(dq.peekFirst() - dq.peekLast());

            int prev = dq.pollFirst();

            while (!dq.isEmpty()) {
                int next = dq.pollFirst();
                int diff = Math.abs(prev - next);

//                System.out.println(prev + " " + next);

                max = Math.max(max, diff);

                prev = next;
            }

//            max = Math.max(Math.abs(prev - dq.peekFirst()), max);

            sb.append(max).append("\n");
        }

        System.out.println(sb.toString());
    }
}

/*
조건
- N개의 통나무 원형
- 난이도 = |Ha - Hb|의 최댓값

요구
- 최소 난이도 구하기

풀이
- 높은 거에서 양옆으로 하나씩 붙이기.

 */