import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	조건
	- 문자열 뒤에 A추가
	- 문자열 뒤집고 뒤에 B추가

	요구
	- S를 T로 만들수 있는지

	풀이
	- T에서 시작
	- T끝이 B면 B제거 후 뒤집기
	- T끝이 A면 1번
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		while (T.length() >= S.length()) {
			if (T.charAt(T.length() - 1) == 'B') {
				T = T.substring(0, T.length() - 1);
				T = new StringBuilder(T).reverse().toString();
			} else {
				T = T.substring(0, T.length() - 1);
			}

			if (T.equals(S)) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
}
