import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] parent;
	static int[][] map;
	static int answer = 0;
	static boolean start = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		map = new int[2001][2001];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) + 500;
			int y1 = Integer.parseInt(st.nextToken()) + 500;
			int x2 = Integer.parseInt(st.nextToken()) + 500;
			int y2 = Integer.parseInt(st.nextToken()) + 500;

			if (x1 > x2) {
				int tmp = x1; x1 = x2; x2 = tmp;
			}
			if (y1 > y2) {
				int tmp = y1; y1 = y2; y2 = tmp;
			}

			if ((x1 <= 500 && 500 <= x2 && (y1 == 500 || y2 == 500)) ||
				(y1 <= 500 && 500 <= y2 && (x1 == 500 || x2 == 500))) {
				start = true;
			}

			for (int x = x1; x <= x2; x++) {
				if (map[x][y1] != 0) union(i, map[x][y1]);
				if (map[x][y2] != 0) union(i, map[x][y2]);
				map[x][y1] = i;
				map[x][y2] = i;
			}
			for (int y = y1; y <= y2; y++) {
				if (map[x1][y] != 0) union(i, map[x1][y]);
				if (map[x2][y] != 0) union(i, map[x2][y]);
				map[x1][y] = i;
				map[x2][y] = i;
			}
		}

		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			set.add(find(i));
		}

		answer = set.size();
		if (start) answer--;

		System.out.println(answer);
	}

	static int find(int v) {
		if (parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			if (a < b) parent[b] = a;
			else parent[a] = b;
		}
	}
}
