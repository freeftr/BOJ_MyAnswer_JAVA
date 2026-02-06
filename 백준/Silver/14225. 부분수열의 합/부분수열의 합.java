import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(S);

        long possible = 0;
        for (int i = 0; i < N; i++) {
            if (S[i] > possible + 1) break;
            possible += S[i];
        }

        System.out.println(possible + 1);
    }
}
