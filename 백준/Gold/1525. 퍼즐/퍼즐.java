import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static String answer = "123456780";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				sb.append(st.nextToken());
			}
		}

		String start = sb.toString();
		System.out.println(bfs(start));
	}

	static int bfs(String start) {
		Queue<String> q = new LinkedList<>();
		Map<String, Integer> visited = new HashMap<>();

		q.add(start);
		visited.put(start, 0);

		int[] dx = {-1, 1, -3, 3};

		while (!q.isEmpty()) {
			String cur = q.poll();

			if (cur.equals(answer)) {
				return visited.get(cur);
			}

			int zeroIdx = cur.indexOf('0');
			int x = zeroIdx / 3;
			int y = zeroIdx % 3;

			for (int i = 0; i < 4; i++) {
				int nextIdx = zeroIdx + dx[i];

				if (nextIdx < 0 || nextIdx >= 9) continue;
				if (i == 0 && y == 0) continue;
				if (i == 1 && y == 2) continue;
				if (i == 2 && x == 0) continue;
				if (i == 3 && x == 2) continue;

				char[] arr = cur.toCharArray(); 
				char temp = arr[zeroIdx];
				arr[zeroIdx] = arr[nextIdx];
				arr[nextIdx] = temp;

				String next = new String(arr); 
				if (!visited.containsKey(next)) {
					visited.put(next, visited.get(cur) + 1);
					q.add(next);
				}
			}
		}

		return -1;
	}
}
