import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 1 동맹  2 전쟁
            int O = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            P = find(P);
            Q = find(Q);

            if (O == 1) {
                union(Q, P);
                A[P] += A[Q];
                A[Q] = 0;
            } else {
                if (A[Q] == A[P]) {
                    A[Q] = 0;
                    A[P] = 0;
                }

                if (A[Q] > A[P]) {
                    union(P, Q);
                    A[Q] -= A[P];
                    A[P] = 0;
                }

                if (A[P] > A[Q]) {
                    union(Q, P);
                    A[P] -= A[Q];
                    A[Q] = 0;
                }
            }
        }

        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i && A[i] > 0) nums.add(A[i]);
        }

        Collections.sort(nums);

        System.out.println(nums.size());
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }
}

/*
조건
- N개의 국가
- 동맹 = 병력 하나로 합침.
- 전쟁 = 병력이 많은 나라가 승리. 승리 병력 - 패배 병력

요구
- 남아있는 국가의 수 + 남은 병력 오름차순

1   2   3   4   5
1   2   3   4   5
10  20  30  40  50

1   2   3   4   5
1   1   3   4   5
30  0   30  4   50

1   2   3   4   5
1   1   3   3   5
30  0   70  0   50

1   2   3   4   5
3   3   3   3   5
0   0   40  0   50
 */