import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A, B, C, D;
	static int[] AB, CD;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		AB = new int[N * N];
		CD = new int[N * N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx] = C[i] + D[j];
				idx++;
			}
		}

		Arrays.sort(AB);
		Arrays.sort(CD);

		long answer = 0;
		for (int i : AB) {
			int lower = lowerBound(CD, -i);
			int upper = upperBound(CD, -i);
			answer += upper - lower;
		}

		System.out.println(answer);
	}

	public static int upperBound(int[] arr, int target) {
		int left = 0, right = arr.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (arr[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	public static int lowerBound(int[] arr, int target) {
		int left = 0, right = arr.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
