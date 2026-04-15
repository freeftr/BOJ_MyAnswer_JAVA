import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	/*
	조건
	- 위치에 상관없이 두 수 곱할 수 있다.
	- 최댓값 구하기

	풀이
	- 일단 가장 큰 수와 그 다음으로 큰 수 곱하기
	- 음수가 있으면 음수끼리
	- 음수 양수 분리 필요
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> plus = new PriorityQueue<>((a, b) -> b - a);
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		int one = 0;
		int zero = 0;

		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());

			if (a == 1) {
				one += 1;
				continue;
			}

			if(a > 0) {
				plus.add(a);
			} else {
				minus.add(a);
			}
		}

		int answer = one;
		while (plus.size() >= 2) {
			answer += plus.poll() * plus.poll();
		}

		while (minus.size() >= 2) {
			answer += minus.poll() * minus.poll();
		}

		if (plus.size() == 1) {
			answer += plus.poll();
		}

		if (minus.size() == 1) {
			answer += minus.poll();
		}

		System.out.println(answer);
	}
}
