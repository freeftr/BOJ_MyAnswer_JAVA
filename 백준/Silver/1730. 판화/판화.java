import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], '.');
        }

        char[] cmd = br.readLine().toCharArray();

        int x = 0;
        int y = 0;

        for (char c : cmd) {
            int nx = x;
            int ny = y;

            if (c == 'D') {
                nx += 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[x][y] == '.') map[x][y] = '|';
                else if (map[x][y] == '-') map[x][y] = '+';

                if (map[nx][ny] == '.') map[nx][ny] = '|';
                else if (map[nx][ny] == '-') map[nx][ny] = '+';
            }

            if (c == 'U') {
                nx -= 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (map[x][y] == '.') map[x][y] = '|';
                else if (map[x][y] == '-') map[x][y] = '+';

                if (map[nx][ny] == '.') map[nx][ny] = '|';
                else if (map[nx][ny] == '-') map[nx][ny] = '+';
            }

            if (c == 'R') {
                ny += 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (map[x][y] == '.') map[x][y] = '-';
                else if (map[x][y] == '|') map[x][y] = '+';

                if (map[nx][ny] == '.') map[nx][ny] = '-';
                else if (map[nx][ny] == '|') map[nx][ny] = '+';
            }

            if (c == 'L') {
                ny -= 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (map[x][y] == '.') map[x][y] = '-';
                else if (map[x][y] == '|') map[x][y] = '+';

                if (map[nx][ny] == '.') map[nx][ny] = '-';
                else if (map[nx][ny] == '|') map[nx][ny] = '+';
            }

            x = nx;
            y = ny;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
