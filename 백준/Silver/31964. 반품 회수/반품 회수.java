import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] X = new int[N];
        int[] T = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            nodes.add(new Node(X[i], T[i]));
        }

        Collections.sort(nodes, (a, b) -> a.time - b.time);

        int idx = 0;
        int time = 0;
        for (Node node : nodes) {
            int nextIdx = node.x;
            int nextTime = node.time;

            time += Math.abs(nextIdx - idx);
            idx = nextIdx;

            if (time < nextTime) {
                time += nextTime - time;
            }
        }

        time += idx;

        System.out.println(time);
    }

    static class Node {
        int x;
        int time;
        Node (int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}

/*
조건
- N개의 집
- 트럭이 물품 회수.
- T에 반품할 물건 내놓음.
- 트럭은 제자리에서 대기 가능.

요구
- 트럭이 모든 물건 회수해서 돌아오는데 걸리는 시간.
 */