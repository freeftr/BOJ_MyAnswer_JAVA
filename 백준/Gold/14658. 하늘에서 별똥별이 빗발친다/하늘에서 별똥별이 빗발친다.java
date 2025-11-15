import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L, K;
    static int[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        N = 가로 길이
        M = 세로 길이
        L = 트램펄린 한변의 길이
        K = 별똥별의 수.
         */
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars[i][0] = x;
            stars[i][1] = y;
        }

        int max = 0;

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x = stars[i][0];
                int y = stars[j][1];

                int cnt = 0;

                for (int k = 0; k < K; k++) {
                    if (x <= stars[k][0] && stars[k][0] <= x + L) {
                        if (y <= stars[k][1] && stars[k][1] <= y + L) {
                            cnt++;
                        }
                    }
                }

                max = Math.max(max, cnt);
            }
        }

        System.out.println(K - max);
    }
}

/*
조건
- 하늘을 바로 차니 별이 떨어짐.
- 지표면에 떨어지는 별의 수를 최소화해야 한다.
- L*L 트램펄린으로 팅겨낸다.

요구
- 최대한 많은 별똥별을 튕겨내도록 트램펄린을 배치했을 때 몇개가 떨어질지.
 */