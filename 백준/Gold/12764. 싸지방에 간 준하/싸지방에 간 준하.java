import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<int[]> pq1 = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		PriorityQueue<int[]> pq2 = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> computer = new PriorityQueue<>();

		int[] answer = new int[N];
		int computerCount = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			pq1.add(new int[]{P, Q});
		}

		while (!pq1.isEmpty()) {
			int[] cur = pq1.poll();
			int start = cur[0];
			int end = cur[1];

			while (!pq2.isEmpty() && pq2.peek()[0] <= start) {
				int[] finished = pq2.poll();
				computer.offer(finished[1]);
			}

			int computerNumber;
			if (!computer.isEmpty()) {
				computerNumber = computer.poll();
			} else {
				computerNumber = computerCount++;
			}

			answer[computerNumber]++;
			pq2.add(new int[]{end, computerNumber});
		}

		System.out.println(computerCount);
		for (int i = 0; i < computerCount; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
