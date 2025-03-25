import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] rank = new int[N+1];
			int[] size = new int[N+1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int a = Integer.parseInt(st.nextToken());
				rank[a] = i+1;
				size[a] = i;
			}

			int M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// a가 더 높을 때
				if (rank[a] < rank[b]) {
					size[a]++;
					size[b]--;
				} else {
					size[a]--;
					size[b]++;
				}
			}

			boolean impossible = false;
			ArrayList<int[]> result = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				result.add(new int[]{i, size[i]});
			}

			Collections.sort(result, (a,b) -> a[1] - b[1]);

			// for (int[] a : result) {
			// 	System.out.println(a[0] + " " + a[1]);
			// }
			// System.out.println();

			for (int i = 0; i < N; i++) {
				if (result.get(i)[1]<i) impossible = true;
			}

			if (impossible) {
				sb.append("IMPOSSIBLE\n");
			} else {
				for (int[] a : result) {
					sb.append(a[0] + " ");
				}
				sb.append("\n");
			}
		}

		System.out.print(sb.toString());
	}
}
