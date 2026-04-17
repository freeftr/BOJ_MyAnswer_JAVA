import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	조건
	- 좋다 = 어떤 수가 다른 두수의 합임
	- 위치가 다르면 다른 수임
	- 음수도 잇음

	요구
	- 좋은 수가 몇개인지

	풀이
	- 이분 탐색

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int answer = 0;

		for (int i = 0; i < N; i++) {
			int target = arr[i];

			int left = 0;
			int right = N - 1;
			boolean flag = false;

			while(left < right) {
				if (left == i) {
					left++;
					continue;
				}
				
				if (right == i) {
					right--;
					continue;
				}

				int sum = arr[left] + arr[right];

				if (sum == target) {
					flag = true;
					break;
				}

				if (sum > target) right--;
				else left++;
			}

			if (flag) answer++;
		}

		System.out.println(answer);
	}
}
