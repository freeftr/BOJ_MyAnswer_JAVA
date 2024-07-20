import java.util.*;

public class Main {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean check = true;
		int index = 0;
		Stack<Integer> stackInt = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			if(stackInt.empty()) {
				stackInt.push(0);
			}
			int k = sc.nextInt();
			if(stackInt.peek() < k) {
				while(stackInt.peek() < k) {
					index++;
					stackInt.push(index);
					sb.append("+\n");
				}
			}
			if(stackInt.peek() == k) {
				stackInt.pop();
				sb.append("-\n");
				
			}
			if(stackInt.peek() > k) {
				check = false;
			}
		}
		String result = sb.toString();
		if(!check) {
			System.out.println("NO");
		}
		else {
			System.out.println(result);
		}
	}
}