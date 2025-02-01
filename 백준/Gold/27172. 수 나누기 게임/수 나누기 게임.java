import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean[] isMultiple;
    static int[] scores;
    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        scores = new int[1000001];
        cards = new int[N];
        isMultiple = new boolean[1000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            isMultiple[cards[i]] = true;
        }

        for (int i = 0; i < N; i++) {

            for (int j = cards[i] * 2; j <= 1000000; j += cards[i]) {
                if (isMultiple[j]) {
                    scores[j]--;
                    scores[cards[i]]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(scores[cards[i]] + " ");
        }
    }
}
