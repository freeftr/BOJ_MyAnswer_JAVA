import java.io.*;
import java.util.*;

public class Main {
    static int[][] wheel;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        wheel = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Integer.parseInt(s[j]);
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            int[] rotateDir = new int[4];
            rotateDir[idx] = dir;

            for (int j = idx - 1; j >= 0; j--) {
                if (wheel[j][2] != wheel[j + 1][6]) {
                    rotateDir[j] = rotateDir[j + 1] * -1;
                } else {
                    break;
                }
            }

            for (int j = idx + 1; j < 4; j++) {
                if (wheel[j - 1][2] != wheel[j][6]) {
                    rotateDir[j] = rotateDir[j - 1] * -1;
                } else {
                    break;
                }
            }

            for (int j = 0; j < 4; j++) {
                if (rotateDir[j] != 0) {
                    Rotate(rotateDir[j], j);
                }
            }
        }

        int ans = 0;
        if (wheel[0][0] == 1) ans += 1;
        if (wheel[1][0] == 1) ans += 2;
        if (wheel[2][0] == 1) ans += 4;
        if (wheel[3][0] == 1) ans += 8;

        System.out.println(ans);
    }

    static void Rotate(int dir, int idx) {
        if (dir == 1) {
            int temp = wheel[idx][7];
            for (int i = 7; i > 0; i--) {
                wheel[idx][i] = wheel[idx][i - 1];
            }
            wheel[idx][0] = temp;
        } else {
            int temp = wheel[idx][0];
            for (int i = 0; i < 7; i++) {
                wheel[idx][i] = wheel[idx][i + 1];
            }
            wheel[idx][7] = temp;
        }
    }
}