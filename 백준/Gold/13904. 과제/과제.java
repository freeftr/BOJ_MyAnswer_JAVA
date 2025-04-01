import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static PriorityQueue<Integer>[] hw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		hw = new PriorityQueue[1001]; 

		for (int i = 0; i <= 1000; i++) {
			hw[i] = new PriorityQueue<>((a, b) -> b - a); 
		}

		int max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			max = Math.max(max, d);
			hw[d].add(w);
		}

		PriorityQueue<Integer> available = new PriorityQueue<>((a, b) -> b - a);
		int sum = 0;

		for (int i = max; i > 0; i--) {
			while (!hw[i].isEmpty()) {
				available.add(hw[i].poll());
			}
			if (!available.isEmpty()) {
				sum += available.poll();
			}
		}

		System.out.println(sum);
	}
}
