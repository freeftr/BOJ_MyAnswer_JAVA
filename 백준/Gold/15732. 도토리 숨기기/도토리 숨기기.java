import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, K, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ArrayList<int[]> rules = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            rules.add(new int[]{A, B, C});
        }

        int answer = 0;
        int low = 1;
        int high = N;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (check(mid, rules, D)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean check(int mid, ArrayList<int[]> rules, int D) {
        long sum = 0;
        for (int[] rule : rules) {
            int s = rule[0];
            int e = Math.min(mid, rule[1]);
            int d = rule[2];

            if (mid < s) continue;

            sum += (e - s) / d + 1;
        }

        return sum >= D;
    }
}

/*
조건
- 도토리 D개 보관
- A부터 B까지 C개 간격으로 하나씩 더 넣는다.
- 이러한 규칙이 K개
- 마지막 도토리가 들어가 있는 상자의 번호 구하기

요구
- 마지막 도토리가 들어가 있는 상자의 번호 구하기

풀이
- 매개 변수 탐색
- 마지막 번호 기준으로 계산
 */