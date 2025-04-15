import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		long sum = 0;

		HashMap<String, PriorityQueue<Integer>> gorilla = new HashMap<>();

		for (int q = 0; q < Q; q++) {
			String[] query = br.readLine().split(" ");
			String name = query[1];

			if (query[0].equals("1")) {
				int k = Integer.parseInt(query[2]);
				gorilla.putIfAbsent(name, new PriorityQueue<>((a, b) -> b - a));
				PriorityQueue<Integer> pq = gorilla.get(name);

				for (int i = 3; i < 3 + k; i++) {
					pq.add(Integer.parseInt(query[i]));
				}

			} else if (query[0].equals("2")) {
				int b = Integer.parseInt(query[2]);
				PriorityQueue<Integer> pq = gorilla.get(name);

				if (pq == null) continue;

				for (int i = 0; i < b && !pq.isEmpty(); i++) {
					sum += pq.poll();
				}
			}
		}

		System.out.println(sum);
	}
}
