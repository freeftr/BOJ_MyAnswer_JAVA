import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 박스 수랑
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        double low = 0;
        double high = Math.min(L, Math.min(W, H));

        for (int i = 0; i < 100; i++) {
            double mid = (low + high) / 2;

            if (check(mid, N, L, W, H)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        System.out.println(low);
    }

    static boolean check(double A, int N, int L, int W, int H) {
        long cnt = (long)(L / A) * (long)(W / A) * (long)(H / A);

        return cnt >= N;
    }

}

/*
조건
- 같은 크기 작은 박스 N개, 정육면체 A x A x A
- L, W, H 직육면체 박스에 모두 넣으려고 함.

요구
- 가능한 A 최댓값 구하기

풀이
- A를 이분탐색으로
- check -> N개들어갈수 있는지 확인
 */