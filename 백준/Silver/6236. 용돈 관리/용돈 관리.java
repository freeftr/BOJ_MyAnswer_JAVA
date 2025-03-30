import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] money;
	static int sum;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		money = new int[N];

		sum = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(br.readLine());
			sum += money[i];
			max = Math.max(max, money[i]);
		}

		int left = max;
		int right = sum;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid)){
				right = mid - 1;
				ans = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}

	static boolean check(int mid) {
		int temp = mid;
		int cnt = 1;

		for (int i = 0; i < N; i++) {
			if (temp >= money[i]) {
				temp -= money[i];
			} else {
				cnt++;      
				temp = mid - money[i]; 
			}
		}

		return cnt <= M; // M번 이하로 인출 가능해야 true
	}
}
