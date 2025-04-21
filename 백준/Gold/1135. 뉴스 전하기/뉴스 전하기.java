import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			tree.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int boss = Integer.parseInt(st.nextToken());
			if (boss != -1) {
				tree.get(boss).add(i);
			}
		}

		System.out.println(dfs(0));
	}

	static int dfs(int v) {
		ArrayList<Integer> times = new ArrayList<>();

		for (int child : tree.get(v)) {
			times.add(dfs(child));
		}

		Collections.sort(times, Collections.reverseOrder());

		int max = 0;

		for (int i = 0; i < times.size(); i++) {
			max = Math.max(max, times.get(i) + i + 1);
		}

		return max;
	}
}
