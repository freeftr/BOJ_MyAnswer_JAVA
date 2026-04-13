import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	/*
	조건
	- 가방에는 한개의 보석만 넣기. 대신 무게를 넘으면 안댐.

	요구
	- 훔칠 수 있는 최대 가격 구하기

	풀이
	-
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> gems = new PriorityQueue<>((a, b) -> a[0] - b[0]);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			gems.add(new int[] { M, W });
		}

		int[] bags = new int[K];

		for (int i = 0; i < K; i++) {
			int C = Integer.parseInt(br.readLine());
			bags[i] = C;
		}

		Arrays.sort(bags);

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

		long answer = 0;

		for (int i = 0; i < K; i++) {
			int limit = bags[i];
			while (!gems.isEmpty() && gems.peek()[0] <= limit) {
				pq.add(gems.poll()[1]);
			}

			if (!pq.isEmpty()) answer += pq.poll();
		}

		System.out.println(answer);
	}
}
