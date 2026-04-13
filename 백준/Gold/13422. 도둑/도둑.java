import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	조건
	- 원형을 이어진 N개의 집
	- 도둑은 M개의 연속된 집에서 돈을 훔침.
	- 훔친 크기가 K 이상이면 방법장치 울림

	요구
	- 훔칠수 있는 경우의 수

	풀이
	- 슬라이딩 윈도우
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[] houses = new int[2 * N];

			for (int i = 0; i < N; i++) {
				houses[i] = Integer.parseInt(st.nextToken());
				houses[N + i] = houses[i];
			}

			int left = 0;
			int right = M - 1;
			long sum = 0;

			for (int i = 0; i < M; i++) {
				sum += houses[i];
			}

			int answer = 0;

			if (M == N) {
				if (sum < K) answer = 1;
				else answer = 0;

				sb.append(answer).append("\n");
				continue;
			}

			if (sum < K) answer = 1;

			while (left < N - 1) {
				sum -= houses[left];
				left++;
				right++;
				if (right == N * 2) break;
				sum += houses[right];

				if (sum < K) answer++;
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}
}