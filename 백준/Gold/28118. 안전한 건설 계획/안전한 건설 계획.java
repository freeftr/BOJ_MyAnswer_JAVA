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

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int answer = 0;
        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                for (int k = j + 1; k <= N; k++) {
                    int a = find(i);
                    int b = find(j);
                    int c = find(k);

                    if (a == b && b == c) {
                        continue;
                    } else if (a == b) {
                        union(a , c);
                        answer += 1;
                    } else if (b == c) {
                        union(b , a);
                        answer += 1;
                    } else if (a == c) {
                        union(a , b);
                        answer += 1;
                    } else {
                        continue;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}

/*
삼각형 형태로 보강함
- 모두 연결되어 있지 않으면 진행 불가능
- 빔 1개 => 비용 1
- 빔 2개 => 비용 x

모든 기둥에 빔이 존재하도록 하는 최소 비용.
*/