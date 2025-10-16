import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int cycle = 0;
        int group = N;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find(a) == find(b)) {
                cycle++;
            } else {
                union(a, b);
                group--;
            }
        }

        System.out.println(cycle + group - 1);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }
}

/*
조건
- 민서가 쓰러짐.
- 민서의 뉴런 일부가 끊어짐.
- 뉴런을 트리형태로 연결해야함.

요구
- 모두 하나의 집합으로 만들기 위한 최소 횟수

풀이
- 집합의 개수 구하기.
 */