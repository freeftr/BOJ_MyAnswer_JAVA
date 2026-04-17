import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	/*
	조건
	- n개의 정수
	- 한 개 제거 -> 점수 += 정수
	- 두 개 제거 -> 점수 += 정수 * 정수

	요구
	- 수열을 다 소모했을 때 점수의 최댓값 구하기

	풀이
	- 음수는 음수끼리
	- 양수는 양수끼리
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> minus = new PriorityQueue<>();
		PriorityQueue<Integer> plus = new PriorityQueue<>((a, b) -> (b - a));

		long score = 0;

		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(br.readLine());

			if (a <= 0) minus.add(a);
			else if (a > 1) plus.add(a);
			else score++;
		}

		while (minus.size() >= 2) {
			score += (long)minus.poll() * minus.poll();
		}

		while (plus.size() >= 2) {
			score += (long)plus.poll() * plus.poll();
		}

		if (minus.size() == 1) score += minus.poll();
		if (plus.size() == 1) score += plus.poll();
		System.out.println(score);
	}
}
