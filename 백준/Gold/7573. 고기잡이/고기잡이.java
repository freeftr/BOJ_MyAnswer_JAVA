import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, I, M;
	static ArrayList<int[]> fishes = new ArrayList<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		I = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			fishes.add(new int[]{x, y});
		}

		int answer = 0;
		for (int[] fish : fishes) {
			int fx = fish[0];
			int fy = fish[1];

			for (int a = 1; a < I; a++) {
				for (int b = 1; b < I; b++) {
					if (2 * a + 2 * b != I) continue;

					for (int dx = 0; dx <= a; dx++) {
						for (int dy = 0; dy <= b; dy++) {
							int sx = fx - dx;
							int sy = fy - dy;
							int ex = sx + a;
							int ey = sy + b;

							if (sx < 0 || sy < 0 || ex >= N || ey >= N) continue;

							int cnt = 0;
							for (int[] temp : fishes) {
								int nx = temp[0];
								int ny = temp[1];

								if (sx <= nx && nx <= ex && sy <= ny && ny <= ey) {
									cnt++;
								}
							}
							answer = Math.max(answer, cnt);
						}
					}
				}
			}
		}

		System.out.println(answer);
	}
}
