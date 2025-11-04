import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] ponds = new int[N];
        int[] dir = {1,-1};
        Queue<int[]> q = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ponds[i] = Integer.parseInt(st.nextToken());
            visited.add(ponds[i]);
            q.add(new int[]{ponds[i], 0});
        }

        long answer = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int d = cur[1];

            for (int i = 0; i < 2; i++) {
                int nv = v + dir[i];

                if (K > 0 && !visited.contains(nv)) {
                    answer += d + 1;
                    visited.add(nv);
                    K--;
                    q.add(new int[]{nv, d + 1});
                }
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 일직선 상에 N개의 샘터.
- K채의 집을 지으려고 함.
- 위치는 중복되지 않음.
- 가능하면 샘터의 주변에 집들을 지어 불행도 합이 최소가 되게.
- 불행도 = 샘터까지의 거리.

요구
- 불행도의 합의 최솟값 구하기.

풀이
- 각 샘터로부터 거리를 1씩 늘려가면 빈자리 확인?
 */
