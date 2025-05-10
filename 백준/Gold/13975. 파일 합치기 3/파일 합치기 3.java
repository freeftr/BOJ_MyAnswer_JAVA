import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		ArrayList<Long> answer = new ArrayList<>();

		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}

			long sum = 0;

			while(true) {
				if (pq.size() == 1) break;
				Long	first = pq.poll();
				Long	second = pq.poll();

				sum += first + second;

				pq.add(first + second);
			}
			answer.add(sum);
		}

		for (long i : answer) {
			System.out.println(i);
		}
	}
}
