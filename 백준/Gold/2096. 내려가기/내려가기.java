import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        int[][] map = new int[N][3];
        int[] max = new int[3];
        int[] min = new int[3];
        int[] nMax = new int[3];
        int[] nMin = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max[0] = map[0][0];
        max[1] = map[0][1];
        max[2] = map[0][2];

        min[0] = map[0][0];
        min[1] = map[0][1];
        min[2] = map[0][2];

        for (int i = 1; i < N; i++) {

            nMax[0] = Math.max(max[0] + map[i][0], max[1] + map[i][0]);

            nMax[1] = Math.max(max[0] + map[i][1], max[1] + map[i][1]);
            nMax[1] = Math.max(nMax[1], max[2] + map[i][1]);

            nMax[2] = Math.max(max[2] + map[i][2], max[1] + map[i][2]);

            nMin[0] = Math.min(min[0] + map[i][0], min[1] + map[i][0]);

            nMin[1] = Math.min(min[0] + map[i][1], min[1] + map[i][1]);
            nMin[1] = Math.min(nMin[1], min[2] + map[i][1]);

            nMin[2] = Math.min(min[2] + map[i][2], min[1] + map[i][2]);

            max[0] = nMax[0];
            max[1] = nMax[1];
            max[2] = nMax[2];

            min[0] = nMin[0];
            min[1] = nMin[1];
            min[2] = nMin[2];
        }

        int a = 0;
        int b = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            a = Math.max(a, max[i]);
            b = Math.min(b, min[i]);
        }

        System.out.println(a + " " + b);
    }
}

/*
조건
- N줄에 0~9 숫자가 세개씩 적혀 있음.
- 처음의 세 개의 숫자중 하나를 골라서 시작.
- 다움줄 내려가려면 바로 아래의 수로 넘어가거나 바로 아래의 수와 붙어 있는 수로만 이동 가능
- 적혀있는 숫자로 점수 획득.

요구
- 최대 점수 + 최소 점수?

풀이
- dp

1   2   3
4   5   6
4   9   0
 */