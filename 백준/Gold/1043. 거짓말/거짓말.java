import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int answer = 0;

        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        int truthCnt = Integer.parseInt(st.nextToken());

        for (int i = 0; i < truthCnt; i++) {
            parent[Integer.parseInt(st.nextToken())] = 0;
        }

        ArrayList<ArrayList<Integer>> party = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int comeCnt = Integer.parseInt(st.nextToken());
            int first = 0;

            party.add(new ArrayList<>());

            for (int j = 0; j < comeCnt; j++) {
                int come = Integer.parseInt(st.nextToken());

                party.get(i).add(come);
                if (j == 0) first = come;

                union(first, come);
            }
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int p : party.get(i)) {
                if (find(p) == 0) {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(M - cnt);
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
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
}

/*
조건
- 지민이 구라가 들통나면 안댐
- 들통나는 경우 => 진실을 아는 사람과 파티를 여러번 하는 경우

요구
- 파티를 갈 수 있는 최대 횟수 구하기
 */