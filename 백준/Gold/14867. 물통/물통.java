import java.io.*;
import java.util.*;

public class Main {

    static int A, B, a, b;
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { 0, 0, 0 });
        set.add(0 + " " + 0);

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int nowA = arr[0], nowB = arr[1];
            int dist = arr[2];

            if (nowA == a && nowB == b) {
                return dist;
            }

            // 물통 A를 가득 채우는 경우
            if (!set.contains(A + " " + nowB)) {
                set.add(A + " " + nowB);
                q.add(new int[] { A, nowB, dist + 1 });
            }

            // 물통 B를 가득 채우는 경우
            if (!set.contains(nowA + " " + B)) {
                set.add(nowA + " " + B);
                q.add(new int[] { nowA, B, dist + 1 });
            }

            // 물통 A를 비우는 경우
            if (!set.contains(0 + " " + nowB)) {
                set.add(0 + " " + nowB);
                q.add(new int[] { 0, nowB, dist + 1 });
            }

            // 물통 B를 비우는 경우
            if (!set.contains(nowA + " " + 0)) {
                set.add(nowA + " " + 0);
                q.add(new int[] { nowA, 0, dist + 1 });
            }

            // B -> A로 이동하는 경우 (A가 넘치는 경우)
            if (nowA + nowB > A) {
                int newA = A;
                int newB = nowB - (A - nowA);
                if (!set.contains(newA + " " + newB)) {
                    set.add(newA + " " + newB);
                    q.add(new int[] { newA, newB, dist + 1 });
                }
            }

            // B -> A로 이동하는 경우 (A가 안 넘치는 경우)
            if (nowA + nowB <= A) {
                int newA = nowA + nowB;
                int newB = 0;
                if (!set.contains(newA + " " + newB)) {
                    set.add(newA + " " + newB);
                    q.add(new int[] { newA, newB, dist + 1 });
                }
            }

            // A -> B로 이동하는 경우 (B가 넘치는 경우)
            if (nowA + nowB > B) {
                int newA = nowA - (B - nowB);
                int newB = B;
                if (!set.contains(newA + " " + newB)) {
                    set.add(newA + " " + newB);
                    q.add(new int[] { newA, newB, dist + 1 });
                }
            }

            // A -> B로 이동하는 경우 (B가 안 넘치는 경우)
            if (nowA + nowB <= B) {
                int newA = 0;
                int newB = nowA + nowB;
                if (!set.contains(newA + " " + newB)) {
                    set.add(newA + " " + newB);
                    q.add(new int[] { newA, newB, dist + 1 });
                }
            }
        }

        return -1;
    }
}