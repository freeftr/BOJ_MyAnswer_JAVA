import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        bfs(A, B, C);

        Collections.sort(result);

        for (int a : result) {
            System.out.print(a + " ");
        }
    }

    static void bfs(int A, int B, int C) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();

        q.add(new int[]{0, 0, C});
        visited.add(0 + " " + 0 + " " + C);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if (a == 0) result.add(c);

            if (a != 0) {
                int move = Math.min(a, B - b);
                int na = a - move;
                int nb = b + move;
                int nc = c;
                if (visited.add(na + " " + nb + " " + nc))
                    q.add(new int[]{na, nb, nc});

                move = Math.min(a, C - c);
                na = a - move;
                nb = b;
                nc = c + move;
                if (visited.add(na + " " + nb + " " + nc))
                    q.add(new int[]{na, nb, nc});
            }

            if (b != 0) {
                int move = Math.min(b, A - a);
                int na = a + move;
                int nb = b - move;
                int nc = c;
                if (visited.add(na + " " + nb + " " + nc))
                    q.add(new int[]{na, nb, nc});

                move = Math.min(b, C - c);
                na = a;
                nb = b - move;
                nc = c + move;
                if (visited.add(na + " " + nb + " " + nc))
                    q.add(new int[]{na, nb, nc});
            }

            if (c != 0) {
                int move = Math.min(c, A - a);
                int na = a + move;
                int nb = b;
                int nc = c - move;
                if (visited.add(na + " " + nb + " " + nc))
                    q.add(new int[]{na, nb, nc});

                move = Math.min(c, B - b);
                na = a;
                nb = b + move;
                nc = c - move;
                if (visited.add(na + " " + nb + " " + nc))
                    q.add(new int[]{na, nb, nc});
            }
        }
    }
}

/*
조건
- ABC 물통, C는 가득 차있음

요구
- A가 비어 있을 때 C에 있을 수 있는 물의 양 모두 구하기

풀이
- bfs
 */