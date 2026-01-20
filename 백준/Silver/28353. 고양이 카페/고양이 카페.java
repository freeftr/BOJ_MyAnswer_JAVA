import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] w = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(w);
        int left = 0;
        int right = N - 1;
        int answer = 0;

        while (left < right) {
            int sum = w[left] + w[right];

            if (sum > K) {
                right--;
            } else {
                answer++;
                left++;
                right--;
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- N마리 고양이
- 고양이 2마리 무릎에
- 무게의 합의 K를 넘으면 안댐.

요구
- 최대 몇명이 행복해질 수 있는지

풀이
2   4   8   11  16

 */