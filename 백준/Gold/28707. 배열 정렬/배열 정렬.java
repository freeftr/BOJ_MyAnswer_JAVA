import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static class State {
        int[] a;
        int dist;

        State(int[] a, int dist) {
            this.a = a;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[][] cmds = new int[M][3];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cmds[i][0] = a - 1;
            cmds[i][1] = b - 1;
            cmds[i][2] = c;
        }

        int[] sortedA = Arrays.copyOf(A, N);
        Arrays.sort(sortedA);
        String target = makeKey(sortedA);

        HashMap<String, Integer> dist = new HashMap<>();
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        dist.put(makeKey(A), 0);
        pq.add(new State(A, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int[] v = cur.a;
            int d = cur.dist;

            String key = makeKey(v);

            if (key.equals(target)) {
                System.out.println(d);
                return;
            }

            if (dist.containsKey(key)) {
                int value = dist.get(key);
                if (value < d) continue;
            } else {
                dist.put(key, d);
            }

            for (int[] c : cmds) {
                int a = c[0];
                int b = c[1];
                int nd = c[2];

                int[] nv = swap(a, b, v);
                String nk = makeKey(nv);

                if (dist.containsKey(nk)) {
                    if (dist.get(nk) > d + nd) {
                        dist.put(nk, d + nd);
                        pq.add(new State(nv, d + nd));
                    }
                } else {
                    dist.put(nk, d + nd);
                    pq.add(new State(nv, d + nd));
                }
            }
        }

        System.out.println(-1);
    }

    static String makeKey(int[] arr) {
        String result = "";
        for (int num : arr) result += num + " ";
        return result.substring(0, result.length() - 1);
    }

    static int[] swap(int a, int b, int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int temp = newArr[a];
        newArr[a] = newArr[b];
        newArr[b] = temp;
        return newArr;
    }
}
/*
조건
- A를 비 내림차순으로 만들어야 함.
- 조작이 주어짐.
- a, b, c = a와 b번째 숫자를 바꾸고 비용은 C

요구
- 비내림차순으로 만드는 최소 비용 구하기

풀이
- 맵 = 거리 배열
- 키 = 정렬 상태
- 값 = 횟수

1   4   3   2
1   4   2   3       2
1   2   4   3       5
1   2   3   4       7
 */