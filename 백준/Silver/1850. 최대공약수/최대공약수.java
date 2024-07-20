import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long A = sc.nextLong();
		long B = sc.nextLong();
		long n = 0;
		if(A < B) {
			long temp = B;
			B = A;
			A = temp;
		}
		while(B!=0) {
			n = A % B;
			A = B;
			B = n;
		}
		for(long i = 0; i < A; i++) {
			bw.write('1');
		}
		bw.close();
	}
}
