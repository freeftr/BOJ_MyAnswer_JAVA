import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, d, k, c;
	static int max = 0;
	static int[] table, sushi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		table = new int[N];
		sushi = new int[d + 1];

		for (int i = 0; i < N; i++) {
			table[i] = Integer.parseInt(br.readLine());
		}

		int cnt = 0;
		for (int i = 0; i < k; i++) {
			if (sushi[table[i]] == 0) cnt++;
			sushi[table[i]]++;
		}

		if (sushi[c] == 0) cnt++;
		max = cnt;
		if (sushi[c] == 0) cnt--;

		for (int left = 0; left < N; left++) {
			int right = (left + k) % N;

			if (--sushi[table[left]] == 0) cnt--;
			if (sushi[table[right]]++ == 0) cnt++;

			if (sushi[c] == 0) {
				max = Math.max(max, cnt + 1);
			} else {
				max = Math.max(max, cnt);
			}
		}

		System.out.println(max);
	}
}
