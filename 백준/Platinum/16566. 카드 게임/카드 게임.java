import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int[] cards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            int b = findNextCard(cards, a);
            if (b != -1) {
                System.out.println(b);
                union(b, b + 1);
            }
        }
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[a] = b;
        }
    }

    static int findNextCard(int[] cards, int value) {
        int idx = binarySearch(cards, value);
        if (idx == -1 || idx >= cards.length) {
            return -1;
        }
        return find(cards[idx]);
    }

    static int binarySearch(int[] cards, int value) {
        int left = 0, right = cards.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (cards[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left < cards.length ? left : -1;
    }
}