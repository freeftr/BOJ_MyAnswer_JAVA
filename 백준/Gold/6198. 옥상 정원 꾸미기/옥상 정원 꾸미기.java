import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();

		long answer = 0;
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			while(!s.isEmpty()) {
				if(s.peek()<=a){
					s.pop();
				} else {
					break;
				}
			}
			answer += s.size();
			s.push(a);
		}

		System.out.println(answer);
	}
}

// 10 3 7 4 12 2
// 10
// 10 3
// 10 7
// 10 7 4
// 12
// 12 2