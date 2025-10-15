import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean win(char[][] b, char ch) {
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == ch && b[i][1] == ch && b[i][2] == ch) return true;
        }
        for (int j = 0; j < 3; j++) {
            if (b[0][j] == ch && b[1][j] == ch && b[2][j] == ch) return true;
        }
        if (b[0][0] == ch && b[1][1] == ch && b[2][2] == ch) return true;
        if (b[0][2] == ch && b[1][1] == ch && b[2][0] == ch) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();
            if (s == null) break;
            if (s.equals("end")) break;

            char[] temp = s.toCharArray();
            char[][] board = new char[3][3];

            int o = 0, x = 0, dot = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char c = temp[i * 3 + j];
                    board[i][j] = c;
                    if (c == 'O') o++;
                    else if (c == 'X') x++;
                    else if (c == '.') dot++;
                }
            }

            boolean valid = true;

            if (!(x == o || x == o + 1)) {
                valid = false;
            } else {
                boolean xWin = win(board, 'X');
                boolean oWin = win(board, 'O');

                if (xWin && oWin) {
                    valid = false;
                } else if (xWin) {
                    valid = (x == o + 1);
                } else if (oWin) {
                    valid = (x == o);
                } else {
                    valid = (dot == 0 && x == o + 1);
                }
            }

            sb.append(valid ? "valid" : "invalid").append("\n");
        }

        System.out.print(sb.toString());
    }
}
