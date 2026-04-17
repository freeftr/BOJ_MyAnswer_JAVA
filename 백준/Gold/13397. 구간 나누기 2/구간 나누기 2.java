import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	조건
	- N개의 배열을 M개 이하의 구간으로 나누기
	- 하나의 구간에는 하나 이상의 수
	- 모든 수 사용
	- 점수 = 구간내 최댓값 - 최솟값

	요구
	- 구간의 점수의 최댓값의 최솟값을 구하기

	풀이
	- 매개변수 탐색
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int max = 0;
		int min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}

		int left = 0;
		int right = max - min;
		int answer = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid, N, M, arr)) {
				right = mid - 1;
				answer = Math.min(answer, mid);
			} else {
				left = mid + 1;
			}
		}

		System.out.println(answer);
	}

	static boolean check(int mid, int N, int M, int[] arr) {
		int cnt = 1;
		int min = arr[0];
		int max = arr[0];

		for (int i = 1; i < N; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);

			if (max - min > mid) {
				cnt++;
				min = arr[i];
				max = arr[i];
			}
		}

		return cnt <= M;
	}
}
