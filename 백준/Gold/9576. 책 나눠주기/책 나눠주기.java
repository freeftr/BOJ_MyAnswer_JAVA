import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] ranges = new int[M][2];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                ranges[i][0] = a;
                ranges[i][1] = b;
            }

            Arrays.sort(ranges, (a, b) -> a[1] - b[1]);
            boolean[] checked = new boolean[N + 1];
            int answer = 0;
            for (int i = 0; i < M; i++) {
                int a = ranges[i][0];
                int b = ranges[i][1];
                for (int j = a; j <= b; j++) {
                    if (checked[j]) continue;
                    checked[j] = true;
                    answer++;
                    break;
                }
            }
            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 전공 서적을 나눠줌
- M명이 두 정수 a, b를 적어냄.
- 번호가 a 이상 b 이하인 책 중 한권을 준다.

요구
- 책을 줄 수 있는 최대 학생 수 구하기
 */