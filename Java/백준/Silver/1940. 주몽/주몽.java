import java.util.*;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int cnt = 0;
		int[] materials = new int[N];
		
		for(int i = 0; i < N; i++) {
			materials[i] = sc.nextInt();
		}
		for(int i : materials) {
			if(IntStream.of(materials).anyMatch(x -> x == M - i)) {
				cnt++;
			}
		}
		System.out.println(cnt/2);
	}
}