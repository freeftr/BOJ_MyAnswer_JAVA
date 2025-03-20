import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] eggs;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        backtrack(0);

        System.out.println(ans);
    }

    static void backtrack(int depth) {
        if (depth == N){
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i][0] <= 0){
                    sum++;
                }
            }
            ans = Math.max(ans, sum);
            return;
        }

        if (eggs[depth][0] <= 0){
            backtrack(depth+1);
            return;
        }
        boolean flag = false;

        for (int i = 0; i < N; i++){
            if (i==depth) {
                if (i==N-1){
                    backtrack(depth+1);
                    return;
                } else {
                    continue;
                }
            }
            if (eggs[i][0] <= 0) {
                continue;
            }
            flag = true;
            eggs[i][0] -= eggs[depth][1];
            eggs[depth][0] -= eggs[i][1];
            backtrack(depth+1);
            eggs[i][0] += eggs[depth][1];
            eggs[depth][0] += eggs[i][1];
        }
        
        if(!flag){
            backtrack(depth+1);
            return;
        }
    }
}
// (1,100) (8,5) (3,5)
// (0,100) (0,5) (3,5)