import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L, K, C;
	static int[] cut;
	static int firstCut;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cut = new int[K+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= K; i++) {
			cut[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cut);

		int left = 0, right = L;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid, C)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(left);
		System.out.println(firstCut);
	}

	 static boolean check(int maxLength, int c){
		int prev = L;
		int prevCut = -1;
		for (int i = K; i >= 0; i--) {
			int cur = cut[i];
			if (prev - cur <= maxLength) {
				if (cur != 0 && c > 0){
					prevCut = cur;
				}
			}  else{
				if (i == K) {
					return false;
				}

				cur = cut[++i];

				if(prev - cur <= maxLength) {

					if(c == 0){
						return false;
					}

					c--;
					prev = cur;

					if (cur != 0){
						prevCut = cur;
					}
				} else {
					return false;
				}
			}
		}
		firstCut = prevCut;
		return true;
	}
}
