import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] diff = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());

            diff[i] = B[i] - A[i];
        }

        Arrays.sort(diff);

        if (N % 2 == 1) {
            System.out.println(1);
        } else {
            int left = diff[N/2 - 1];
            int right = diff[N/2];
            System.out.println(right - left + 1);
        }
    }
}

/*
조건
- 각 마법사는 한명의 머글 만남
- 기다리는 시간 = 먼저 도착한 사람이 늦게 도착한 사람이 도착할 때까지
- 기다리는 시간 = A + T - B의 절댓값

요구
- 기다리는 시간의 합이 최소가 되는 서로 다른 정수 T의 개수 구하기

풀이
- 일단 약속과 도착의 차이를 구한 리스트 만들기
4
4
9
-3
 */