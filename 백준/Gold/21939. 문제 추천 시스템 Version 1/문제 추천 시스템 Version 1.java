import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> problems = new HashMap<>();
        PriorityQueue<int[]> hard = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return b[0] - a[0];
            return b[1] - a[1];
        });
        PriorityQueue<int[]> easy = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            hard.add(new int[]{P, L});
            easy.add(new int[]{P, L});
            problems.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            if (cmd.equals("add")) {
                int a = Integer.parseInt(st.nextToken());
                problems.put(t, a);
                hard.add(new int[]{t, a});
                easy.add(new int[]{t, a});
            } else if (cmd.equals("recommend")) {
                if (t == 1) {
                    while (!problems.containsKey(hard.peek()[0]) || problems.get(hard.peek()[0]) != hard.peek()[1]) hard.poll();
                    System.out.println(hard.peek()[0]);
                }
                if (t == -1) {
                    while (!problems.containsKey(easy.peek()[0]) || problems.get(easy.peek()[0]) != easy.peek()[1]) easy.poll();
                    System.out.println(easy.peek()[0]);
                }
            } else if (cmd.equals("solved")) {
                problems.remove(t);
            }
        }
    }
}

/*
조건
- recommend : x가 1이면 가장 어려운 문제 번호 출력, x가 -1이면 가장 쉬운 번호 출력,
- add P L : 추천 문제 리스트에 난이도 L인 문제 P를 추가.
- problems P : P를 제거
 */