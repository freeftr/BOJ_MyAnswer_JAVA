import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int[][] preSumWhite = getPreSum('W', board, N, M);
        int[][] preSumBlack = getPreSum('B', board, N, M);

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= N - K + 1; i++) {
            for (int j = 1; j <= M - K + 1; j++) {

                int x2 = i + K - 1;
                int y2 = j + K - 1;

                int w = preSumWhite[x2][y2]
                        - preSumWhite[i - 1][y2]
                        - preSumWhite[x2][j - 1]
                        + preSumWhite[i - 1][j - 1];

                int b = preSumBlack[x2][y2]
                        - preSumBlack[i - 1][y2]
                        - preSumBlack[x2][j - 1]
                        + preSumBlack[i - 1][j - 1];

                answer = Math.min(answer, Math.min(w, b));
            }
        }

        System.out.println(answer);
    }

    static int[][] getPreSum(char color, char[][] board, int N, int M) {
        int[][] result = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int now = 0;

                if ((i + j) % 2 == 0) {
                    now = board[i][j] == color ? 0 : 1;
                } else {
                    now = board[i][j] != color ? 0 : 1;
                }

                result[i + 1][j + 1] = result[i + 1][j] + result[i][j + 1] - result[i][j] + now;
            }
        }
        
        return result;
    }
}

/*
조건
- M * N 크기의 보드
- 검, 흰으로 도색.
- 잘라서 K * K로 만듬.

요구
- 잘랐을 때 체스판이 되도록 칠해야 하는 정사각형의 최소 갯수를 구해야 함.

풀이
- 누적합
- 시작 = 흰 -> 흰 칠하기 : i + j가 짝수 = 흰, 홀수 = 검
- 시작 = 흰 -> 검 칠하기 :
- 시작 = 검 -> 흰 칠하기
- 시작 = 검 -> 검 칠하기
=> 시작이 어떤 것인지는 중요하지 않음. i + j의 짝수 홀수에 어떤 색을 칠하지가 중요
 */