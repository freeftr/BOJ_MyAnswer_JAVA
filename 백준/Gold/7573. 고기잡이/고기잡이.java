import java.io.*;
import java.util.*;

public class Main {

    static int N, l, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<int[]> fishes = new ArrayList<>();
        ArrayList<Integer> candX = new ArrayList<>();
        ArrayList<Integer> candY = new ArrayList<>();

        candX.add(1);
        candY.add(1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            fishes.add(new int[]{x, y});
            candX.add(x);
            candY.add(y);
        }

        ArrayList<int[]> webs = makeWeb();

        int answer = 0;

        for (int sx : candX) {
            for (int sy : candY) {
                for (int[] w : webs) {
                    int a = w[0];
                    int b = w[1];

                    int ex = sx + a;
                    int ey = sy + b;

                    if (ex > N || ey > N) continue;

                    int cnt = 0;
                    for (int[] f : fishes) {
                        if (sx <= f[0] && f[0] <= ex && sy <= f[1] && f[1] <= ey) cnt++;
                    }

                    answer = Math.max(answer, cnt);
                }
            }
        }

        System.out.println(answer);
    }

    static ArrayList<int[]> makeWeb() {
        ArrayList<int[]> result = new ArrayList<>();
        int half = l / 2;
        for (int i = 1; i <= half - 1; i++) {
            result.add(new int[]{i, half - i});
        }
        return result;
    }
}