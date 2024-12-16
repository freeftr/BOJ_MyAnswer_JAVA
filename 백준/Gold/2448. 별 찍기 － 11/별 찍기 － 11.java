import java.io.*;

public class Main {

    static char[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        answer = new char[N][2*N-1];

        make(0,0,N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2*N-1; j++) {
                if(answer[i][j]!='*'){
                    sb.append(" ");
                }
                else {
                    sb.append("*");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void make(int x, int y, int size) {
        if (size == 3) {
            answer[x][y+2] = '*';
            answer[x+1][y+1] = '*';
            answer[x+1][y+3] = '*';
            for (int i = 0; i < 5; i++) {
                answer[x+2][y+i] = '*';
            }
            return;
        }

        make(x, y+size/2, size/2);
        make(x+size/2, y, size/2);
        make(x+size/2, y+size, size/2);
    }
}