import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                int cur = Integer.parseInt(st.nextToken());
                arr[i] = cur;
            }

            long answer = 0;
            int max = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (arr[i] > max) {
                    max = arr[i];
                } else {
                    answer += (max - arr[i]);
                }
            }
            
            sb.append(answer).append("\n");

        }

        System.out.println(sb.toString());
    }
}

/*
행동
1. 주식 하나 사기
2. 원하는 만큼 주식 팔기
3. 아무것도 안하기

조건
- 날별로 주식 가격주어짐

요구
- 최대 이익

풀이
- 처음부터 순회
- 현재까지의 최소와 최대를 구함
- 최소와 최대를 구할때 인덱스도 같이 표시
- 아니면 일단 다 사기?

1 1 3 1 5
 */