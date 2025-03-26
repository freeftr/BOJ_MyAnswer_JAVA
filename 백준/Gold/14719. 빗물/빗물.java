import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		map = new int[W];
		int ans = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < W; i++) {
			int left = 0;
			int right = 0;

			for (int j = 0; j < i; j++) {
				left = Math.max(left, map[j]);
			}

			for (int j = i + 1; j < W; j++) {
				right = Math.max(right, map[j]);
			}

			// System.out.println("i: " + i + " left: " + left + " right: " + right);

			if (left >= map[i] && right >= map[i]) {
				ans += Math.min(left, right) - map[i];
			}
		}

		System.out.println(ans);
	}
}
