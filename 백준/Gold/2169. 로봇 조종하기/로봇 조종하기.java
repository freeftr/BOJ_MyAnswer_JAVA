import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] cache;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cache = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cache[0][0] = map[0][0];

        for (int i = 1; i < M; i++) {
            cache[0][i] = cache[0][i-1] + map[0][i];
        }

        for (int i = 1; i < N; i++) {
            int[] left = new int[M];
            int[] right = new int[M];

            left[0] = cache[i-1][0] + map[i][0];
            right[M-1] = cache[i-1][M-1] + map[i][M-1];

            for (int j = 1; j < M; j++) {
                left[j] = Math.max(left[j-1], cache[i-1][j]) + map[i][j];
            }

            for (int j = M-2; j >= 0; j--) {
                right[j] = Math.max(right[j+1], cache[i-1][j]) + map[i][j];
            }

            for (int j = 0; j < M; j++) {
                cache[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(cache[N-1][M-1]);
    }
}
