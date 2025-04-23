import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> jewels = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			jewels.offer(new int[]{M, V});
		}

		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		long answer = 0;
		for (int i = 0; i < K; i++) {
			int limit = bags[i];

			while (!jewels.isEmpty() && jewels.peek()[0] <= limit) {
				pq.offer(jewels.poll()[1]);
			}

			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}

		System.out.println(answer);
	}
}
