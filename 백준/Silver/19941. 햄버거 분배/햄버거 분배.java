import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        String s = br.readLine();
        boolean[] visited = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'P') {
                for (int j = i - K; j <= i + K; j++) {
                    if (j < 0 || j >= s.length()) continue;
                    if (visited[j]) continue;
                    if (s.charAt(j) == 'P') continue;
                    visited[j] = true;
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 자신의 위치에서 거리가 K이하인 버거를 먹음.

요구
- 햄버거를 먹을 수 있는 최대 사람 수 구하기
1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
H   H   H   H   H   P   P   P   P   P   H   P   H   P   H   P   H   H   H   P
            V   V
 */