import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Integer, Integer> papers = new HashMap<>();

        for (int i = 1; i <= 6; i++) {
            papers.put(i, Integer.parseInt(br.readLine()));
        }

        int answer = 0;

        // 6x6
        answer += papers.get(6);

        // 5x5
        answer += papers.get(5);
        papers.put(1, Math.max(0, papers.get(1) - papers.get(5) * 11));

        // 4x4
        answer += papers.get(4);
        int need2 = papers.get(4) * 5;

        if (papers.get(2) >= need2) {
            papers.put(2, papers.get(2) - need2);
        } else {
            int lack = need2 - papers.get(2);
            papers.put(2, 0);
            papers.put(1, Math.max(0, papers.get(1) - lack * 4));
        }

        // 3x3
        answer += papers.get(3) / 4;
        int remain3 = papers.get(3) % 4;

        if (remain3 > 0) {
            answer++;

            if (remain3 == 1) {
                if (papers.get(2) >= 5) {
                    papers.put(2, papers.get(2) - 5);
                } else {
                    int lack = 5 - papers.get(2);
                    papers.put(2, 0);
                    papers.put(1, Math.max(0, papers.get(1) - lack * 4));
                }
                papers.put(1, Math.max(0, papers.get(1) - 7));
            }
            else if (remain3 == 2) {
                if (papers.get(2) >= 3) {
                    papers.put(2, papers.get(2) - 3);
                } else {
                    int lack = 3 - papers.get(2);
                    papers.put(2, 0);
                    papers.put(1, Math.max(0, papers.get(1) - lack * 4));
                }
                papers.put(1, Math.max(0, papers.get(1) - 6));
            }
            else if (remain3 == 3) {
                if (papers.get(2) >= 1) {
                    papers.put(2, papers.get(2) - 1);
                } else {
                    papers.put(1, Math.max(0, papers.get(1) - 4));
                }
                papers.put(1, Math.max(0, papers.get(1) - 5));
            }
        }

        // 2x2
        answer += papers.get(2) / 9;
        if (papers.get(2) % 9 != 0) {
            answer++;
            int empty = 9 - papers.get(2) % 9;
            papers.put(1, Math.max(0, papers.get(1) - empty * 4));
        }

        // 1x1
        answer += (papers.get(1) + 35) / 36;

        System.out.println(answer);
    }
}
